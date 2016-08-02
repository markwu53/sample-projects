import pandas
df = pandas.DataFrame({
                       'animal': 'cat dog cat fish dog cat cat'.split(),
                       'size': list('SSMMMLL'),
                       'weight': [8, 10, 11, 1, 20, 12, 12],
                       'adult' : [False] * 5 + [True] * 2
                       })
data = df.groupby("animal").apply(lambda subf: subf['size'][subf['weight'].idxmax()])
#for item in dir(data): print(item)
#print(help(data.head))
print(data)