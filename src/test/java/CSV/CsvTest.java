package CSV;

import Table.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvTest {

    private static Csv csv;

    private static String pathNoLabel = "src/Files/tableNoLabel.csv";
    private static String pathLabel = "src/Files/tableLabels.csv";

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

        csv = new Csv();

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

    //WIP -> Ignore these tests for now. The validation of the CSV class is reflected in the Table and TableWithLabels test
    @Test
    void readTable() throws FileNotFoundException {

        Table t = csv.readTable(pathNoLabel);


        assertEquals(true,  tableNoLabel_Test.getHeaders().equals(t.getHeaders()));
        assertEquals(true, tableNoLabel_Test.getSize() == t.getSize());

        for (int i = 0; i < tableNoLabel_Test.getSize(); i++ ){

            Row tRow = t.getRowAt(i);
            Row testRow = tableNoLabel_Test.getRowAt(i);

            assertEquals(true, tRow.getData().equals(testRow.getData()));
        }
    }

    @Test
    void readTableWithLabels() throws FileNotFoundException {

        TableWithLabels t = csv.readTableWithLabels(pathLabel);

        assertEquals(true,  tableLabel_Test.getHeaders().equals(t.getHeaders()));
        assertEquals(true, tableLabel_Test.getSize() == t.getSize());

        for (int i = 0; i < tableLabel_Test.getSize(); i++ ){

            RowWithLabel tRow = t.getRowAt(i);
            RowWithLabel testRow = t.getRowAt(i);

            assertEquals(true, tRow.getLabel().equals(testRow.getLabel()));
            assertEquals(true, tRow.getData().equals(testRow.getData()));
        }


    }
}