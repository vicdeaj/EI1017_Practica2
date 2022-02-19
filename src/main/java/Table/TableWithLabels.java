package Table;

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


}
