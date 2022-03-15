package KMEANS;
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

        for (int i = 0; i < iterations; i++) {
            List<Table> groups = new ArrayList<>(numberClusters);

            classify(groups, data); //Fills up the groups with their corresponding rows according to its centroid
            recalcularCentroides(groups);
        }

    }
    private Double euclideanMetric(List<Double> data, Centroid centroide){
        Double sumatorio = 0.0;
        for (int i = 0; i < centroide.getSize();i++){
            sumatorio += Math.pow(centroide.getData().get(i) - data.get(i), 2);
        }
        return Math.sqrt(sumatorio);
    }


    @Override
    public String estimate(List<Double> sample) {

        int nCent = minDistance(sample);
        return String.format("Cluster %d",nCent);
    }


    private int minDistance(List<Double> data){
        double minDistance = Double.MAX_VALUE;
        int iMin = 0;

        //Check where will a certain row be placed according to the Euclidean distance relative to each centroid
        for (int i = 0; i < centroids.size(); i++){

            double distance = euclideanMetric(data, centroids.get(i));
            if (distance < minDistance){
                minDistance = distance;
                iMin = i;
            }
        }
        return iMin;
    }

    private void classify(List<Table> groups, Table data){ //Fills up the groups with their corresponding rows according to its centroid
        for (Row row : data.getRows()){
            int i = minDistance(row.getData());
            groups.get(i).addRow(row); //Adds to its corresponding group
        }

    }

    private void recalcularCentroides(List<Table> groups){
        int numberOfCoordenates = centroids.get(0).getSize();

        // para cada grupo calculamos un centroide
        for (int grupo = 0; grupo < groups.size(); grupo++) {
            List<Double> newCoordenates = new ArrayList<>(numberOfCoordenates);
            // para cada coordenada sumamos los valores para cada campo
            for (int i = 0; i < numberOfCoordenates; i++) {
                newCoordenates.set(i,sum(groups.get(grupo).getColumnAt(i)));
            }

            //dividimos por el número de elementos que hay en la tabla
            for (int i = 0; i < numberOfCoordenates; i++) {
                newCoordenates.set(i,newCoordenates.get(i)/groups.get(grupo).getSize());
            }
            centroids.get(grupo).setData(newCoordenates);

        }

    }

    private double sum(List<Double> list) {
        double sum = 0;

        for (double i : list)
            sum = sum + i;

        return sum;
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
