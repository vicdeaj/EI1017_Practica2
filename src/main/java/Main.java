import CSV.CSV;
import Table.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //TableWithLabels t = CSV.readTableWithLabels("iris.csv");

        TableWithLabels t = CSV.readTableWithLabels("Files/tableLabels.csv");
        Table tNL = CSV.readTable("Files/tableNoLabel.csv");
        Table tNL2 = CSV.readTable("Files/tableNoLabel.csv");

        tNL.equals(tNL2);
        System.out.println("asdf");
    }
}
