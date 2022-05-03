package Controller;

import Model.*;
import View.*;

public interface ControllerInterface {
    void loadFile(String path);
    void selectDistanceType();
    void estimate();
    void setView(View view);
    void setModel(ModelInterface model);
}
