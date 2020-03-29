import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Author: Group 13
 * Date: 03/01/2020
 * Purpose: This class defines a KMeans blueprint that contains main algorithm implementations.
 */
public class KMeans {
	private List<Points2D> points; // ArrayList of the actual Point2D objects
	private List<Cluster> clusterList; // ArrayList of cluster objects
	private int K; // The number of expected clusters (K) is predefined

	// Constructor initializes
	KMeans(String filename, int K) throws NumberFormatException, IOException {
		this.K = K;

		// Read file extract points into points ArrayList
		this.points = new ArrayList<Points2D>();
		this.parsePoints(filename);

	}

	// reads the given file containing input points and append to points array
	private void parsePoints(String filename) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(filename)));

		String[] delimited;
		String line;
		Points2D p;
		int i = 0;

		// Keep reading until end of file
		while ((line = br.readLine()) != null) {

			// X and Y coordinates are space delimited
			delimited = line.split(" ");

			// Create a new point
			p = new Points2D(i, Float.valueOf(delimited[0]), Float.valueOf(delimited[1]));

			// Add it to points ArrayList
			this.points.add(p);
			i++;
		}
		br.close();
	}

	// Standard cluster initializer
	// Initializes clusters such that centroids are located at a random input point
	// Pros: better than random value initialization as centroid is guaranteed to be
	// within bounds
	// Cons: Centroids may be close together yielding a suboptimal solution
	void init() {

		this.clusterList = new ArrayList<Cluster>();

		// Keep track of ids where another cluster is intialized to prevent multiple
		// clusters from being intialized
		List<Integer> taken_ids = new ArrayList<Integer>();
		// Used for holding the points furthest from the first centroid chosen
		Cluster c;
		int genRand;

		for (int i = 0; i < this.K; i++) {

			Random rand = new Random();

			// Choose a random point from the known input list
			// Make sure no collisions occur during cluster initialization
			// So keep looping until unique point is found
			do {
				genRand = rand.nextInt(this.points.size());
			} while (taken_ids.contains(genRand));

			// Add the randomly generated point to the taken_ids ArrayList
			taken_ids.add(genRand);

			// Initialize clusters in clusterList with sequential IDs
			c = new Cluster(i, points.get(genRand));
			this.clusterList.add(c);
		}

	}

	// Iterates over each cluster and returns a centroid
	private Points2D[] getCentroids() {

		Points2D[] centroids = new Points2D[this.K];
		for (int i = 0; i < this.K; i++) {
			// Invoke copy constructor to create a new point
			Points2D p = new Points2D(this.clusterList.get(i).getCentroid());
			centroids[i] = p;
		}
		return centroids;
	}

	// Iterate over clusters, and clear Arraylist containing the associated pointIds
	private void clear() {

		for (int i = 0; i < K; i++) {
			clusterList.get(i).clear_pointsIds();
		}
	}

	// Assignment step, two-way assigning of closest points to each cluster
	private void assignPoints() {

		// Iterate over each point
		for (Points2D point : this.points) {

			float minDist = Float.MAX_VALUE;
			int closestCluster = 0; // By default set to 0

			// Find out which cluster is closest to the point (Euclidean distance)
			for (Cluster cluster : this.clusterList) {
				float dist = point.getDistance(cluster.getCentroid());
				if (dist < minDist) {
					minDist = dist;
					closestCluster = cluster.getId();
				}
			}
			// POV of Point2D object: Add clusterId to point
			point.setCluster(closestCluster);

			// POV of Cluster object: Add pointId to cluster
			this.clusterList.get(closestCluster).addPointIds(point.get_id());
		}
	}

	// Recalculate centroid of each cluster
	private void calculateCentroids() {
		for (Cluster cluster : this.clusterList) {
			cluster.newCentroid(this.points);
		}
	}

	// Master loop that runs the update step until centroids stabilize
	public void calculate() {

		boolean finish = false;
		int K_matches = 0;
		int iterations = 1;

		while (!finish) {

			Points2D[] old_centroids = this.getCentroids();

			this.clear(); // Clear old points associated with each cluster
			this.assignPoints(); // Re-assign new points to each cluster (and vice-versa)
			this.calculateCentroids(); // Update centroid for each cluster

			Points2D[] new_centroids = this.getCentroids();

			// Iterate over centroids
			for (int i = 0; i < K; i++) {
				// Add 1 to K_matches for each centroid that remains unchanged
				if (old_centroids[i].equals(new_centroids[i])) {
					K_matches++;
				}
			}

			if (K_matches == this.K) {
				finish = true;
			} else {
				iterations++;
				K_matches = 0;
			}
		}

		System.out.println("K: " + this.K + ", Iterations per trial: " + iterations);

	}

	// Outputs centroids to new file
	public void outputCentroids(String filename) {
		try (FileWriter writer = new FileWriter(filename); BufferedWriter bw = new BufferedWriter(writer)) {
			Points2D[] centroids = this.getCentroids();
			for (Points2D centroid : centroids) {
				bw.write(centroid.get_x() + " " + centroid.get_y() + "\n");
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

}
