package Table;

import java.util.ArrayList;
import java.util.List;

public class Table {
    protected List<String> headers;
    protected List<Row> rows;

    public Table(){
        rows = new ArrayList<>();
        headers = new ArrayList<>();
    }

    public Row getRowAt(int i){
        return rows.get(i);
    }

    public List<Double> getColumnAt(int i){
        List<Double> column = new ArrayList<>();
        for (Row r : rows) {
            column.add(r.getElement(i));
        }
        return column;
    }

    public void addAllHeaders(List<String> headsList){

        headers.addAll(headsList);

    }

    public List<String> getHeaders(){
        return headers;
    }

    public void addRow(Row row){
        rows.add(row);
    }

}
