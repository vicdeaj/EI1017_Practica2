package LinealRegression;

import CSV.CSV;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import Table.*;

class LinealRegressionTest {

    private static CSV csv;

    private static LinealRegression lr;

    private static Table testTable;

    @BeforeAll
    static void initAll() {

        csv = new CSV();

        try{
            lr = new LinealRegression();
            testTable = csv.readTable("src/Files/miles_dollars.csv");

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }



    @DisplayName("Train test")
    @Test
    void train() {

        lr.train(testTable);

        assertEquals(1.255, lr.getAlfa(), 0.001);
        assertEquals(274.85, lr.getBeta(), 0.001);
    }

    @DisplayName("Estimate test")
    @Test
    void estimate() {

        lr.setAlfa(1.255);
        lr.setBeta(274.85);

        assertEquals(277.36,lr.estimate(2.0),0.1);



    }
}