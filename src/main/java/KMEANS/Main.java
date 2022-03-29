package KMEANS;
import CSV.CSV;
import LinealRegression.LinealRegression;
import Operations.EuclideanDistance;
import Table.*;
import KMEANS.*;
import KNN.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CSV reader = new CSV();
        Table dataTable = (Table) reader.readTableWithLabels("src/Files/irisTest.csv");
        KMEANS kmeans = new KMEANS(3,10,1, new EuclideanDistance());

        List<List<Double>> centroids = kmeans.createRandomCentroids(dataTable);
        System.out.println(centroids.toString());
    }
}
