package Operations;

import java.util.List;

public class Operations {

    public static double sum(List<Double> list) { //Sumatory
        double sum = 0;

        for (double i : list)
            sum = sum + i;

        return sum;
    }

    public static double mean(List<Double> numList){//Calculates the mean of the elements in a list

        return Operations.sum(numList)/numList.size();

    }
}
