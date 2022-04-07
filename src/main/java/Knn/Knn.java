package Knn;


import Interfaces.Algorithm;
import Interfaces.Distance;
import Interfaces.DistanceClient;
import Table.RowWithLabel;
import Table.TableWithLabels;

import java.util.List;

public class Knn implements Algorithm<TableWithLabels, List<Double>, String>, DistanceClient {

    private TableWithLabels data;
    private Distance distanceType;

    public Knn(Distance type){
        data = new TableWithLabels();
        distanceType = type;

    }

    public void train(TableWithLabels table){
        data = table;
    }

    public String estimate(List<Double> sample){
        String labelMin="";
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; i < data.getSize(); i++){

            RowWithLabel row = data.getRowAt(i);
            double distance = distanceType.calculateDistance(row.getData(), sample);

            if (distance < minDistance){

                minDistance = distance;
                labelMin = row.getLabel();

            }
        }

        return labelMin;
    }

    public void setDistance(Distance distance){
        this.distanceType = distance;
    }
}
