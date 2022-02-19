import CSV.CSV;
import Table.TableWithLabels;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TableWithLabels t = CSV.readTableWithLabels("iris.csv");
        System.out.println("asdf");
    }
}
