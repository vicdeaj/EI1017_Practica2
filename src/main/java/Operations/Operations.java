package Operations;

import java.util.List;

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

    public static double euclideanDistance(List<Double> pointA, List<Double> pointB){ //Calculates the euclidean distance between 2 of the same size

        if(pointA.size() != pointB.size() || pointB.isEmpty()){ //Should they be of equal size it is only necessary to check 1 element to see if they are empty lists
            throw new IllegalStateException();
        }

        double sumatorio = 0.0;
        for (int i = 0; i < pointB.size();i++){
            sumatorio += Math.pow(pointB.get(i) - pointA.get(i), 2);
        }
        return Math.sqrt(sumatorio);
    }

    public static double mean(List<Double> numList){//Calculates the mean of the elements in a list

        return Operations.sum(numList)/numList.size();

    }
}
