package View;

import Controller.ControllerInterface;

import Interfaces.Distance;
import Interfaces.DistanceType;
import Model.ModelInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class View {

    private final Stage stage;
    private ControllerInterface controller;
    private ModelInterface model;
    private TabPane tabPane;
    private  List<XYChart.Series> seriesList ;
    private ScatterChart graph;
    private TextField inputCoordinates;
    private TextField resultLabel;
    private XYChart.Series estimation = new XYChart.Series();

    ComboBox xSelector;
    ComboBox ySelector;

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
        xSelector = new ComboBox();

        //Bottom side
        ySelector = new ComboBox();

        //Right side
        ComboBox distanceSelector = new ComboBox();
        Button openFile = new Button("Open file");

        inputCoordinates = new TextField("Input values");
        resultLabel = new TextField("Result");
        resultLabel.setDisable(true); //Does not allow the user to write in the label
        Button estimate = new Button("Estimate");

        VBox rightSide = new VBox(openFile, distanceSelector,inputCoordinates,resultLabel,estimate);
        rightSide.setSpacing(10);
        rightSide.setPadding(new Insets(10,10,10,10));
        rightSide.setAlignment(Pos.CENTER);

        //Center
        final NumberAxis xAxis = new NumberAxis(0, 10, 0.5);
        final NumberAxis yAxis = new NumberAxis(0, 10, 0.5);
        graph = new ScatterChart<Number,Number>(xAxis, yAxis);
        graph.setAnimated(false);
        graph.setLegendVisible(false);

        //Filling the ComboBox
        canvas.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        canvas.setLeft(ySelector);
        BorderPane.setAlignment(ySelector, Pos.CENTER);

        canvas.setBottom(xSelector);
        BorderPane.setAlignment(xSelector, Pos.CENTER);

        canvas.setRight(rightSide);
        canvas.setCenter(graph);

                //ACTIONS//

        //Clicking the open file button loads the data and starts the model
        openFile.setOnAction(e ->{

            selectFile();

            List<String> headers = model.getTableHeaders();
            ObservableList<String> axisData = FXCollections.observableArrayList(headers);
            ObservableList<DistanceType> distanceTypes = FXCollections.observableArrayList(DistanceType.values());

            xSelector.setItems(axisData);
            ySelector.setItems(axisData);
            distanceSelector.setItems(distanceTypes);

            xSelector.getSelectionModel().selectFirst();
            ySelector.getSelectionModel().selectFirst();
            distanceSelector.getSelectionModel().selectFirst();

            updateChart();

            //changeLabelContent(title, axisData.get(0), axisData.get(0));

        });

        //Upon modifying the axis, the name and the shown values change

        EventHandler<ActionEvent> axiiReload = e -> {
            changeLabelContent(title, xSelector.getValue().toString(), ySelector.getValue().toString());
            updateChart();
        };
        xSelector.setOnAction(axiiReload);
        ySelector.setOnAction(axiiReload);

        // Upon modifying distance measurement, model is modified
        EventHandler<ActionEvent> distanceReload = e -> {

            controller.selectDistanceType((DistanceType)distanceSelector.getValue());
            updateChart();
        };

        //Upon clicking "estimate", call for an estimation and represent it in the chart

        EventHandler<ActionEvent> estimateValue = e-> {
            estimate();
        };

        distanceSelector.setOnAction(distanceReload);
        estimate.setOnAction(estimateValue);



        //We add it to the global tab pane

        tabPane.getTabs().add(new Tab("KMEANS", canvas));

    }

    private void selectFile(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        controller.loadFile(file.getAbsolutePath());

    }

    private void changeLabelContent(Label label,String e1, String e2){
        label.setText(e1 + " VS " + e2);
    }

    private void createGraphSeries(int n){ //Desde la vi
        graph.getData().clear();

        seriesList = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            seriesList.add(new XYChart.Series());
        }

        seriesList.add(estimation); //Add the estimation to the list every time.

    }
    public void fillSeries(Double x, Double y, String series){
        String[] aux = series.split(" ");
        int seriesNumber = Integer.parseInt(aux[1]);
        //System.out.println(seriesNumber);
        seriesList.get(seriesNumber).getData().add(new XYChart.Data<Number, Number>(x,y));

        /*for (int i = 0; i < seriesList.size(); i++) {
            if (seriesNumber - 1 == i){
                seriesList.get(i).getData().add(new XYChart.Data<Number, Number>(x,y));
            }
        }*/
    }

    public void insertSeries(){
        //System.out.println(seriesList.get(2).getData());

        graph.getData().addAll(seriesList);

        if (!inputCoordinates.getText().equals("Input values")){
            estimate();
        }
    }

    private void updateChart(){ // update view with selected x and y
        createGraphSeries(model.getNumberOfClusters());

        String lx =xSelector.getValue().toString();
        String l2 =ySelector.getValue().toString();

        model.getData(lx, l2);

    }

    private void estimate(){

        List<Double> coordinates = new ArrayList<>();

        String[] stringCoordinates = inputCoordinates.getText().split(",");

        if (stringCoordinates.length != model.getTableHeaders().size()){
            throw new IllegalStateException();
        }

        for(String e : stringCoordinates){
            coordinates.add(Double.parseDouble(e));
        }

        resultLabel.setText(model.estimate(coordinates));

        estimation.getData().clear(); //Clear the previous estimation
        estimation.getData().add(new XYChart.Data(coordinates.get(model.getiX()), coordinates.get(model.getiY()))); //Get the new one

    }

}
