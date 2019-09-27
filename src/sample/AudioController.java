package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioController {

    @FXML
    private Pane _Audio;
    @FXML
    ListView _listView;


    @FXML
    public void initialize(){

        _listView.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("ls *.wav 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            System.out.println(str.get(i));
            _listView.getItems().add(str.get(i).substring(0,str.get(i).length()-4));

        }
        _listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }













    public void switchToMain(){







        SwitchScenes sw = new SwitchScenes(_Audio);
        try{
            sw.switchScenes("MainMenu.fxml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
