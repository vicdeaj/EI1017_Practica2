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


    @Override
    public void train(Table data) {
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

        // para cada grupo calculamos un centroide
        for (int grupo = 0; grupo < groups.size(); grupo++) {
            List<Double> newCoordenates = new ArrayList<>();
            // para cada coordenada sumamos los valores para cada campo
            for (int i = 0; i < numberOfCoordenates; i++) {
                newCoordenates.add(Operations.sum(groups.get(grupo).getColumnAt(i))); //Añade una coordenada
            }

            //dividimos por el número de elementos que hay en la tabla
            for (int i = 0; i < numberOfCoordenates; i++) {
                newCoordenates.set(i,newCoordenates.get(i)/groups.get(grupo).getSize());
            }

            for (int i = 0; i < numberOfCoordenates; i++) {
                centroids.get(grupo).set(i, newCoordenates.get(i));
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
