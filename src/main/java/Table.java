import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table {
    private List<String> headers;
    private List<Row> rows;

    public Table(){
        rows = new ArrayList<>();
        headers = new ArrayList<>();
    }

    public Row getRowAt(int i){
        return rows.get(i);
    }

    public List<Double> getColumnAt(int i){
        List<Double> ret = new ArrayList<>();
        for (Row r : rows) {
            ret.add(r.getElement(i));
        }
        return ret;
    }
    public void addHeader(String element){
        headers.add(element);
    }
    public void addAllHeader(List<String> h){
        headers.addAll(h);
    }

    public void addRow(Row element){
        rows.add(element);
    }

}
