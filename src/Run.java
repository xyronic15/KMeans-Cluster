import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Run {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		KMeans kmeans = new KMeans("input/points.txt", 4);
		kmeans.calculate();
		kmeans.outputCentroids("output/centroids.txt");
	}

}
