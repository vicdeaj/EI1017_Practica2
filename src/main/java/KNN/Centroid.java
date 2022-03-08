package KNN;

import java.util.ArrayList;
import java.util.List;

public class Centroid {

    private List<Double> data;

    public Centroid(){
        data = new ArrayList<>();
    }

    public Centroid(List<Double> data){
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
