package Controller;

import Model.*;
import View.*;

public interface ControllerInterface {
    void loadFile(String path);
    void selectX();
    void selectY();
    void selectDistanceType();
    void estimate();
    void setView(View view);
    void setModel(ModelInterface model);
}
