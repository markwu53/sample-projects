import pandas as pd
import numpy as np
df1 = pd.DataFrame({'key': ['A', 'B', 'C', 'D'], 'value': np.random.randn(4)})
#print(df1)
df2 = pd.DataFrame({'key': ['B', 'D', 'D', 'E'], 'value': np.random.randn(4)})
#print(df2)
dd = pd.merge(df1, df2, on="key")
print(dd)