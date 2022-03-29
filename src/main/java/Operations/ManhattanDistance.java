package Operations;

import Interfaces.Distance;

import java.util.List;

import static java.lang.Math.abs;

public class ManhattanDistance implements Distance {

    @Override
    public double calculateDistance(List<Double> pointA, List<Double> pointB) {
        if(pointA.size() != pointB.size() || pointB.isEmpty()){ //Should they be of equal size it is only necessary to check 1 element to see if they are empty lists
            throw new IllegalStateException();
        }

        double sumatorio = 0.0;
        for (int i = 0; i < pointB.size();i++){
            sumatorio += abs(pointA.get(i) - pointB.get(i));
        }
        return sumatorio;
    }
}
