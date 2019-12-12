import mysql.connector

class Database:
    def __init__(self, *args):
        self.database = None
        self.cursor = None
        self.connected = False

    def return_message(self, code, message, data):
        message = dict(code=code, message=message, data=data)
        return message

    def connect(self, host, port, database, user, passwd):
        try:
            self.database = mysql.connector.connect(
                host=host,
                port=port,
                database=database,
                user=user,
                passwd=passwd
            )
            self.cursor = self.database.cursor()
            self.connected = True
            return self.return_message(200, "connection established", None)
        except mysql.connector.Error as e:
            return self.return_message(400, e, None)

    def run_query(self, query):
        if not self.connected:
            return self.return_message(400, "connection does not exist", None)
        try:
            self.cursor.execute(query)
            try:
                data = self.cursor.fetchall()
                self.database.commit()
                return self.return_message(200, "query success", data)
            except Exception:
                self.database.commit()
                return self.return_message(200, "query success", None)
        except Exception:
            return self.return_message(400, "query failed", None)