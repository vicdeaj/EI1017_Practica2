package View;

import Controller.ControllerInterface;

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
import java.util.List;


public class View implements ViewInterface{

    private final Stage stage;
    private ControllerInterface controller;
    private ModelInterface model;
    private TabPane tabPane;
    private  List<XYChart.Series> seriesList = new ArrayList<>(); //List of all the plot series
    private ScatterChart graph;
    private TextField inputCoordinates; //Coordinates for estimation go here
    private TextField resultLabel; //Result of previous estimation shows here
    private XYChart.Series estimation = new XYChart.Series(); //Series reserved for the estimation point
    private boolean alreadyEstimated = false; //Checks wether or not there's been an estimation
    private boolean openedFile = false;

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
        createView();


        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }

    private void createView(){

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
        graph.setLegendVisible(true);

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


        openFile.setOnAction(e ->{ //Clicking the open file button loads the data and starts the model

            if (selectFile()){

                List<String> headers = model.getTableHeaders();
                ObservableList<String> axisData = FXCollections.observableArrayList(headers);
                ObservableList<DistanceType> distanceTypes = FXCollections.observableArrayList(DistanceType.values());

                xSelector.setItems(axisData);
                ySelector.setItems(axisData);
                distanceSelector.setItems(distanceTypes);

                xSelector.getSelectionModel().selectFirst();
                ySelector.getSelectionModel().selectFirst();
                distanceSelector.getSelectionModel().selectFirst();

                openedFile = true;

                if (alreadyEstimated){
                    resultLabel.setText("Result");
                    inputCoordinates.setText("Input Values");
                    estimation.getData().clear();
                    alreadyEstimated = false;
                }

                updateChart();
            }
        });



        EventHandler<ActionEvent> axiiReload = e -> { //Upon modifying the axis, the name and the shown values change
            changeLabelContent(title, xSelector.getValue().toString(), ySelector.getValue().toString());

            updateChart();
        };
        xSelector.setOnAction(axiiReload);
        ySelector.setOnAction(axiiReload);


        EventHandler<ActionEvent> distanceReload = e -> { // Upon modifying distance measurement, model is modified

            controller.selectDistanceType((DistanceType)distanceSelector.getValue());
            updateChart();
        };



        EventHandler<ActionEvent> estimateValue = e-> { //Upon clicking "estimate", call for an estimation and represent it in the chart

            if (openedFile) {
                estimate();
            }
        };

        distanceSelector.setOnAction(distanceReload);
        estimate.setOnAction(estimateValue);



        //We add it to the global tab pane

        tabPane.getTabs().add(new Tab("KNN", canvas));

    }

    private boolean selectFile(){ //Starts the file selection process and warns the controller to start the program
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            String path = file.getAbsolutePath();
            String extension = path.substring(path.lastIndexOf('.'));

            if (extension.equals(".csv")){
                controller.loadFile(path);
                return true;
            }
        }

        return false;
    }

    private void changeLabelContent(Label label,String e1, String e2){ //Changes the tittle of the application
        label.setText(e1 + " VS " + e2);
    }


    public void fillSeries(Double x, Double y, String series){//Fills up the chart series with their corresponding points
        boolean inSeries = false;
        for (XYChart.Series s : seriesList){
            if (s.getName().equals(series)){
                // son iguales
                inSeries = true;
                s.getData().add(new XYChart.Data<Number,Number>(x, y));
                break;
            }
        }

        if (!inSeries){
            XYChart.Series s = new XYChart.Series();
            s.setName(series);
            s.getData().add(new XYChart.Data<Number,Number>(x,y));
            seriesList.add(s);
        }
    }

    @Override
    public void insertSeries(){
        //System.out.println(graph.getData());

        graph.getData().clear();
        seriesList.add(estimation); //Add the estimation series before completing the graph
        graph.getData().addAll(seriesList); //Add everything to the graph

        if (alreadyEstimated){
            estimate();
        }
    }

    private void updateChart(){ // update view with selected x and y

        String lx =xSelector.getValue().toString();
        String ly =ySelector.getValue().toString();

        seriesList.clear();

        model.getData(lx, ly);

    }

    private void estimate(){

        List<Double> coordinates = new ArrayList<>();
        String[] stringCoordinates = inputCoordinates.getText().split(",");

        if (stringCoordinates.length == model.getTableHeaders().size()){ //If the length of the coordenates matches the required size, estimate. Else, do nothing
            for(String e : stringCoordinates){
                coordinates.add(Double.parseDouble(e));
            }

            resultLabel.setText(model.estimate(coordinates));

            estimation.getData().clear(); //Clear the previous estimation
            estimation.getData().add(new XYChart.Data(coordinates.get(model.getiX()), coordinates.get(model.getiY()))); //Get the new one
            alreadyEstimated = true;//Acknowledge that an estimation has been done

        }

    }

}
