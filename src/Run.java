import java.io.IOException;

public class Run {

	public static void main(String[] args) throws NumberFormatException, IOException {
		KMeans k = new KMeans("input/points.txt", 5);
//		k.calculate();
	}

}
