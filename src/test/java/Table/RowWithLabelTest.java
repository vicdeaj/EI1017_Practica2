package Table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowWithLabelTest {

    @DisplayName("row.addLabel() + row.getLabel() test")
    @Test
    void addLabel() {

        RowWithLabel row = new RowWithLabel();
        row.addLabel("Label");

        assertEquals("Label", row.getLabel());

    }
}