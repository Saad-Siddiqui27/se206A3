package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

public class AudioController {

    @FXML
    private Pane _Audio;
    @FXML
    private ListView _listView;
    @FXML
    private TextField _tField;

    @FXML
    public void initialize(){

        _listView.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("ls *.wav 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            _listView.getItems().add(str.get(i).substring(0,str.get(i).length()-4));

        }
        _listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    public void merge(){


        StringBuilder files = new StringBuilder();
        List<String> Files = _listView.getSelectionModel().getSelectedItems();

        for(int i=0;i<Files.size();i++){



            files.append(Files.get(i)+".wav ");
        }

        pbuilder.getInstance().probuild("sox "+files.toString()+" "+_tField.getText()+".wav");

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
