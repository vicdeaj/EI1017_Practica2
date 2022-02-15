import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV {
    public static Table readTable(String path){
        Table t = new Table();
        try {
            Scanner scanner = new Scanner(new File(path));

            String headers = scanner.next();
            List<String> lista = Arrays.asList(headers.split(","));
            t.addAllHeader(lista);

            while(scanner.hasNext()){
                Row r = new Row();
                String linea = scanner.next();
                List<String> listaElemento = Arrays.asList(linea.split(","));
                for (String s : listaElemento){
                    r.add(Double.valueOf(s));
                }
                t.addRow(r);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return t;
    }
}
