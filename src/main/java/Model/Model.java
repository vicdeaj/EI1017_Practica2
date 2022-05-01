package Model;

import Controller.ControllerInterface;
import Interfaces.DistanceType;
import Knn.Knn;
import Operations.EuclideanDistance;
import Table.TableWithLabels;
import View.View;
import Csv.*;

import java.io.FileNotFoundException;

public class Model implements ModelInterface{

    private View view;
    private ControllerInterface controller;
    private Csv reader = new Csv();
    private TableWithLabels data;
    private Knn knn = new Knn(new EuclideanDistance());

    @Override
    public void loadData(String path){
        try {
            data = reader.readTableWithLabels(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        knn.train(data);
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
}
