package Model;

import Controller.ControllerInterface;
import View.View;

public interface ModelInterface {

    void loadData(String path);
    void estimateValues();
    void setView(View view);
    void setController(ControllerInterface controller);

}
