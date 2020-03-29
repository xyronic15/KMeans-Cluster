/*
 * Author: Group 13
 * Date: 03/01/2020
 * Purpose: This class defines a 2D point.
 */
public class Points2D {

	private float coord_x;
	private float coord_y;
	private int clusterId; // Keeps track of the cluster which the point belongs to
	private int pointId;
	
	// This constructor is meant to initialize centroids 
	public Points2D(float x, float y) {
		this.pointId = -1;
		this.set_x(x);
		this.set_y(y);
	}
	
	// Copy constructor, meant to intialize centroids
	public Points2D(Points2D p) {
		this.pointId = -1;
		this.set_x(p.get_x());
		this.set_y(p.get_y());
	}

	// This constructor is meant to initialize regular points
	public Points2D(int pointId, float x, float y) {
		this.pointId = pointId;
		this.set_x(x);
		this.set_y(y);
	}

	public int get_id() {// gets point id
		return this.pointId;
	}

	public void set_x(float x) {// sets x alone
		this.coord_x = x;
	}

	public void set_y(float y) {// sets y alone
		this.coord_y = y;
	}

	public float get_x() {// returns x of point
		return this.coord_x;
	}

	public float get_y() {// gets y of point
		return this.coord_y;
	}

	public void setCluster(int c) {// assigns cluster's id to current point
		this.clusterId = c;
	}

	public int getCluster() {// return clusterId
		return this.clusterId;
	}

	// Returns the distance between a point and itself
	public float getDistance(Points2D centroid) {
		return (float) Math.sqrt(Math.pow(centroid.get_y() - this.get_y(), 2) + Math.pow(centroid.get_x() - this.get_x(), 2));
	}

	// Overriding toString() method to print a point in the following format (x, y)
	public String toString() {
		return "(" + this.get_x() + ", " + this.get_y() + ")";

	}
	
	// Override equals operator to compare current point with another
	public boolean equals(Points2D p) {
		if (this.get_x() == p.get_x() && this.get_y() == p.get_y()) {
			return true;
		} else {
			return false;
		}
	}

}