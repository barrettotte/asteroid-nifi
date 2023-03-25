import os
from flask import abort, Flask, jsonify, render_template, request
from flask_cors import CORS, cross_origin

app = Flask(__name__)
app.url_map.strict_slashes = False
cors = CORS(app)

@app.route('/')
def index():
    return render_template('index.html', hello_message='hello world')

@app.route('/api/v1/')
def api_index():
    return 'Asteroid API', 200

@app.route('/api/v1/asteroids', methods=['GET'])
def get_asteroids():
    try:
        return 'get_asteroids()', 200 # TODO:
    except Exception as e:
        app.logger.error('Failed to fetch asteroids.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids/<asteroid_id>/', methods=['GET'])
def get_asteroid_by_id(asteroid_id):
    try:
        return f'get_asteroid_by_id({request.json})', 200 # TODO:
    except Exception as e:
        app.logger.error('Failed to fetch asteroid.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids/', methods=['POST'])
def create_asteroid():
    try:
        return f'create_asteroid({request.json})', 200 # TODO:
    except Exception as e:
        app.logger.error('Failed to create asteroid.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids/<asteroid_id>/', methods=['PUT'])
def update_asteroid(asteroid_id):
    try:
        return f'update_asteroid({request.json})', 200 # TODO:
    except Exception as e:
        app.logger.error('Failed to update asteroid.\n%s', e)
        abort(500)

@app.route('/api/v1/asteroids/<asteroid_id>/', methods=['DELETE'])
def delete_asteroid(asteroid_id):
    try:
        return f'delete_asteroid({request.json})', 200 # TODO:
    except Exception as e:
        app.logger.error('Failed to delete asteroid.\n%s}', e)
        abort(500)

@app.errorhandler(404)
def not_found(e):
    return 'Page not found', 404

@app.errorhandler(Exception)
def internal_error(e):
    return 'Internal server error', 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9000)
