package Controller;

import Interfaces.DistanceType;
import Model.*;
import Interfaces.Distance;
import View.*;

public interface ControllerInterface {
    void loadFile(String path);
    void selectDistanceType(DistanceType t);
    void setView(View view);
    void setModel(ModelInterface model);
}
