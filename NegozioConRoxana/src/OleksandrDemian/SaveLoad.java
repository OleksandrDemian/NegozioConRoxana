package OleksandrDemian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import it.roxanarotaru.prodotti.Alimentare;
import it.roxanarotaru.prodotti.ListaSpesa;
import it.roxanarotaru.prodotti.NonAlimentare;
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
        }catch(IOException e){
        	Errore("Erorre nel caricamento");
        }
		
		try{
			String[] l = s.split(";");
			if(l.length != 0){
				for(int i = 0; i < l.length; i += 5){
					if(l[i].equals("A")){
						Alimentare a = new Alimentare(l[i+1], l[i+2], Prezzo(l[i+3]), l[i+4]);
						loaded.aggiungiProdotto(a);
					}
					if(l[i].equals("N")){
						NonAlimentare n = new NonAlimentare(l[i+1], l[i+2], Prezzo(l[i+3]), l[i+4]);
						loaded.aggiungiProdotto(n);
					}
				}
			}
		}catch(Exception eS){
			System.out.println("Non ci sono i prodotti");
		}
		return loaded;
	}
	
	public static void Save(ListaSpesa l) {
        String stringToSave = "";
        BufferedWriter bw;
        
        for(int i = 0; i < l.Lunghezza(); i++){
        	if(l.getProdotto(i) instanceof Alimentare){
        		Alimentare a = (Alimentare) l.getProdotto(i); 
        		stringToSave += "A;";
            	stringToSave += a.getNome() + ";";
            	stringToSave += a.getCodice() + ";";
            	stringToSave += a.getPrezzo() + ";";
            	stringToSave += a.getScadenza() + ";";
        	}
        	else{
        		NonAlimentare n = (NonAlimentare) l.getProdotto(i); 
        		stringToSave += "N;";
            	stringToSave += n.getNome() + ";";
            	stringToSave += n.getCodice() + ";";
            	stringToSave += n.getPrezzo() + ";";
            	stringToSave += n.getMateriale() + ";";
        	}
        }
        
        try{
        	bw = new BufferedWriter(new FileWriter("Salvataggio.txt"));
        	bw.write(stringToSave);
        	bw.close();
        	Info("Salvato");
        }catch(IOException e){
        	Errore("Errore nel salvataggio");
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
	
	static void Info(String s){
		MessageDialog.openInformation(new Shell(), "Info", s);
	}
	static void Info(int s){
		MessageDialog.openInformation(new Shell(), "Info", String.valueOf(s));
	}
	static void Errore(String s){
		MessageDialog.openError(new Shell(), "Errore", s);
	}
}