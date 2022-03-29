package KNN;

import java.lang.Math;


import Interfaces.Algorithm;
import Interfaces.Distance;
import Interfaces.DistanceClient;
import Table.RowWithLabel;
import Table.TableWithLabels;

import java.util.List;
import Operations.*;

public class KNN implements Algorithm<TableWithLabels, List<Double>, String>{

    private TableWithLabels data;

    public KNN(){
        data = new TableWithLabels();
    }

    public void train(TableWithLabels table){
        data = table;
    }

    public String estimate(List<Double> sample){
        String labelMin="";
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; i < data.getSize(); i++){

            RowWithLabel row = data.getRowAt(i);
            double distance = Operations.euclideanDistance(row.getData(), sample);

            if (distance < minDistance){

                minDistance = distance;
                labelMin = row.getLabel();

            }
        }

        return labelMin;
    }
}
