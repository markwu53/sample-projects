import pandas as pd
import matplotlib.pyplot as plt
#import matplotlib
#matplotlib.style.use("ggplot")

iris = pd.read_csv("iris.data")
iris.columns=["SepalLength", "SepalWidth", "PetalLength", "PetalWidth", "Name"]
print(iris.head())

(iris.query('SepalLength > 5')
    .assign(SepalRatio = lambda x: x.SepalWidth / x.SepalLength,
        PetalRatio = lambda x: x.PetalWidth / x.PetalLength)
    .plot(kind='scatter', x='SepalRatio', y='PetalRatio'))
plt.show()