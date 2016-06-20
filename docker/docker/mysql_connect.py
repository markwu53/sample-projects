from com.ziclix.python.sql import zxJDBC
#import com.ziclix.python.sql.zxJDBC as zxJDBC

connString = "jdbc:mysql://192.168.99.100:32769/test"
driver = "com.mysql.jdbc.Driver"
conn = zxJDBC.connect(connString, "root", "12345678", driver)
print("connected")
cursor = conn.cursor()
cursor.execute("select * from person")
for row in cursor:
    print(row)


