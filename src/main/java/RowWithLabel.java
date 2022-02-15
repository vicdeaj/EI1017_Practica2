public class RowWithLabel extends Row{
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
}
