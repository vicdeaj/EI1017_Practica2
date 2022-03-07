package Table;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    private static TableWithLabels test;
    private static TableWithLabels xtraTable;
    private static RowWithLabel row1;
    private static RowWithLabel row2;
    private static List<String> headers;

    @BeforeAll
    static void initAll(){
        test = new TableWithLabels();
        xtraTable = new TableWithLabels();
        row1 = new RowWithLabel();
        row2 = new RowWithLabel();
        headers = new ArrayList<>();

        headers.add("Header");
        headers.add("Label");

        row1.add(2.0);
        row1.addLabel("Alfa");

        row2.add(2.0);
        row2.addLabel("Beta");

        test.addAllHeaders(headers);
        test.addRow(row1);

        xtraTable.addAllHeaders(headers);
        xtraTable.addRow(row2);

    }

    /*
    @Test
    void testEquals() {

        assertEquals(false, test.equals(xtraTable));
    }

     */
}