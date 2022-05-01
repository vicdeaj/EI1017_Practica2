import Controller.Controller;
import Controller.ControllerInterface;
import Model.ModelInterface;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        //We create the references of the model and controller here to avoid trouble.
        ControllerInterface controller = new Controller();
        ModelInterface model = new Model();
        View view = new View(stage);


        //Next we assign which objects communicate with which
        controller.setView(view);
        controller.setModel(model);

        model.setView(view);
        model.setController(controller);

        view.setController(controller);
        view.setModel(model);

        //Start the GUI
        view.createGUI();
    }
}
