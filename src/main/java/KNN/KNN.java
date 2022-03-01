package KNN;

import java.lang.Math;

import Table.Row;
import Table.RowWithLabel;
import Table.TableWithLabels;

import java.util.List;

public class KNN {

    private TableWithLabels data;

    public KNN(){
        data = new TableWithLabels();
    }

    public void train(TableWithLabels table){
        data = table;
    }

    public String estimate(List<Double> sample){
        String labelMin="";
        double euclideanMin = Double.MAX_VALUE;

        for(int i = 0; i < data.getSize(); i++){

            RowWithLabel row = data.getRowAt(i);
            double metric = euclideanMetric(row, sample);

            if (metric < euclideanMin){

                euclideanMin = metric;
                labelMin = row.getLabel();

            }
        }

        return labelMin;
    }

    private Double euclideanMetric(RowWithLabel row, List<Double> sample){
        Double sumatorio = 0.0;
        for (int i = 0; i < sample.size();i++){
            sumatorio += Math.pow(sample.get(i) - row.getElement(i), 2);
        }
        return Math.sqrt(sumatorio);
    }

}
