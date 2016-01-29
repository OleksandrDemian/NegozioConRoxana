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
import it.roxanarotaru.prodotti.Prodotto;

public class SaveLoad {
	
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
		
		String[] l = s.split("|");
		for(int i = 0; i < l.length; i += 3){
			Prodotto p = new Prodotto(l[i], l[i+1], Prezzo(l[i+2]));
			loaded.aggiungiProdotto(p);
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
	
	private static float Prezzo(String s){
		float f = 0f;
		try{
			return Float.valueOf(s);
		}catch(NumberFormatException nE){
			return 0;
		}
	}
}