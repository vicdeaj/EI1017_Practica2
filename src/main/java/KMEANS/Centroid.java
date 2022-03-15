package KMEANS;

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
    public int getSize(){
        return data.size();
    }

    public void setData(List<Double> newData) {
        data = new ArrayList<>(newData);
    }
}
