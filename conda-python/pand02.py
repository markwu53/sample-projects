import pandas
import numpy as np

data = pandas.read_csv("AirQualityUCI.csv", sep=";")
data = data[data.columns[:-2]]
#data = data[(data["PT08.S1(CO)"] > 1800) & (data["NMHC(GT)"] > 600)][["Date", "Time", "PT08.S1(CO)", "NMHC(GT)"]]
data = data[data["PT08.S1(CO)"] > 1300]
data = data[data["NMHC(GT)"] > 600]
data = data[["Date", "Time", "PT08.S1(CO)", "NMHC(GT)"]]
data = data.groupby("Date")
#print(data.count())
agg = data.agg({ "PT08.S1(CO)": [np.mean, np.size] })
print(type(agg))
#print(agg.columns)
print(type(agg["PT08.S1(CO)"]))
print(agg["PT08.S1(CO)"].columns)
print(agg["PT08.S1(CO)"][["mean"]])

"""
aa = data5["PT08.S1(CO)"]
bb = aa > 1300
cc = [True, False, True, True, False]
print(type(bb))
print(type(cc))
dd = pandas.Series(cc)
print(type(dd))
print(data5[dd])
"""

"""
import re
pattern = "([^(]*)\(([^)]*)\)"
replace = "\\1_\\2"
data.columns = [ re.sub(pattern, replace, item) for item in data.columns ]
data.columns = [ re.sub("\.", "_", item) for item in data.columns ]
print(data.columns)
#print(data.head(5))

import pymysql
conn = pymysql.connect(host="docker-machine", user="root", password="12345678", db="test", )
print("connected")
data.to_sql("air_quality_uci", conn, flavor="mysql", if_exists="replace", index=False)
print("done")
"""