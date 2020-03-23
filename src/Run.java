import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Group 13
 * Date: 03/01/2020
 * Purpose: Main class to execute algorithm.
 */

public class Run {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// In practice, we cannot always notice distinct clusters, so we try range of K
		// values
		// We test using K values: 2,3,4 to see what fits the data the best
		for (int i = 2; i < 8; i++) {

			// Create KMeans object of K value i
			KMeans kmeans = new KMeans("input/points.txt", i);

			for (int j = 0; j < 6; j++) {

				// Initialize clusters
				kmeans.init();

				System.out.print(String.format("Trial: %s, ", (j+1)));

				// Start the algorithm
				kmeans.calculate();

				// Output to specific file with distinct name which indicates centroid number
				kmeans.outputCentroids(String.format("output/centroids_K=%s_trial=%s.txt", i, j));
			}
			System.out.println();
		}
	}

}
