package Operations;

import Interfaces.Distance;
import Interfaces.DistanceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Interfaces.DistanceType.EUCLIDEAN;
import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {

    private static List<Double> listA, listB;
    private static DistanceFactory factory = new DistanceFactory();

    @BeforeAll
    static void initAll(){

        listA = new ArrayList<>();
        listB = new ArrayList<>();
    }

    @Test
    void calculateDistance() {

        Distance test = factory.getDistance(EUCLIDEAN);

        assertThrows(IllegalStateException.class, () -> test.calculateDistance(listA, listB));

        listA.add(3.0);
        listA.add(10.0);
        listB.add(27.0);

        assertThrows(IllegalStateException.class, () -> test.calculateDistance(listA, listB));

        listB.add(6.0);

        assertEquals(24.331, test.calculateDistance(listA, listB), 0.0001);
    }
}