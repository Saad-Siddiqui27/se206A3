package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public void initialize() {

        _listView.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("ls *.wav 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            _listView.getItems().add(str.get(i).substring(0, str.get(i).length() - 4));
        }
        _listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    public void merge() {


        StringBuilder files = new StringBuilder();
        List<String> Files = _listView.getSelectionModel().getSelectedItems();

        if (Files.size() < 2 | _tField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select two or more files to merge");
            alert.setTitle("Merging Files");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }

            });
        } else {
            for (int i = 0; i < Files.size(); i++) {


                files.append(Files.get(i) + ".wav ");

            }
            System.out.println(files.toString());
            pbuilder.getInstance().probuild("sox " + files.toString() + "" + _tField.getText() + ".wav");
            pbuilder.getInstance().probuild("rm " + files.toString());
            initialize();


        }


    }


    public void switchToMain() {


        SwitchScenes sw = new SwitchScenes(_Audio);

        try {
            sw.switchScenes("MainMenu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Henry New Code;


//    public void CombineVwithA() throws IOException, UnsupportedAudioFileException {
//        File f = new File(_listView.getSelectionModel().getSelectedItem().toString());
//        File f2 = new File("car10.mkv");
////        String a = f.getName();
////        String a2 = a.replace(".mkv", "");
//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
//        AudioFormat format = audioInputStream.getFormat();
//        long frames = audioInputStream.getFrameLength();
//        double durationInSeconds = (frames + 0.0) / format.getFrameRate();
////        String filename = f2.getName();
////        String num = filename.replaceAll("[a-zA-Z]", "");
////        num = num.replaceAll(".", "");
////        String term = filename.replaceAll("\\d+", "");
////        term = term.replace(".mkv", "");
////        int number = Integer.parseInt(num);
//        double t = durationInSeconds / ;
//        String time = Double.toString(t);
//        File f3 = new File("input.txt");
//        FileWriter fw = new FileWriter(f3);
//        int i = 1;
//        while (i <= number) {
//            String cmd = "ffmpeg -loop 1 -i " + term + i + ".jpg -c:v libx264 -t " + time + " -pix_fmt yuv420p " + term + "temp" + i + ".mkv";
//            pbuild(cmd);
//            fw.write("file " +term + "temp" + i + ".mkv");
//            i = i + 1;
//        }
//        fw.close();
//        String OutputName = term + "Combinedwith" + a2 + ".mkv";
//        String cmd2 = "ffmpeg -f concat -safe 0 -i "+f3+" -c copy " + term + ".mkv";
//        pbuild(cmd2);
//        String cmd3 = "rm *temp.mkv";
//        pbuild(cmd3);
//        String cmd4 = "rm input.txt";
//        pbuild(cmd4);
//        String cmd5 = "ffmpeg -i " + term + ".mkv -vf drawtext=\"fontfile=/path/to/font.ttf: \\ text='" + term + "': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5: " + "\\" + " boxborderw=5: x=(w-text_w)/2: y=(h-text_h)/2\" -codec:a copy " + "Outtemp.mkv";
//        pbuild(cmd5);
//        String cmd6 = "rm " + term + ".mkv";
//        pbuild(cmd6);
//        String cmd7 = "ffmpeg -i Outtemp.mkv -i " + a + " -c copy " + OutputName;
//        pbuild(cmd7);
//        String cmd8 = "rm " + "Outtemp.mkv";
//        pbuild(cmd8);
//    }
//
//    public void pbuild(String cmd) {
//        try {
//            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
//            Process process = pb.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
