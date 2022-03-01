package KNN;

import CSV.CSV;
import Table.TableWithLabels;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {

    private static List<Double> sample1;
    private static String label1;
    private static KNN knn;
    private static TableWithLabels dataTable;

    @BeforeAll
    static void initAll() throws FileNotFoundException{

        dataTable = CSV.readTableWithLabels("Files/iris.csv");




        knn = new KNN();
        knn.train(dataTable);
        sample1 = Arrays.asList(4.7,3.2,1.3,0.2);
        label1 = "Iris-setosa";

    }

    @Test
    @DisplayName("knn.estimate Test")
    void estimate() {
        assertEquals(label1, knn.estimate(sample1));

    }
}