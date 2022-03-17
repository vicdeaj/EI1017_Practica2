package Operations;

import java.util.List;
import java.util.Random;

public class Operations {

    public static double sum(List<Double> list) { //Sumatory
        double sum = 0;

        for (double i : list)
            sum = sum + i;

        return sum;
    }


    public static int closestCenterIndex(List<Double> data, List<List<Double>> centroidList){ //Calculates which is the closest centroid among a centroid list relative to a designated point and returns its index from within said list
        double minDistance = Double.MAX_VALUE;
        int iMin = 0;

        //Check where will a certain row be placed according to the Euclidean distance relative to each centroid
        for (int i = 0; i < centroidList.size(); i++){

            double distance = euclideanDistance(data, centroidList.get(i));
            if (distance < minDistance){
                minDistance = distance;
                iMin = i;
            }
        }
        return iMin;
    }

    public static double euclideanDistance(List<Double> data, List<Double> centroid){ //Calculates the euclidean distance between 2 of the same size
        Double sumatorio = 0.0;
        for (int i = 0; i < centroid.size();i++){
            sumatorio += Math.pow(centroid.get(i) - data.get(i), 2);
        }
        return Math.sqrt(sumatorio);
    }
}
