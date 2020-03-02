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


try:
	centroids = np.genfromtxt('../output/centroids.txt', delimiter = ' ')
	c_x = np.array(centroids[:,0])
	c_y = np.array(centroids[:,1])
    
except:
    print("cannot open output/centroids.txt")

try:

    plt.scatter(x, y, marker = 'o', label='data points')	
    plt.scatter(c_x, c_y, marker = 'x', label='centroids')
    plt.show()
except:
    print("plotting failed")
