import java.util.*;
import java.io.*; 

public class KMeans 
{
	private List <Points2D> points;
	private List <Cluster> clusterList;
	private int K;
	
	KMeans(String filename, int K) throws NumberFormatException, IOException
	{
		this.K = K;
		
		// Read file extract points into points
		this.points = new ArrayList<Points2D>();
		this.parsePoints(filename);
		this.printPoints();
	}
	
	void parsePoints(String filename) throws NumberFormatException, IOException
	{
        BufferedReader br  = new BufferedReader(new BufferedReader(new FileReader(filename)));
       
        String[] delimited; 
        String line;
        Points2D p;
        
        while((line = br.readLine()) != null)
        {
        	delimited = line.split(" ");
        	p = new Points2D(Float.valueOf(delimited[0]), Float.valueOf(delimited[1]));
            this.points.add(p);
        } 
	}
	
	void init()
	{
		clusterList = new ArrayList<Cluster>();
		// Initialize clusters in clusterList with sequential IDs
		Random rand;
		for(int i=0; i<K; i++)
		{
			//Choose a random point from the known set to initialize at
			rand = new Random();
			
			Cluster c = new Cluster(i, points.get(rand.nextInt(this.points.size())));
			clusterList.add(c);
		}
	}
	
	void printPoints()
	{
		System.out.println("All the points: ");
		for(int i=0;i<points.size(); i++)
		{
			System.out.println(points.get(i));
		}
	}
	
	void printClusters() 
	{
		System.out.println("All the Clusters: ");

		for (Cluster a : clusterList) {
			System.out.println(a);
		}
	}
	
	List<Points2D> getCentroids()
	{
		return null;
	}
	
	void clear()
	{
		
	}
	
	void assignPoints()
	{
		
	}
	
	void calculateCentroids()
	{
		
	}
	
	void calculate()
	{
		
	}
	
}

/*
List <Points2D> points; ------------------------------------------------------------

int numClusters; ------------------------------------------------------------

List <Clusters> clusterList; ------------------------------------------------------------ 

KMeans(String file, int K){//Initialize K, read the file and extract points} ------------------------------------------------------------

void init(); // Initialize clusters in clusterList with sequential ids  ------------------------------------------------------------

void printPoints();  ------------------------------------------------------------
void printClusters(); ------------------------------------------------------------

List2D<Point2D>  getCentroids(); ------------------------------------------------------------

void clear() -------------------------------------------------------------------------------
//Iterate through each cluster and call the cluster’s clear_closest_points() method 
//If the cluster.get_closest_points().isEmpty()

void assignPoints();
//For each point, calculate distances between itself and each cluster's centroid
//Set the point.cluster_id to the cluster that is closest to it
//Add the id of the point to the cluster.points

void calculateCentroids();
//For each cluster 
//             pass points to it and call newCentroid()

Void  calculate()
{
Boolean finish = false;

while(!finish){
List<Point2D> current_Centroids = getCentroids();
clear()

assignPoints()

calculateCentroids();

List <Point2D> new_centroids = getCentroids();

//if checking the two centroid lists is true then finish =true
//else checking the two centroid lists is false then finish =false
	
*/