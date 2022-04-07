package Kmeans;

import Csv.Csv;
import Operations.*;
import Table.Table;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {

    private static Table dataTable;
    private static Kmeans kmeans;
    private static Kmeans failure;
    private static List<List<Double>> centroids;

    private static List<Double> c1;
    private static List<Double> c2;
    private static List<Double> c3;

    private static List<Double> nums;
    private static List<Double> nums2;

    @BeforeAll
    static void initAll() throws FileNotFoundException{
        nums = new ArrayList<>();
        nums.add(1.00);
        nums.add(2.00);
        nums.add(3.00);
        nums.add(4.00);

        nums2 = new ArrayList<>();

        nums2.add(5.00);
        nums2.add(6.00);
        nums2.add(7.00);
        nums2.add(8.00);


        // crear tabla de pruebas
        Csv reader = new Csv();
        dataTable = (Table) reader.readTableWithLabels("src/Files/irisTest.csv");

        c1 = Arrays.asList(5.1,3.5,1.4,0.2);
        c2 = Arrays.asList(4.7,3.2,1.3,0.2);
        c3 = Arrays.asList(7.0,3.2,4.7,1.4);

        failure = new Kmeans(1000, 10, 1, new EuclideanDistance());
    }

    @BeforeEach
    void init(){
        // crear objetos
        kmeans= new Kmeans(3,10,1, new EuclideanDistance());
    }

    @Test
    void closestCenterIndex(){

        List<Double> point = new ArrayList<>();
        point.add(1.00);
        point.add(1.00);
        point.add(1.00);
        point.add(1.00);


        List<List<Double>> centroids = new ArrayList<>();

        centroids.add(nums);
        centroids.add(nums2);

        assertEquals(0, kmeans.closestCenterIndex(point, centroids));
    }

    @Test
    void createRandomCentroids() {

        centroids = kmeans.createRandomCentroids(dataTable);

        assertEquals(c1, centroids.get(0));
        assertEquals(c2, centroids.get(1));
        assertEquals(c3, centroids.get(2));
    }

    @Test
    void estimate() {

        assertThrows(IllegalStateException.class, () -> failure.train(dataTable));

        kmeans.train(dataTable);
        assertEquals("Cluster 0",kmeans.estimate(c1));
        assertEquals("Cluster 1", kmeans.estimate(c2));
        assertEquals("Cluster 2", kmeans.estimate(c3));

        kmeans.setDistance(new ManhattanDistance());

        kmeans.train(dataTable);

        assertNotEquals("Cluster0",kmeans.estimate(c1));
        assertNotEquals("Cluster1",kmeans.estimate(c2));
        assertNotEquals("Cluster2",kmeans.estimate(c3));

    }


}