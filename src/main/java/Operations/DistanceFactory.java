package Operations;
import Interfaces.DistanceType;
import Interfaces.Distance;

public class DistanceFactory implements Interfaces.Factory{

    public Distance getDistance(DistanceType distanceType){

        if (distanceType == DistanceType.EUCLIDEAN){
            return new EuclideanDistance();
        } else if (distanceType == DistanceType.MANHATTAN){
            return new ManhattanDistance();
        } else {
            throw new IllegalStateException();
        }
    }
}
