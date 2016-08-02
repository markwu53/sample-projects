import pandas

data = pandas.read_csv("AirQualityUCI.csv", sep=";")
#data = data[data.columns[range(6)]]
data = data[data.columns[[1,3,5,7,9]]]
#print(data.ix[[1,3,5]])
#print(data.ix[range(5,9)])
#print(data[data.index.isin(range(5,9))])
data = data.ix[range(5,9)]
data.index = ["A", "B", "C", "D"]
print(data)
#print(data.ix[0:3])
#print(data.ix[range(0,3)])
#print(data.iloc[0:3])
#print(data.ix["A":"C"])
#data2 = data.head(10); print(data2.ix[(data2["NO2(GT)"].astype(float)-97).abs().argsort()])