# KMeans-Cluster
Data Structure Final Project


![Git Cheat Sheet](https://i.redd.it/8341g68g1v7y.png)

#### Input
points.txt

#### Output
centroids.txt


*There's also a visio sequence diagram that we can use to help visualize the process a bit better. The initialization process is already there but if you hav anything you want to add or change then go ahead*


| Class | Description | Class Members (Pseudocode) | Asignee | Completion |
|-----------|---------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|------------|
| Point2D | Represents one point in 2D  | float coord_x;<br>float coord_y;<br>Cluster cluster;<br><br>Point2D(coord_x, coord_y){//Initialize}<br><br>float getX();<br>float getY();<br><br>void setX(float x);<br>void setY(float y);<br><br>float getDistance(cluster);<br> |  | [ ] |
| Cluster | Represents one cluster | <br>int id;<br>Point2D centroid;<br>ArrayList Points; // Change to list if needed<br><br>Cluster(int id){//Initialize}<br>Cluster(int id, Point2D centroid){//Initialize}<br><br>int getId();<br>Point2D getCentroid();<br>Point2D setCentroid();<br><br><br>ArrayList getPoints();<br>void setPoints(ArrayList Points); //Maybe not<br><br>//MORE HERE<br> |  | [ ] |
| KClusters | Maybe |  |  | [ ] |
| KMeans | Creates a points obj. 1. centroid initialization. 2. loop --> initialization step and update step | List <Points2D> points;<br><br>int numClusters;<br><br>List <Clusters> clusterList;<br><br>KMeans(String file, int K){//Initialize K, read the file and extract points}<br><br>void init(); // Initialize centroids in clusterList with sequential ids <br>void update();<br><br>void printPoints(); <br>void printClusters();<br><br>void calculate(){<br>    List distances(cluster, points); //<br>    void clearClusters(); // Resets cluster centroids and points;<br>    void assignCluster(); // Associates points to the closest cluster<br>    void calculateCentroids(); // Finds the clusters' points' mean<br>}<br>void outputClusters(Filename f); |  | [ ] |
