package KMEANS;
import Table.Table;
import Table.Row;
import Interfaces.Algorithm;

import java.util.*;
import Operations.Operations;

public class KMEANS implements Algorithm<Table, List<Double>, String>{

    private int numberClusters;
    private List<List<Double>> centroids;
    private int iterations;
    private long seed;

    public KMEANS(int numberClusters, int iterations, long seed){

        this.numberClusters = numberClusters;
        this.iterations =  iterations;
        this.seed = seed;
        centroids = new ArrayList<>();
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
            recalcularCentroides(clusters);
        }

    }

    @Override
    public String estimate(List<Double> sample) {

        int nCent = Operations.closestCenterIndex(sample, centroids);
        return String.format("Cluster %d",nCent);
    }


    protected void classify(List<Table> cluster, Table data){ //Fills up the clusters with their corresponding rows according to its centroid
        for (Row row : data.getRows()){
            int i = Operations.closestCenterIndex(row.getData(), centroids);
            cluster.get(i).addRow(row); //Adds to its corresponding cluster
        }

    }

    protected void recalcularCentroides(List<Table> groups){
        int numberOfCoordenates = centroids.get(0).size();

        // we calculate a centroid for each group
        for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
            List<Double> newCoordenates = new ArrayList<>();

            Table group = groups.get(groupIndex);
            int groupSize = group.getSize();

            //We proceed on calculating the mean for each coordenate and then updating the centroid, on ecoordenate at a time
            for (int i = 0; i < numberOfCoordenates; i++) {
                newCoordenates.add(Operations.sum(group.getColumnAt(i))); //Sum of coordenates added to its corresponding position
                newCoordenates.set(i,newCoordenates.get(i)/groupSize);//Division to calculate the mean
                centroids.get(groupIndex).set(i, newCoordenates.get(i));
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
}
