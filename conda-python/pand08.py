import pandas as pd

df = pd.DataFrame({
                   'animal': 'cat dog cat fish dog cat cat'.split(),
                   'size': list('SSMMMLL'),
                   'weight': [8, 10, 11, 1, 20, 12, 12],
                   'adult' : [False] * 5 + [True] * 2
                   })
print(df)
#print(df["weight"][1])
gb = df.groupby("animal")
gbf = gb.filter(lambda f: f["weight"].sum() > 20)
ff = [ item for item in dir(gb) if item[0] != "_" ]
for item in ff: print(item)
#print(help(gb.get_group))
#print(gb.apply(lambda f: (f["size"], f.weight.sum())))
#gba = gb.apply(lambda f: (f.iloc[0]["size"], f["weight"].sum()))
gba = gb.apply(lambda f: f.animal)
print(gba)
#print(gb.groups)
#gbw = gb["weight"]
#gb = df.groupby(lambda idx: df["weight"][idx] % 2); print(gb.first())
#gb = df.groupby(lambda idx: df["animal"][idx]); print(gb.first())
#print(gb.apply(lambda f: f["size"][f["weight"].idxmax()]))
#gb = df.groupby(["animal", "size"]); print(gb.first())
#gb = df.groupby(["size", lambda idx: df["animal"][idx], ]); print(gb.first())
#gb = df.groupby(["size", lambda idx: df["weight"][idx] % 2]); print(gb.first().index)
#print(help(gb.first))