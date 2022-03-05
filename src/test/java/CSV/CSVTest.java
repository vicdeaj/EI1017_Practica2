package CSV;

import Table.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    private static CSV csv;

    private static String pathNoLabel = "Files/tableNoLabel.csv";
    private static String pathLabel = "Files/tableLabels.csv";

    private static Table tableNoLabel_Test = new Table();
    private static TableWithLabels tableLabel_Test = new TableWithLabels();

    private static List<String> headNL = new ArrayList<>();
    private static List<String> headL = new ArrayList<>();

    private static Row r1 = new Row();
    private static Row r2 = new Row();

    private static RowWithLabel rL1 = new RowWithLabel();
    private static RowWithLabel rL2 = new RowWithLabel();


    @BeforeAll
    static void initAll(){

        csv = new CSV();

        headNL.add("Unidades");
        headNL.add("Precio");

        headL.add("Unidades");
        headL.add("Precio");
        headL.add("Etiqueta");

        r1.add(7.0);
        r1.add(24.5);
        r2.add(1.0);
        r2.add(273.7);

        rL1.add(7.0);
        rL1.add(24.5);
        rL1.addLabel("A");

        rL2.add(1.0);
        rL2.add(273.7);
        rL2.addLabel("B");

        tableNoLabel_Test.addAllHeaders(headNL);
        tableNoLabel_Test.addRow(r1);
        tableNoLabel_Test.addRow(r2);

        tableLabel_Test.addAllHeaders(headL);
        tableLabel_Test.addRow(rL1);
        tableLabel_Test.addRow(rL2);



    }

    //WIP
    @Test
    void readTable() throws FileNotFoundException {

        Table t = csv.readTable(pathNoLabel);
        assertEquals(true, t.equals(tableNoLabel_Test));
    }

    @Test
    void readTableWithLabels() {
    }
}