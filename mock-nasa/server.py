import json
import traceback
from flask import abort, Flask, request

app = Flask(__name__)
app.url_map.strict_slashes = False

# mock: https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=DEMO_KEY
@app.route('/neo/rest/v1/neo/browse', methods=['GET'])
def browse():
    try:
        api_key = request.args['api_key']
        if api_key is None:
            msg = 'Missing api_key'
            app.logger.error(msg)
            return msg, 400

        app.logger.info(f'Browsing asteroids. api_key={api_key}')

        with open('asteroid-sample.json', 'r') as f:
            return json.load(f), 200
    except Exception as e:
        app.logger.error('Failed to fetch asteroids.\n', e)
        abort(500)

@app.errorhandler(404)
def not_found(e):
    return 'Page not found', 404

@app.errorhandler(Exception)
def internal_error(e):
    app.logger.error(traceback.format_exc())
    return 'Internal server error', 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9050)
