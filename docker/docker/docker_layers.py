import subprocess, shlex
import mysql.connector

allLayers = []

def dockerLayers():
    output = subprocess.check_output(shlex.split("docker images --all --no-trunc"))
    items = output.split("\n")
    items = items[1:]
    items = [ item.split() for item in items ]
    items = [ item for item in items if len(item) >= 3 ]
    for item in items:
        allLayers.append(["%s:%s" % (item[0], item[1]), item[2].split(":")[1]])

    for item in items:
        command = "docker inspect --format '{{{{.RootFS.Layers}}}}' {imageId}".format(imageId=item[2].split(":")[1])
        output = subprocess.check_output(shlex.split(command))
        layers = output[1:-1].split()
        layers = [layer.split(":")[1] for layer in layers]
        allLayers.extend([["%s:dependency" % item[0], layer] for layer in layers])

def putImages():
    conn = mysql.connector.connect(host="docker-machine", port="3306", user="root", password="12345678", database="test")
    cursor = conn.cursor()
    sqlFormat = "insert into docker_layers (image, image_id) values ('{image}', '{image_id}')"
    for layer in allLayers:
        sql = sqlFormat.format(image=layer[0], image_id=layer[1])
        print(sql)
        cursor.execute(sql)
    conn.commit()

#282fd552add6aa67509775e68e32aeabb2ea88726299367d49e36847c65833b4

def createDb():
    conn = mysql.connector.connect(host="docker-machine", port="3306", user="root", password="12345678")
    cursor = conn.cursor()
    cursor.execute("create database if not exists test")

def createDbTable():
    conn = mysql.connector.connect(host="docker-machine", port="3306", user="root", password="12345678", database="test")
    cursor = conn.cursor()
    cursor.execute("create table if not exists docker_layers (image varchar(50), image_id varchar(100))")

def cleanTable():
    conn = mysql.connector.connect(host="docker-machine", port="3306", user="root", password="12345678", database="test")
    cursor = conn.cursor()
    cursor.execute("truncate docker_layers")

def checkTable():
    conn = mysql.connector.connect(host="docker-machine", port="3306", user="root", password="12345678", database="test")
    cursor = conn.cursor()
    cursor.execute("select * from docker_layers")
    for row in cursor: print(row)

#createDb()
#createDbTable()
#cleanTable()
dockerLayers()
putImages()
#for layer in allLayers: print(layer)