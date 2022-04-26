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
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;


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

    private void createKNNView(){


        BorderPane canvas = new BorderPane();

        //Top
        Label title = new Label("");

        //Left side
        ComboBox xSelector = new ComboBox();

        //Bottom side
        ComboBox ySelector = new ComboBox();

        //Right side
        Button openFile = new Button("Open file");
        openFile.setOnAction(e -> selectFile());

        ComboBox distanceType = new ComboBox();
        TextField inputCoordinates = new TextField("Input values");
        TextField resultLabel = new TextField("Result");
        resultLabel.setDisable(true); //Does not allow the user to write in the label
        Button estimate = new Button("Estimate");

        VBox rightSide = new VBox(openFile, distanceType,inputCoordinates,resultLabel,estimate);
        rightSide.setSpacing(10);
        rightSide.setPadding(new Insets(10,10,10,10));
        rightSide.setAlignment(Pos.CENTER);

        //Center
        final NumberAxis xAxis = new NumberAxis(0, 5.5, 0.5);
        final NumberAxis yAxis = new NumberAxis(0, 5.5, 0.5);
        ScatterChart graph = new ScatterChart<Number,Number>(xAxis, yAxis);

        //Filling the ComboBox
        canvas.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        canvas.setLeft(ySelector);
        BorderPane.setAlignment(ySelector, Pos.CENTER);

        canvas.setBottom(xSelector);
        BorderPane.setAlignment(xSelector, Pos.CENTER);

        canvas.setRight(rightSide);
        canvas.setCenter(graph);

        //We add it to the global tab pane

        tabPane.getTabs().add(new Tab("KNN", canvas));

    }

    private void selectFile(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getAbsolutePath());

    }
}
