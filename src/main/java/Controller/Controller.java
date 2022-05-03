package Controller;

import Interfaces.Distance;
import Interfaces.DistanceType;
import Model.*;
import Operations.EuclideanDistance;
import Operations.ManhattanDistance;
import View.View;

import java.security.InvalidParameterException;

public class Controller implements ControllerInterface{

    private ModelInterface model;
    private View view;

    @Override
    public void loadFile(String path) {

        model.loadData(path);
        model.train();
    }

    @Override
    public void estimate() {

    }

    @Override
    public void selectDistanceType(DistanceType t) {

        switch(t){
            case EUCLIDEAN:
                model.setDistanceType(new EuclideanDistance());
                break;
            case MANHATTAN:
                model.setDistanceType(new ManhattanDistance());
                break;
            default:
                throw new InvalidParameterException();

        }

        model.train();

    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(ModelInterface model) {
        this.model = model;
    }


}
