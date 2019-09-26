package sample;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;


public class CreateController {

    @FXML
    private Pane _create;
    @FXML
    private TextField _textfield;
    @FXML
    TextArea textArea1;
    @FXML
    TextArea textArea2;
    @FXML
    MenuButton _menubutton;
    @FXML
    CheckMenuItem _voice1;
    @FXML
    CheckMenuItem _voice2;



    String term;


    public void search() {
        term = _textfield.getText();

        if(term.isEmpty())

    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Please enter a valid term that you want to search");
        alert.setTitle("Empty term name");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

            }
        });
    }
        else

    {

        String command = "wikit " + term + "| sed -e :1 -e 's/\\([.?!]\\)[[:blank:]]\\{1,\\}\\([^[:blank:]]\\)/\\1\\\n" +
                "\\2/;t1' ";
//        pbuild(command);

        try{
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

        Process process = pb.start();

        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        int exitStatus = process.waitFor();

        StringBuilder sb = new StringBuilder();
        if (exitStatus == 0) {
            String line;
            int count =1;
            while ((line = stdout.readLine()) != null) {
                sb.append(count++ + ". "+line).append("\n");

            }
        } else {
            String line;
            while ((line = stderr.readLine()) != null) {
                System.err.println(line);
            }
        }

        textArea1.setText(sb.toString());

        stderr.close();
        stdout.close();

    } catch(Exception e){
            e.printStackTrace();
        }


    }

}


public String select(){
        String str = textArea1.getSelectedText();
        textArea2.setText(str);

        return str;
}

    File f = new File("voice1.scm");



public void play(){
        String str = textArea1.getSelectedText();




    try {
        if(_voice1.isSelected()) {
            System.out.println("yes");
            FileWriter fw = new FileWriter(f);
            fw.write("(voice_akl_nz_jdt_diphone) ;; select Jono" + " \n(SayText \"" + str + "\")");
            fw.close();
            String cmd3 = "festival -b " + f;
            pbuild(cmd3);
        }else if(_voice2.isSelected()){
            System.out.println("no");
            FileWriter fw = new FileWriter(f);
            fw.write("(voice_kal_diphone) ;; select Jono" + " \n(SayText \"" + str + "\")");
            fw.close();
            String cmd3 = "festival -b " + f;
            pbuild(cmd3);

        }
    }catch(Exception e){
        e.printStackTrace();
    }


}

public void Save(){

    try {
        String str2 = textArea2.getText();


        if(_voice1.isSelected()) {
            helpSave(str2, "(voice_akl_nz_jdt_diphone) ");
        }else if(_voice2.isSelected()){
            helpSave(str2, "(voice_kal_diphone) ");

        }
    }catch(Exception e){
        e.printStackTrace();
    }

//    String str2 = textArea2.getText();
////    String cmd4 = "festival; (utt.save.wave (SayText \""+str2+"\") \"name.wav\" 'riff)";
//    String cmd4 = "text2wave -o some.wav "+str2+" -eval slow.scm";
//    pbuild(cmd4);
}

    public void helpSave(String str2, String s) throws IOException {
        File f2 = new File("text");
        FileWriter fw2 = new FileWriter(f2);
        fw2.write(str2);
        fw2.close();
        FileWriter fw = new FileWriter(f);
        fw.write(s);
        fw.close();
        String cmd3 = "text2wave -o some.wav "+f2+" -eval "+f;
        pbuild(cmd3);
    }


    public void pbuild(String cmd){
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);

            Process process = pb.start();

//            BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            int exitStatus = process.waitFor();
//
//            StringBuilder sb = new StringBuilder();
//            if (exitStatus == 0) {
//                String line;
//                int count =1;
//                while ((line = stdout.readLine()) != null) {
//                    sb.append(count++ + ". "+line).append("\n");
//
//                }
//            } else {
//                String line;
//                while ((line = stderr.readLine()) != null) {
//                    System.err.println(line);
//                }
//            }
//
//            textArea1.setText(sb.toString());
//

        } catch(Exception e){
            e.printStackTrace();
        }


    }





    public void switchScenes(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) _create.getScene().getWindow();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public void MainMenu(){
        try {
            switchScenes("MainMenu.fxml");
        }catch(IOException e){
            e.printStackTrace();
        }

    }





}
