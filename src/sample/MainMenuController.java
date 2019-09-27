package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainMenuController {

    @FXML Pane _Menu;




    public void switchScenes(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) _Menu.getScene().getWindow();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
    }




    public void switchToCreate() {
        try {
            switchScenes("create.fxml");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void switchToAudio(){



        SwitchScenes sw = new SwitchScenes(_Menu);


        try{
            sw.switchScenes("AudioFiles.fxml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }






}
