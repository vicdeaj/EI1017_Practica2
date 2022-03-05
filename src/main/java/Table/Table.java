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

    public List<Row> getRows(){return rows;}

    public void addRow(Row row){
        rows.add(row);
    }

    public boolean equals(Table table){
        if (!this.sameHeaders(table.getHeaders()) || !this.sameSize(table.getSize())){
            return false;
        }

        for (int i = 0; i < rows.size(); i++) {
            if (!rows.get(i).equals(table.getRows().get(i))){
                return false;
            }
        }

        return true;
    }

    protected boolean sameHeaders(List<String> otherHeaders){

        return headers.equals(otherHeaders);
    }

    protected boolean sameSize(int otherSize){

        return this.getSize() == otherSize;
    }

    public int getSize(){
        return rows.size();
    }

}
