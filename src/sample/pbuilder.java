package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class pbuilder {

    private String _cmd;
    private static pbuilder _instance;

    private static List<String> _stdOut = new ArrayList<String>();
    private static int num;


    private pbuilder() {}

    public static pbuilder getInstance() {
        if (_instance == null) {
            _instance = new pbuilder();
        }

        return _instance;
    }


        public void probuild(String _cmd) {
            try {
                ProcessBuilder pb = new ProcessBuilder("bash", "-c", _cmd);

                Process process = pb.start();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void probuild2(String _cmd){

            try {

                ProcessBuilder pb = new ProcessBuilder("bash", "-c", _cmd);

                Process process = pb.start();

                BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));




                int exitStatus = process.waitFor();

                if (exitStatus == 0) {
                    String line;
                    while ((line = stdout.readLine()) != null) {

                        _stdOut.add(line);

                    }

                } else {
                    String line;
                    while ((line = stderr.readLine()) != null) {
                        System.err.println(line);
                    }
                }


                stdout.close();
                stderr.close();

            } catch(Exception e)

            {
                e.printStackTrace();
            }


        }

    public static List<String> getStd() {
        return _stdOut;
    }


}
