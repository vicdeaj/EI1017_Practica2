package Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableWithLabels extends Table {
    public TableWithLabels(){
        super();
    }

    @Override
    public RowWithLabel getRowAt(int index){
        return (RowWithLabel) rows.get(index);
    }


    public void addRow(RowWithLabel element){
        super.addRow(element);
    }

    public List<String> getNumberLabels(){//Returns every label except the last one, which corresponds to the "class" label
        //List<String> res =  super.getHeaders();
        List<String> res = new ArrayList<>(super.getHeaders());
        res.remove(res.size() - 1);
        return res;
    }
}
