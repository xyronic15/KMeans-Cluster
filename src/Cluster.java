import java.util.ArrayList;
import java.util.List;

/*
 * Author: Group 13
 * Date: 03/01/2020
 * Purpose: This class defines a cluster that groups points together.
 */
public class Cluster {

	int id;
	Points2D centroid; // point that is located at the average x and y values of associated points
	List<Integer> pointIds; // Mutable list of pointIds (to save memory, do not store actual points)

	// Initialize a cluster with given id, and centroid
	public Cluster(int id, Points2D centroid) {
		this.id = id;
		this.centroid = centroid;
		this.pointIds = new ArrayList<Integer>();
	}

	public int getId() {// return id
		return this.id;
	}

	public void clear_pointsIds() {// clear pointIds associated with current cluster
		this.pointIds.clear();
	}

	public List<Integer> get_pointsIds() {// return ArrayList containing pointIds
		return this.pointIds;
	}

	public void addPointIds(int p_id) {// add int to list of PointIds
		this.pointIds.add(p_id);
	}

	public Points2D getCentroid() {// return current centroid
		return this.centroid;
	}

	// Computes a new Point2D by taking average of x and y values of each point
	// associated with this cluster 
	public void newCentroid(List<Points2D> p) {

		if (this.pointIds.isEmpty()) {
			return;
		} else {
			float sum_x = 0;
			float sum_y = 0;
			int numPoints = this.pointIds.size();

			for (Integer id : this.pointIds) {
				sum_x += p.get(id).get_x();
				sum_y += p.get(id).get_y();
			}

			float new_x = sum_x / (float) numPoints;
			float new_y = sum_y / (float) numPoints;
			this.centroid.set_x(new_x);
			this.centroid.set_y(new_y);

		}
	}
}