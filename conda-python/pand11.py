import numpy
import pandas
data = pandas.read_csv("AirQualityUCI.csv", sep=";")
#print(data.head())
#data = data[data.columns[:-2]]
#data = data[[3,4,]].head(); print(data)
#data = data.apply(numpy.sqrt)
#data = data.apply(numpy.mean)
data = data[[0,1,3,]].head()
print(data)
#data[data.columns[2]] = numpy.sqrt(data[data.columns[2]]); print(data)
#def myf(a): return numpy.sqrt(a) if a.name == "PT08.S1(CO)" else a
#data = data.apply(myf)
#print(data)
def myf(functions): return lambda c: functions[c.name](c)
def myid(c): return c
data = data.apply(myf({"Date": myid, "Time": myid, "PT08.S1(CO)": numpy.sqrt}))
print(data)
