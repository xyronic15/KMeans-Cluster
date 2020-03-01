import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial import Voronoi, voronoi_plot_2d



try:
    points = np.genfromtxt('../input/points.txt', delimiter = ' ')
    m, n = points.shape

    x = np.array(points[:, :-1])
    y = np.array(points[:, -1:]) # We do -1: to make sure the shape of y is (m,1) instead of (m,)
    plt.scatter(x, y, marker = 'o', label='data points')
except:
    print("cannot open input/points.txt")

try:
    centroids = np.genfromtxt('../output/centroids.txt', delimeter = ' ')
    c_x, c_y = centroids[:,0], centroids[:,1]

    vor = Voronoi(centroids)
    voronoi_plot_2d(vor)
    plt.scatter(c_x, c_y, marker = 'o', label='centroids')
except:
    print("cannot open output/centroids.txt")

try:
    plt.show()
except:
    print("plotting failed")
