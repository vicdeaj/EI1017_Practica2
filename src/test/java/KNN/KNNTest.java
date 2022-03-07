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

    private static CSV csv;

    private static List<Double> sample1;
    private static List<Double> sample2;
    private static List<Double> sample3;

    private static String label1;
    private static String label2;
    private static String label3;

    private static KNN knn;
    private static TableWithLabels dataTable;


    @BeforeAll
    static void initAll() throws FileNotFoundException{

        csv = new CSV();

        dataTable = csv.readTableWithLabels("src/Files/iris.csv");

        knn = new KNN();
        knn.train(dataTable);

        sample1 = Arrays.asList(4.7,3.2,1.3,0.2);
        sample2 = Arrays.asList(5.0, 2.3, 3.3, 1.0);
        sample3 = Arrays.asList(5.8, 2.8, 5.1, 2.4);

        label1 = "Iris-setosa";
        label2 = "Iris-versicolor";
        label3 = "Iris-virginica";

    }

    //Solo se va a testear el metodo estimate, pues el metodo train es leer un fichero csv y eso se ha testeado en pruebas anteriores

    @Test
    @DisplayName("knn.estimate Test")
    void estimate() {

        assertEquals(label1, knn.estimate(sample1));
        assertEquals(label2, knn.estimate(sample2));
        assertEquals(label3, knn.estimate(sample3));


    }
}