import java.util.*;

public class Cluster {

    int id;
    Points2D centroid;
    List<Integer> pointIds;

    public Cluster(int id, Points2D centroid){
        this.id = id;
        this.centroid = centroid;
        this.pointIds = new ArrayList<Integer>();
    }

    public int getId(){
        return id;
    }

    public void clear_pointsIds(){
        pointIds.clear();
    }

    public List<Integer> get_pointsIds(){
        return pointIds;
    }

    public Points2D getCentroid(){
        return centroid;
    }

    public void newCentroid(List<Points2D> p){
        if (this.pointIds.isEmpty()){
            return;
        } else {
            float sum_x = 0;
            float sum_y = 0;
            int numPoints = this.pointIds.size();

            for(Integer id : this.pointIds){
                sum_x += p.get(id).get_x();
                sum_y += p.get(id).get_y();
            }

            float new_x = sum_x / numPoints;
            float new_y = sum_y / numPoints;
            this.centroid.set_x(new_x);
            this.centroid.set_y(new_y);

        }
    }
}