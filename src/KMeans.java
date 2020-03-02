import java.util.*;
import java.io.*; 

public class KMeans 
{
	private List <Points2D> points;
	private List <Cluster> clusterList;
	private int K;
	
	KMeans(String filename, int K) throws NumberFormatException, IOException
	{
		
		System.out.println("Kmeans constructor");
		this.K = K;
		
		// Read file extract points into points
		this.points = new ArrayList<Points2D>();
		this.parsePoints(filename);
		
		// Initialize clusters
		this.init();
		
		// Do some printing
		
	}
	
	void parsePoints(String filename) throws NumberFormatException, IOException
	{
        BufferedReader br  = new BufferedReader(new BufferedReader(new FileReader(filename)));
       
        String[] delimited; 
        String line;
        Points2D p;
        int i=0;
        while((line = br.readLine()) != null)
        {
        	delimited = line.split(" ");
        	p = new Points2D(i, Float.valueOf(delimited[0]), Float.valueOf(delimited[1]));
            this.points.add(p);
            i++;
        } 
        br.close();
	}
	
	void init()
	{
		System.out.println("Init entered");
		clusterList = new ArrayList<Cluster>();
		
		// Initialize clusters in clusterList with sequential IDs
		Random rand;
		
		List<Integer> taken_ids = new ArrayList<Integer>();
		Cluster c;
		int genRand;
		
		for(int i=0; i<K; i++)
		{
			// Choose a random point from the known set to initialize at
			rand = new Random();
			
			// Make sure no collisions occur during cluster initialization
			do 
			{
				genRand = rand.nextInt(this.points.size());
			}while(taken_ids.contains(genRand));
			
			taken_ids.add(genRand);
			c = new Cluster(i, points.get(genRand));
			clusterList.add(c);

			System.out.println("Point #" + genRand + " is " + this.points.get(genRand).toString());
		}
		System.out.println();
	}
	
	// Maybe obsolete
	void printPoints(List<Points2D> p)
	{
		for(int i=0;i<p.size(); i++)
		{
			System.out.println(p.get(i));
		}
	}

	// Needs to be tested
	void printClusters() 
	{
		for(int i=0;i<K; i++)
		{
			System.out.println("Cluster #" + i);
			System.out.print(clusterList.get(i).get_pointsIds());
		}	
	}
	
	// Returns the centroids of each cluster
	List<Points2D> getCentroids()
	{
		List<Points2D> centroids = new ArrayList<>();
		for(int i=0; i < K; i++)
		{
			centroids.add(clusterList.get(i).getCentroid());
		}
		System.out.println("Centroids are: " + centroids);
		return centroids;
	}
	
	// Iterate over K clusters, and clear the arraylist containing the point Ids that belong to each cluster
	void clear()
	{
		for(int i=0; i<K; i++)
		{
			clusterList.get(i).clear_pointsIds();
		}
		System.out.println("Cleared all points in every cluster");
	}
	
	
	void assignPoints()
	{		
		for (Points2D point : this.points) 
		{			
			float minDist = Float.MAX_VALUE;
			float dist;
			int closestCluster = 0;
			
			// iterates over points and finds which cluster is closest
			for(Cluster cluster : this.clusterList)
			{
				dist = point.getDistance(cluster.getCentroid());
				if(dist < minDist)
				{
					minDist = dist;
					closestCluster = cluster.getId();
				}
			}
			// Add clusterId to point to keep track of what cluster it belongs to
			point.setCluster(closestCluster);
			
			// Add pointId to cluster to keep track of what points it has
			clusterList.get(closestCluster).addPointIds(point.get_id());
			
			System.out.println("Closest cluster to point #" + point.get_id() + " is " + closestCluster);
		}
		System.out.println();
	}
	
	// Recalculate centroids (based on the points associated with it) for each cluster
	void calculateCentroids()
	{
		for(Cluster cluster : this.clusterList)
		{
			cluster.newCentroid(this.points);
		}
	}
	
	void calculate()
	{
		boolean finish = false;
		boolean quit_flag = false;
		List <Points2D> old_centroids;
		List <Points2D> new_centroids;
		
		while(finish == false)
		{
			old_centroids = this.getCentroids();
			
			this.clear();
			this.assignPoints();
			this.calculateCentroids();
			
			new_centroids = this.getCentroids();
			
			// Iterate over centroids
			for(int i = 0; i < K; i++)
			{
				// The bottom condition should not run if the old and new centroids are the same
				if(old_centroids.get(i).equals(new_centroids.get(i)) == false)
				{
					quit_flag = true;
				}
			}
			// Whenever there is at least one centroid that does 
			// 	not match the old_centroid, then we continue iterating
			if(quit_flag == false)
			{
				finish = true;
			}
			else
			{
				quit_flag = false;
			}		

			System.out.println("Looping...");
		}
		
	}
	
	void outputCentroids(String filename)
	{
		try (FileWriter writer = new FileWriter(filename);
        	BufferedWriter bw = new BufferedWriter(writer))
        	{
        		List<Points2D> centroids = this.getCentroids();
        		for(Points2D centroid : centroids)
        		{
        			bw.write(centroid.get_x() + " " + centroid.get_y() + "\n");
        		}
        	} 
        catch (IOException e) 
        {
            System.err.format("IOException: %s%n", e);
        }
	}
	
}