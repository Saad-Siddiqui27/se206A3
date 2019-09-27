package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScenes {

    private Pane _pane;

    public SwitchScenes( Pane pane){

        _pane = pane;

    }

    public void switchScenes(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) _pane.getScene().getWindow();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
    }
}
