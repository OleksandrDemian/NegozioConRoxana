package OleksandrDemian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import it.roxanarotaru.prodotti.ListaSpesa;

public class SaveLoad {

    /*public static void Save() {
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
    }*/
	
	public static ListaSpesa Load(){
		ListaSpesa loaded = new ListaSpesa();
		FileReader fr;
		String s = "";
		
		try{
        	fr = new FileReader("Salvataggio.txt");
			BufferedReader br = new BufferedReader(fr);
        	s = br.readLine();
			
        	MessageDialog.openInformation(new Shell(), "Erorre", s);
        }catch(IOException e){
        	MessageDialog.openError(new Shell(), "Erorre", "Erorre nel caricamento");
        }
		
		return loaded;
	}
	
	public static void Save(ListaSpesa l) {
        String stringToSave = "";
        BufferedWriter bw;
        MessageDialog.openInformation(new Shell(), "Erorre", "Comincio salvataggio");
        
        for(int i = 0; i < l.Lunghezza(); i++){
        	stringToSave += l.getProdotto(i).getNome() + "|";
        	stringToSave += l.getProdotto(i).getCodice() + "|";
        	stringToSave += l.getProdotto(i).getPrezzo() + "|";
        }
        
        try{
        	bw = new BufferedWriter(new FileWriter("Salvataggio.txt"));
        	bw.write(stringToSave);
        	bw.close();
        	MessageDialog.openInformation(new Shell(), "Erorre", "Salvato");
        }catch(IOException e){
        	MessageDialog.openError(new Shell(), "Erorre", "Erorre nel salvataggio");
        }
    }
}
