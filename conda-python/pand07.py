import pandas

data = pandas.read_csv("AirQualityUCI.csv", sep=";")
data = data[data.columns[range(7)]]
pt08_max_time = data.groupby("Date").apply(lambda sub: sub["Time"][sub["PT08.S2(NMHC)"].idxmax()])
print(pt08_max_time)