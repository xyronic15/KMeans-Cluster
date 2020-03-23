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
max_K = 7

# Iterate over the K range
for i in range(min_K, max_K+1):

    # We will use a 3x2 subplots to show 6 trials for each K value
    fig_title = f'K-Means Test, K={i}'
    fig, axs = plt.subplots(3, 2, figsize=(15,15))
    fig.canvas.set_window_title(fig_title)
    fig.suptitle(fig_title, fontsize=16)

    col = 0
    row = 0

    for j in range(0, 6):

        try:
            centroids = np.genfromtxt(f'../output/centroids_K={i}_trial={j}.txt', delimiter = ' ')
            c_x = np.array(centroids[:,0])
            c_y = np.array(centroids[:,1])

            axs[row][col].plot(x, y, 'x', label='data')
            axs[row][col].plot(c_x, c_y, 'o', label='centroid')

            axs[row][col].set_title(f'K={i}, trial={j+1}')
            axs[row][col].legend()

            if(col==0):
                col=1
            else:
                row+=1
                col=0

        except:
            print(f'cannot open output/centroids_K={i}_trial={j}.txt')

    # Plot once all subplots are shown
    plt.savefig(fig_title + '.png')
    plt.show()
