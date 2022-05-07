package Model;

import Controller.ControllerInterface;
import Interfaces.Distance;
import Kmeans.Kmeans;
import Knn.Knn;
import Operations.EuclideanDistance;
import Table.RowWithLabel;
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
    private Distance distanceType;
    private Knn knn;
    private int iX;
    private int iY;

    public Model(){
        distanceType = new EuclideanDistance();
        knn = new Knn(distanceType);

    }

    @Override
    public void loadData(String path){
        try {
            data = reader.readTableWithLabels(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void train(){
        knn.train(data);
    }

    @Override
    public String estimate(List<Double> coordinates) {
        return knn.estimate(coordinates);
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
    public void getData(String labelX , String labelY){

        iX = data.getNumberLabels().indexOf(labelX);
        iY = data.getNumberLabels().indexOf(labelY);


       for (int i = 0; i < data.getSize(); i++) {
           RowWithLabel row = data.getRowAt(i);
           view.fillSeries(row.getElement(iX), row.getElement(iY), row.getLabel());
       }

       view.insertSeries();


   }

   public void setDistanceType(Distance t){
       distanceType = t;
       knn = new Knn(distanceType);
   }

   @Override
   public int getiX(){
        return iX;
   }
   @Override
   public int getiY(){
        return iY;
   }

}
