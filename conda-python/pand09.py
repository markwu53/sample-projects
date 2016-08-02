import pandas as pd
import numpy as np

rng = pd.date_range('2000-01-01', periods=6)
df1 = pd.DataFrame(np.random.randn(6, 3), index=rng, columns=['A', 'B', 'C'])
df2 = df1.copy()
#print(df1); print(df2)
#df = df1.append(df2, ignore_index=True); print(df)
df = pd.DataFrame(data={'Area' : ['A'] * 5 + ['C'] * 2, 'Bins' : [110] * 2 + [160] * 3 + [40] * 2, 'Test_0' : [0, 1, 0, 1, 2, 0, 1], 'Data' : np.random.randn(7)})
df["Test_1"] = df["Test_0"] - 1
print(df)
mm = pd.merge(df, df, left_on="Test_0", right_on="Test_1", how="left")
print(mm.columns)