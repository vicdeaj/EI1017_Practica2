import CSV.CSV;
import Table.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TableWithLabels t = CSV.readTableWithLabels("Files/iris.csv");
        Table tNL = CSV.readTable("Files/miles_dollars.csv");

        System.out.println("asdf");
    }
}
