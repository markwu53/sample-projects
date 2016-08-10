import pandas

data = pandas.read_csv("iris.data")
data.columns = list("ABCDE")
data1 = data.ix[range(10)].head()
data2 = data.ix[range(10)].tail()
#data = pandas.concat([data, data2], keys=list("XY"))
data = pandas.concat([data1, data2], axis=0, keys=list("XY"))
print(data)