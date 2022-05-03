package Kmeans;
import Interfaces.Distance;
import Interfaces.DistanceClient;
import Table.Table;
import Table.Row;
import Interfaces.Algorithm;

import java.util.*;
import Operations.*;

public class Kmeans implements Algorithm<Table, List<Double>, String>, DistanceClient {

    private int numberClusters;
    private List<List<Double>> centroids;
    private int iterations;
    private long seed;
    private Distance distanceType;

    public Kmeans(int numberClusters, int iterations, long seed, Distance type){

        this.numberClusters = numberClusters;
        this.iterations =  iterations;
        this.seed = seed;
        centroids = new ArrayList<>();
        distanceType = type;
    }
    public List<List<Double>> getCentroids(){
        return centroids;
    }


    @Override
    public void train(Table data) {
        if (numberClusters > data.getSize()){
            throw new IllegalStateException();
        }

        centroids = createRandomCentroids(data);

        for (int i = 0; i < iterations; i++) {
            List<Table> clusters = new ArrayList<>();
            for (int j = 0; j < numberClusters; j++) {
                Table c = new Table();
                clusters.add(c);
            }

            classify(clusters, data); //Fills up the clusters with their corresponding rows according to its centroid
            recalculateCentroids(clusters);//Recalculates the centroids on each group
        }

    }

    @Override
    public String estimate(List<Double> sample) {

        int nCent = closestCenterIndex(sample, centroids);
        return String.format("Cluster %d",nCent);
    }


    protected void classify(List<Table> cluster, Table data){ //Fills up the clusters with their corresponding rows according to its centroid
        for (Row row : data.getRows()){
            int i = closestCenterIndex(row.getData(), centroids);
            cluster.get(i).addRow(row); //Adds to its corresponding cluster
        }

    }

    protected void recalculateCentroids(List<Table> groups){
        int numberOfCoordenates = centroids.get(0).size();

        // we calculate a centroid for each group
        for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
            List<Double> newCoordenate = new ArrayList<>();

            Table group = groups.get(groupIndex);
            List<Double> centroid = centroids.get(groupIndex);

            //We proceed on calculating the mean for each coordenate and then updating the centroid, on ecoordenate at a time
            for (int i = 0; i < numberOfCoordenates; i++) {

                newCoordenate.add(Operations.mean(group.getColumnAt(i)));//This needs to be re-looked. Works for now.
                centroid.set(i, newCoordenate.get(i));
            }

        }

    }

    protected List<List<Double>> createRandomCentroids(Table table){//Creates the first numberClusters centroids randomly (Launch exception if numberClusters > Table.size)
        Set<Integer> indexes = new HashSet();
        List<List<Double>> ret = new LinkedList<>();
        int tableSize = table.getSize();

        for (int i = 0; i < numberClusters; i++) { //For each cluster

            Random random = new Random(seed);
            int index = Math.abs(random.nextInt() % tableSize); //Designate a starting index
            while (indexes.contains(index)) {//If that index is on a set of indexes that have already been asigned to a centroid keep looking for an index
                index = Math.abs(random.nextInt() % tableSize);
            }
            indexes.add(index);

            List<Double> centroid =  new ArrayList<>(table.getRowAt(index).getData());
            ret.add(centroid);
        }
        return ret;
    }

    protected int closestCenterIndex(List<Double> data, List<List<Double>> centroidList){ //Calculates which is the closest centroid among a centroid list relative to a designated point and returns its index from within said list
        double minDistance = Double.MAX_VALUE;
        int iMin = 0;


        //Check where will a certain row be placed according to the distance relative to each centroid
        for (int i = 0; i < centroidList.size(); i++){

            double distance = distanceType.calculateDistance(data, centroidList.get(i));
            if (distance < minDistance){
                minDistance = distance;
                iMin = i;
            }
        }
        return iMin;
    }

    public void setDistance(Distance distance){
        distanceType = distance;
    }
}
