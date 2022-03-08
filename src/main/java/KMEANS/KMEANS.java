package KMEANS;
import KNN.Centroid;
import Table.Table;
import Table.Row;
import Interfaces.Algorithm;

import java.util.*;

public class KMEANS implements Algorithm<Table, List<Double>, String>{

    private int numberClusters;
    private List<Centroid> centroids;
    private int iterations;
    private long seed;

    public KMEANS(int numberClusters, int iterations, long seed){

        this.numberClusters = numberClusters;
        this.iterations =  iterations;
        this.seed = seed;
        centroids = new ArrayList<>();
    }


    @Override
    public void train(Table data) {
        centroids = createRandomCentroids(data);


    }

    @Override
    public String estimate(List<Double> sample) {

        return null;
    }


    private List<Centroid> createRandomCentroids(Table table){//Creates the first numberClusters centroids randomly
        Set<Integer> indexes = new HashSet();
        List<Centroid> ret = new LinkedList<>();
        int tableSize = table.getSize();

        for (int i = 0; i < numberClusters; i++) {
            int index = getRandomIndex(tableSize);
            while (indexes.contains(index)) {
                index = getRandomIndex(tableSize);
            }
            indexes.add(index);

            Centroid centroid =  new Centroid(table.getRowAt(index).getData());
            ret.add(centroid);
        }
        return ret;
    }

    private int getRandomIndex(int tableSize){ //Obtains a random number to be used as an index
        Random random = new Random(seed);
        return random.nextInt() % tableSize;
    }
}
