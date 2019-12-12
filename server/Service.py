from flask import Flask, request, jsonify, make_response
from flask_restful import Resource, Api, reqparse
import json
from DatabaseService import DatabaseService
import os

dbService = DatabaseService()
application = Flask(__name__)
api = Api(application)


class LoginAPI(Resource):
    def post(self):
        data = request.get_json(force=True)
        return dbService.login(data['email'], data['password'])


class RegisterAPI(Resource):
    def post(self):
        data = request.get_json(force=True)
        return dbService.register(data)


class FetchAPI(Resource):
    def get(self):
        return dbService.getAll()


class CategoryAPI(Resource):
    def get(self, category):
        return dbService.getByCategory(category)


class SearchAPI(Resource):
    def get(self, inp):
        return dbService.search(inp)


api.add_resource(LoginAPI, '/login')
api.add_resource(RegisterAPI, '/register')
api.add_resource(FetchAPI, '/fetch')
api.add_resource(CategoryAPI, '/category/<category>')
api.add_resource(SearchAPI, '/search/<inp>')

if __name__ == '__main__':
    from gevent.pywsgi import WSGIServer
    http_server = WSGIServer(('', 5000), application)
    http_server.serve_forever()
