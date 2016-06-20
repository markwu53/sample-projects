import mysql.connector

conn = mysql.connector.connect(host="192.168.99.100", port="32769", user="root", password="12345678", database="test")
cursor = conn.cursor()
cursor.execute("select * from person")
for row in cursor: print(row)

#cursor.execute("insert into person (name, age) values ('Yong', 36)")
#conn.commit()

cursor.execute("update person set age = 38 where name = 'Yong'")
conn.commit()

cursor.execute("select * from person")
for row in cursor: print(row)