package Operations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    private static List<Double> nums;

    private static List<Double> nums2;

    @BeforeAll

    public static void initAll(){
        nums = new ArrayList<>();
        nums.add(1.00);
        nums.add(2.00);
        nums.add(3.00);
        nums.add(4.00);

        nums2 = new ArrayList<>();

        nums2.add(5.00);
        nums2.add(6.00);
        nums2.add(7.00);
        nums2.add(8.00);

    }

    @Test
    void sum() {
        assertEquals(10.00, Operations.sum(nums));
        assertEquals(26.00, Operations.sum(nums2));
    }

/*
    @Test
    void euclideanDistance() {

        List<Double> empty = new ArrayList<>();

        assertThrows(IllegalStateException.class, () -> Operations.euclideanDistance(empty, nums));
        assertEquals(8.00, Operations.euclideanDistance(nums, nums2));
    }
*/
    @Test
    void mean(){

        assertEquals(2.5, Operations.mean(nums));
        assertEquals(6.5, Operations.mean(nums2));
    }
}