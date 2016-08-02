import numpy
import pandas

length = 6
data = pandas.DataFrame(numpy.random.randn(length,1),
                        index=pandas.date_range('2013-08-01', periods=length, freq='B'),
                        columns=[ "data" ])
data.ix[3, "data"] = numpy.NaN
print(data)
print(data.ffill())