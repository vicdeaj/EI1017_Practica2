package View;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {

    private final Stage stage;
    private TextField tfNombre;

    public View(final Stage stage){
        this.stage = stage;
        tfNombre = new TextField();
        tfNombre.setText("HOLA");

        VBox box = new VBox(10, tfNombre);

        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    public void createGUI(){


    }
}
