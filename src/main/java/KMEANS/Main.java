package KMEANS;
import CSV.Csv;
import Operations.EuclideanDistance;
import Table.*;

import java.util.List;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Csv reader = new Csv();
        Table dataTable = (Table) reader.readTableWithLabels("src/Files/irisTest.csv");
        Kmeans kmeans = new Kmeans(3,10,1, new EuclideanDistance());

        List<List<Double>> centroids = kmeans.createRandomCentroids(dataTable);
        System.out.println(centroids.toString());
    }
}
