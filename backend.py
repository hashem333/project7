from flask import Flask, jsonify, send_from_directory, url_for
from flask_cors import CORS
import os

# configuration
DEBUG = True

# instantiate the app
app = Flask(__name__, static_folder='dist', static_url_path='/dist')
CORS(app)


# serve the index.html file
@app.route('/')
def serve_index_html():
    return send_from_directory(app.static_folder, "index.html")

# serve static js files
@app.route('/js/<path:filename>')
def serve_js(filename):
    return send_from_directory(os.path.join(app.static_folder, 'js'), filename)

# serve static css files
@app.route('/css/<path:filename>')
def serve_css(filename):
    return send_from_directory(os.path.join(app.static_folder, 'css'), filename)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080, debug=DEBUG)
