package sample;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class CreateController {

    @FXML
    private Pane _create;
    @FXML
    private TextField _textfield;
    @FXML
    TextArea textArea1;
    @FXML
    TextArea textArea2;



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


public void select(){
        String str = textArea1.getSelectedText();
    System.out.println("yes");
        textArea2.setText(str);
//        return str;
}

File f = new File("two.scm");

public void play(){
        String str = textArea1.getSelectedText();

//        String cmd2 = "echo "+str+ "| festival --tts";
//    String cmd2 = "festival -b (voice_akl_nz_jdt_diphone );; (SayText \" hi bro\")";
    String cmd2 = "festival -b (SayText \" hi bro\")";
//    String cmd3 = "touch two.scm;; echo \"(voice_akl_nz_jdt_diphone );; (SayText \" hi bro\") >> one.scm; festival -b one.scm ";
    String cmd3 = "touch two.scm; echo \"(voice_akl_nz_jdt_diphone) ;; (SayText \"hi bro\")\" >>"+f+" ; festival -b "+f;
    pbuild(cmd3);
        pbuild(cmd2);


}

public void Save(){

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
