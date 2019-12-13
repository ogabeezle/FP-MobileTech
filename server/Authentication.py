from Database import Database
import jwt
import datetime
import time
from Token import Token
import os
from dotenv import load_dotenv
load_dotenv()
print(os.getenv('USER'))

class Authentication:
    def __init__(self, *args):
        self.db = Database()
        self.db.connect(
            "localhost",
            "3306",
            "sponsy",
            "chael",
            "wakakaka1")
            #os.getenv('AUTH_HOST'),
            #os.getenv('AUTH_PORT'),
            #os.getenv('AUTH_DB'),
            #os.getenv('USER'),
            #os.getenv('PASS')))

    def return_message(self, code, message, data):
        message = dict(code=code, message=message, data=data)
        return message

    def register(self, data):
        query = """SELECT * FROM account WHERE email="{}" """. format(
            data['email'])
        result = self.db.run_query(query)
        print(result)
        if not (len(result['data']) == 0):
            return self.return_message(400, "Username Taken", None)

        query = """INSERT INTO `account`(
            `name`,
            `email`,
            `contact_person`,
            `password`,
            `image_url`,
            `address`,
            `account_type`,
            `deskripsi`)
            VALUES ('{}','{}','{}','{}','{}','{}',{},'{}')""". format(
            data['name'],
            data['email'],
            data['contactPerson'],
            data['password'],
            data['imageUrl'],
            data['address'],
            data['accountType'],
            data['deskripsi'])
        result = self.db.run_query(query)
        print(result)
        print(query)
        return self.return_message(200, 'Register Success', None)

    def login(self, username, password):
        query = """SELECT * FROM account WHERE email="{}" AND password="{}" """. format(
            username, password)
        result = self.db.run_query(query)

        if not (len(result['data']) == 1):
            return self.return_message(400, "login failed", None)

        data = {}
        data['email'] = result['data'][0][1]
        token_expiration = datetime.datetime.utcnow() + datetime.timedelta(seconds=3600)
        data['exp'] = token_expiration
        token = Token(data).get_encoded()
        return self.return_message(200, 'Login Success', token)

    def check_token(self, data):
        try:
            return self.return_message(200, "token exist", Token(data).get_decoded())
        except Exception:
            return self.return_message(400, "token has been expired", None)

    def getAll(self):
        query = """select * from account order by id asc"""
        print(query)
        result = self.db.run_query(query)
        hasil = []
        print(result)
        result=result["data"]
        for item in result:
            hasil.append({
                'id': item[0],
                'name': item[1],
                'email': item[2],
                'contactPerson': item[3],
                'imageUrl': item[5],
                'address': item[6],
                'deskripsi': item[8] 
            })

        return self.return_message(200, 'success', hasil)

    def getByCategory(self, category):
        query = """select * from account 
                    inner join account_category on account.id=account_category.account_id
                    inner join category on category.id=account_category.category_id
                    where category.name='{}'""".format(category)
        result = self.db.run_query(query)['data']
        hasil = []
        for item in result:
            hasil.append({
                'id': item[0],
                'name': item[1],
                'email': item[2],
                'contactPerson': item[3],
                'imageUrl': item[5],
                'address': item[6],
                'deskripsi': item[8] 
            })

        return self.return_message(200, 'success', hasil)

    def search(self, inp):
        query = """select * from account where name='{}'""".format(inp)
        result = self.db.run_query(query)['data']
        hasil = []
        for item in result:
            hasil.append({
                'id': item[0],
                'name': item[1],
                'email': item[2],
                'contactPerson': item[3],
                'imageUrl': item[5],
                'address': item[6],
                'deskripsi': item[8]
            })

        return self.return_message(200, 'success', hasil)
