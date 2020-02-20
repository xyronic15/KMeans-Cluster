public class Points2D{

    private float coord_x;//x coordinate
    private float coord_y;//y coordinate
    private int clusterId;//cluster point is assigned to

    public Points2D(float x,float y){//creates point object
        this.set_x(x);
        this.set_y(y);
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

}