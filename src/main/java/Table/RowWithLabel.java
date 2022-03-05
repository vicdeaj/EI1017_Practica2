package Table;

public class RowWithLabel extends Row {
    String label;
    public RowWithLabel(){
        super();
        label = "";
    }
    public void addLabel(String s){
        label = s;
    }
    public String getLabel(){
        return label;
    }

    public boolean equals(RowWithLabel row){

        if (!label.equals(row.getLabel())){
            return false;
        } else {
            return super.equals(row);
        }

    }
}
