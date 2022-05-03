package Model;

import Controller.ControllerInterface;
import Interfaces.Distance;
import View.View;

import java.util.List;

public interface ModelInterface {

    void loadData(String path);
    void train();

    void estimateValues();
    void setView(View view);
    void setController(ControllerInterface controller);
    List<String> getTableHeaders();
    int getNumberOfClusters();
    void getData(String labelX , String labelY);

    public void setDistanceType(Distance t);

}
