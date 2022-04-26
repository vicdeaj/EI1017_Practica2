package View;

import Controller.Controller;
import Controller.ControllerInterface;
import Model.ModelInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {

    private final Stage stage;
    private ControllerInterface controller;
    private ModelInterface model;
    private TabPane tabPane;

    public View(final Stage stage){
        this.stage = stage;
    }
    public void setController(ControllerInterface c){
        controller = c;
    }

    public void setModel(ModelInterface m){
        model = m;
    }
    public void createGUI(){

        tabPane = new TabPane();
        createKNNView();


        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }
}
