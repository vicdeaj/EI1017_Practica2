package Table;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private static Table test;
    private static Row testRow1;
    private static Row testRow2;
    private static List<String> headers;

    private static final double n1 = 5.0;
    private static final double n2 = 3.3;
    private static final double n3 = 6.74;

    @BeforeAll
    static void initAll(){
        test = new Table();
        testRow1 = new Row();
        testRow2 = new Row();

        headers = new ArrayList<>();

        headers.add("Header1");
        headers.add("Header2");

        testRow1.add(n1);
        testRow1.add(n2);
        testRow1.add(n3);

        testRow2.add(n2);
        testRow2.add(n1);
        testRow2.add(n3);

        test.addAllHeaders(headers);
    }

    @BeforeEach
    void initTest(){
        test = new Table();
        test.addRow(testRow1);
        test.addRow(testRow2);
    }


    @Test
    void getRowAt() {

        assertEquals(true, test.getRowAt(0).equals(testRow1));
        assertEquals(true, test.getRowAt(1).equals(testRow2));
    }

    @Test
    void getColumnAt() {
        List<Double> col1 = new ArrayList<>();
        List<Double> col2 = new ArrayList<>();
        List<Double> col3 = new ArrayList<>();

        col1.add(n1);
        col1.add(n2);

        col2.add(n2);
        col2.add(n1);

        col3.add(n3);
        col3.add(n3);

        assertEquals(col1, test.getColumnAt(0));
        assertEquals(col2, test.getColumnAt(1));
        assertEquals(col3, test.getColumnAt(2));
    }

    @Test
    void addAllHeaders() {

        assertEquals(true, test.getHeaders().containsAll(headers));

    }

    @Test
    void addRow() {

        Row xtraRow = new Row();

        xtraRow.add(n1);
        xtraRow.add(n1);

        test.addRow(xtraRow);

        assertEquals(true, test.getRowAt(2).equals(xtraRow));
    }

    @Test
    void equals(){
        Table xtraTable =new Table();
        xtraTable.addRow(testRow1);

        assertEquals(false, test.equals(xtraTable));

        xtraTable.addRow(testRow2);

        assertEquals(true, test.equals(xtraTable));

        xtraTable.addRow(testRow1);
        xtraTable.addRow(testRow2);

        assertEquals(false, test.equals(xtraTable));

    }
}