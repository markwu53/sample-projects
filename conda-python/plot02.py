import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.style
matplotlib.style.use("ggplot")

iris = pd.read_csv("iris.data")
iris.columns=["SepalLength", "SepalWidth", "PetalLength", "PetalWidth", "Name"]
iris = iris[iris["SepalLength"] > 5]
iris["SepalRatio"] = iris.SepalWidth / iris.SepalLength
iris["PetalRatio"] = iris.PetalWidth / iris.PetalLength
#iris.plot(kind="scatter", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="line", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="bar", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="barh", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="hist", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="box", x="SepalRatio", y="PetalRatio"); plt.show()
iris.plot(kind="kde", figsize=(10,8), x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="area", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="pie", x="SepalRatio", y="PetalRatio"); plt.show()
#iris.plot(kind="hexbin", figsize=(10,8), x="SepalRatio", y="PetalRatio"); plt.show()