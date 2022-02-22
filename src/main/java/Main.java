import CSV.CSV;
import LinealRegression.LinealRegression;
import Table.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TableWithLabels t = CSV.readTableWithLabels("Files/iris.csv");
        Table tNL = CSV.readTable("Files/miles_dollars.csv");
        LinealRegression lr = new LinealRegression();
        lr.train(tNL);

        System.out.println("asdf");
    }
}
