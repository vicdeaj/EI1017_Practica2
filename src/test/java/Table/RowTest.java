package Table;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Finishings;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    private static List<Double> resList;
    private static double n1 = 5.0;
    private static double n2 = 3.3;

    @BeforeAll
    static void initAll(){
        resList = new ArrayList<>();
        resList.add(n1);
        resList.add(n2);
    }


    @DisplayName("row.add() + row.getRow test")
    @Test
    void add() {
        Row test = new Row();

        test.add(n1);
        test.add(n2);

        assertEquals(true, test.getRow().equals(resList));


    }

        @DisplayName("row.getElement() test")
    @Test
    void getElement() {

        Row test = new Row();
        test.add(n1);
        assertEquals(5.0, test.getElement(0));
    }
}