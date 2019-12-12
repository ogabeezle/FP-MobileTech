import jwt

class Token:
    def __init__(self, data=''):
        self.key = 'cloudcomputing'
        self.data = data

    def get_encoded(self):
        encoded = jwt.encode(self.data, self.key, 'HS256').decode('UTF-8')
        return encoded

    def get_decoded(self):
        decoded = jwt.decode(self.data, self.key, 'HS256')
        return decoded