package OleksandrDemian;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SaveLoad {

    public static void Save() {
        BufferedWriter writer = null;
        try {
            //create a temporary file
            //String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        	String toSave = "Ciao";
            File logFile = new File(toSave);

            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Hello world!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
