package Table;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Double> data;

    public Row() {
        data = new ArrayList<>();
    }

    public void add(Double element){
        data.add(element);
    }

    public Double getElement(int i){
        return data.get(i);
    }

    public List<Double> getRow(){
        return data;
    }

}

