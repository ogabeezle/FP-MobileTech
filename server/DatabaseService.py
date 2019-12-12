from Authentication import Authentication


class DatabaseService:
    def __init__(self):
        self.authentication = Authentication()

    def login(self, username, password):
        result = Authentication().login(username, password)
        return result

    def register(self, data):
        result = self.authentication.register(data)
        return result

    def check_token(self, data):
        result = self.authentication.check_token(data)
        return result

    def getAll(self):
        return self.authentication.getAll()

    def getByCategory(self, category):
        return self.authentication.getByCategory(category)

    def search(self, inp):
        return self.authentication.search(inp)
