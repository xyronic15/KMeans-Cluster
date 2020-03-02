import java.lang.Math;

public class Points2D{

    private float coord_x;//x coordinate
    private float coord_y;//y coordinate
    private int clusterId;//cluster point is assigned to
    private int pointId;//id of the current point
  
    // This is meant to initialize centroids as we don't want centroids to have pointId
    public Points2D(float x,float y){//creates point object
        this.pointId = -1;
    	this.set_x(x);
        this.set_y(y);
    }
    
    public Points2D(int pointId, float x,float y){//creates point object
        this.pointId = pointId;
    	this.set_x(x);
        this.set_y(y);
    }
    
    public int get_id(){ //gets point id
    	return this.pointId;
    }
    
    public void set_x(float x){//sets x alone
        coord_x=x;
    }

    public void set_y(float y){//sets y alone
        coord_y=y;
    }

    public float get_x(){//gets x of point
        return this.coord_x;
    }

    public float get_y(){//gets y of point
        return this.coord_y;
    }

    public void setCluster(int c){//assigns 
        clusterId=c;
    }

    public int getCluster(){
        return clusterId;
    }

    public float getDistance(Points2D centroid){
        return (float)Math.sqrt(Math.pow(centroid.get_y() - this.get_y(), 2) + Math.pow(centroid.get_x() - this.get_x(), 2));
    }
    
    public String toString()
    {
		return "(" + this.get_x() + ", " + this.get_y() + ")";
    	
    }
    
    public boolean equals(Points2D p)
    {
    	if(this.get_x() == p.get_x() && this.get_y() == p.get_y())
    	{
    		return true;
    	}
    	else
    	{
        	return false;
    	}
    }

}