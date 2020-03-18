import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial import Voronoi, voronoi_plot_2d

try:
    points = np.genfromtxt('../input/points.txt', delimiter = ' ')
    m, n = points.shape

    x = np.array(points[:, :-1])
    y = np.array(points[:, -1:]) # We do -1: to make sure the shape of y is (m,1) instead of (m,)
except:
    print("cannot open input/points.txt")


min_K = 2
max_K = 4

fig, axs = plt.subplots(max_K-min_K+1, figsize=(15,15))
fig.suptitle('K-Means Test', fontsize=16)

count = 0

for i in range(min_K, max_K+1):

    try:
        centroids = np.genfromtxt(f'../output/centroids_K={i}.txt', delimiter = ' ')
        c_x = np.array(centroids[:,0])
        c_y = np.array(centroids[:,1])

        axs[count].plot(c_x, c_y, 'o', x, y, 'x')
        axs[count].set_title(f'K={i}')
        count+=1

    except:
        print(f'cannot open output/centroids_K={i}.txt')

plt.show()

try:

    centroids = np.genfromtxt('../output/centroids_K=4.txt', delimiter = ' ')
    c_x = np.array(centroids[:,0])
    c_y = np.array(centroids[:,1])

    vor = Voronoi(centroids)
    voronoi_plot_2d(vor)

    plt.scatter(x, y, marker = 'o', label='data points')
    plt.scatter(c_x, c_y, marker = 'o', label='centroids')
    plt.show()

except:
    print("plotting failed")
