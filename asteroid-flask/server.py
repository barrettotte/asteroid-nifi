import datetime
import json
import logging
import os
import traceback
from bson import ObjectId
from flask import abort, Flask, jsonify, render_template, request
from kafka import KafkaProducer
from pymongo import MongoClient
from asteroid import Asteroid

# init flask
app = Flask(__name__)
app.url_map.strict_slashes = False
app.logger.setLevel(logging.DEBUG)

# init mongodb
mongo_conn = f"mongodb://{os.environ['MONGODB_USERNAME']}:{os.environ['MONGODB_PASSWORD']}" +\
            f"@{os.environ['MONGODB_HOST']}:{os.environ['MONGODB_PORT']}/?authSource={os.environ['MONGODB_DB']}"
mongo_client = MongoClient(mongo_conn)
db = mongo_client[os.environ['MONGODB_DB']]

# init kafka
kafka_bootstrap_servers = [f"{os.environ['KAFKA_HOST']}:{os.environ['KAFKA_PORT']}"]
kafka_topic = os.environ['KAFKA_TOPIC']
kafka_producer = KafkaProducer(bootstrap_servers=kafka_bootstrap_servers,
                               value_serializer=lambda m: json.dumps(m, default=str).encode('utf-8'))

# map mongodb row to Asteroid model
def row_to_asteroid(row: dict) -> Asteroid:
    return Asteroid(name=row['name'], diameter_min=row['diameter_min'], diameter_max=row['diameter_max'],
                    hazard=row['hazard'], relative_velocity=row['relative_velocity'], distance=row['distance'],
                    orbiting_body=row['orbiting_body'], created=row['created'], created_by=row['created_by'], id=row['_id'])

# query recent asteroids
def query_asteroids_recent(limit: int = 50) -> list[Asteroid]:
    cursor = db.asteroid.find().sort('created', -1).limit(limit)
    return [row_to_asteroid(asteroid) for asteroid in cursor]

@app.route('/')
def index():
    return render_template('index.html', asteroids=query_asteroids_recent())

@app.route('/api/v1/asteroids', methods=['GET'])
def get_asteroids():
    try:
        app.logger.debug('get_asteroids()')
        return jsonify(query_asteroids_recent()), 200
    except Exception as e:
        app.logger.error('Failed to fetch asteroids.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids/<asteroid_id>', methods=['GET'])
def get_asteroid_by_id(asteroid_id):
    try:
        app.logger.debug(f'get_asteroid_by_id({asteroid_id})')
        asteroid = db.asteroid.find_one({'_id': ObjectId(str(asteroid_id))})
        return jsonify(asteroid), 200
    except Exception as e:
        app.logger.error('Failed to fetch asteroid.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids', methods=['POST'])
def create_asteroid():
    try:
        app.logger.debug(f'create_asteroid() {request.json}')
        created = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S.%f')

        asteroid = Asteroid(name=request.json['name'], diameter_min=request.json['diameter_min'], 
                            diameter_max=request.json['diameter_max'], hazard=request.json['hazard'],
                            relative_velocity=request.json['relative_velocity'], distance=request.json['distance'], 
                            orbiting_body=request.json['orbiting_body'], created=created)

        inserted = db.asteroid.insert_one(asteroid.__dict__)
        asteroid.id = str(inserted.inserted_id)
        
        # send asteroid to kafka topic
        msg = asteroid.__dict__
        msg.pop('_id', None) # remove bson id
        kafka_producer.send(kafka_topic, asteroid.__dict__)

        return jsonify(asteroid), 200

    except Exception as e:
        app.logger.error('Failed to create asteroid.\n%s', e)
        abort(500)

@app.errorhandler(404)
def not_found(e):
    return 'Page not found', 404

@app.errorhandler(Exception)
def internal_error(e):
    app.logger.error(traceback.format_exc())
    return 'Internal server error', 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9000, debug=True)
