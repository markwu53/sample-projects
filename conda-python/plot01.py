import numpy as np
import matplotlib.pyplot as plt
# Data to be represented
X = np.random.randn(256)

# Actual plotting
fig = plt.figure(figsize=(8,6), dpi=72, facecolor="white")
axes = plt.subplot(111)
heights, positions, patches = axes.hist(X, color='white')

"""
axes.spines['right'].set_color('none')
axes.spines['top'].set_color('none')
axes.xaxis.set_ticks_position('bottom')

# was: axes.spines['bottom'].set_position(('data',1.1*X.min()))
axes.spines['bottom'].set_position(('axes', -0.05))
axes.yaxis.set_ticks_position('left')
axes.spines['left'].set_position(('axes', -0.05))

axes.set_xlim([np.floor(positions.min()), np.ceil(positions.max())])
axes.set_ylim([0,70])
axes.xaxis.grid(False)
axes.yaxis.grid(False)
fig.tight_layout()
"""

plt.show()
