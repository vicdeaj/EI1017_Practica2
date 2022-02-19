package CSV;

import Table.*;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV {
    public static Table readTable(String path) throws FileNotFoundException{
        Table table = new Table();
        List<String> headers = new ArrayList<>();

        Scanner file = new Scanner(new File(path));
        headers.addAll(Arrays.asList(file.nextLine().split(",")));
        table.addAllHeaders(headers);

        while(file.hasNextLine()){ //Posiblemente convertir esto a un metodo estatico privado de la clase haria la laectura mas simple. Nombre: leer o yo que se

            Row row = new Row();

            String [] data = file.nextLine().split(",");
            for (String element : data){
                row.add(Double.parseDouble(element));
            }
        }

        return table;
    }

    public static TableWithLabels readTableWithLabels(String path) throws FileNotFoundException{

        TableWithLabels table = new TableWithLabels();
        List<String> headers = new ArrayList<>();
        Scanner file = new Scanner(new File(path));

        headers.addAll(Arrays.asList(file.nextLine().split(",")));

        table.addAllHeaders(headers);

        while(file.hasNextLine()){

            RowWithLabel row = new RowWithLabel();

            String [] data = file.nextLine().split(",");
            row.addLabel(data[data.length - 1]); //El ultimo elemento de data es siempre el label

            for (int i = 0; i < data.length - 1; i++) {
                row.add(Double.parseDouble(data[i]));
            }

            table.addRow(row);
        }



        return table;
    }
}
