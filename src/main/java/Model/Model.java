package Model;

import Controller.ControllerInterface;
import Kmeans.Kmeans;
import Operations.EuclideanDistance;
import Table.TableWithLabels;
import View.View;
import Csv.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Model implements ModelInterface{

    private View view;
    private ControllerInterface controller;
    private Csv reader = new Csv();
    private TableWithLabels data;
    private List<String> clusterMap = new ArrayList<>();
    private Kmeans kmeans = new Kmeans(3, 3, 5, new EuclideanDistance());

    @Override
    public void loadData(String path){
        try {
            data = reader.readTableWithLabels(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        kmeans.train(data);

        for (int i = 0; i < data.getSize(); i++) {
            clusterMap.add(kmeans.estimate(data.getRowAt(i).getData()));
        }
    }

    @Override
    public void estimateValues() {

    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    @Override
    public List<String> getTableHeaders(){
        return data.getNumberLabels();
    }

    @Override
    public int getNumberOfClusters(){

        return kmeans.getCentroids().size();
    }

    @Override
    public void getData(String labelX , String labelY){
        int iX;
        int iY;

        iX = data.getNumberLabels().indexOf(labelX);
        iY = data.getNumberLabels().indexOf(labelY);

        List<Double> xCoordenates = data.getColumnAt(iX);
        List<Double> yCoordenates = data.getColumnAt(iY);

       for (int i = 0; i < data.getSize(); i++) {
           view.fillSeries(xCoordenates.get(i), yCoordenates.get(i), clusterMap.get(i));
       }

       view.insertSeries();


   }
}
