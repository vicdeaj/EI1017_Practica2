package Controller;

import Model.*;
import View.View;

public class Controller implements ControllerInterface{

    private ModelInterface model;
    private View view;

    @Override
    public void loadFile(String path) {

        model.loadData(path);
    }

    @Override
    public void estimate() {

    }

    @Override
    public void selectDistanceType() {

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
