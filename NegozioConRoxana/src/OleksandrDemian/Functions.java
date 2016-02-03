package OleksandrDemian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import it.roxanarotaru.prodotti.Alimentare;
import it.roxanarotaru.prodotti.Data;
import it.roxanarotaru.prodotti.ListaSpesa;
import it.roxanarotaru.prodotti.NonAlimentare;
import it.roxanarotaru.prodotti.Prodotto;

public class Functions {
	
	/*public static ListaSpesa Load(String tipo){
		ListaSpesa loaded = new ListaSpesa();
		FileReader fr;
		String s = "";
		
		try{
        	fr = new FileReader(tipo + ".txt");
			BufferedReader br = new BufferedReader(fr);
        	s = br.readLine();
        	br.close();
        }catch(IOException e){
        	//BufferedWriter bw = new BufferedWriter(new FileWriter(tipo + ".txt"));
        	e.printStackTrace();
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
			Alimentare a = new Alimentare("Banane", "12354", 5.2f, new Data());
			loaded.aggiungiProdotto(a);
		}
		return loaded;
	}
	
	public static void Save(ListaSpesa l, String tipo) {
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
        	bw = new BufferedWriter(new FileWriter(tipo + ".txt"));
        	bw.write(stringToSave);
        	bw.close();
        	Info("Salvato");
        }catch(IOException e){
        	Errore("Errore nel salvataggio");
        }
    }*/
	
	public static void Salva(ListaSpesa ls, String nomeFile) throws FileNotFoundException{
		FileOutputStream salva = new FileOutputStream(nomeFile);
		PrintStream ps = new PrintStream(salva);
		ps.println(ls.Lunghezza());
		for(int i=0; i<ls.Lunghezza();i++){
			ps.println(ls.getProdotto(i).getNome());
			ps.println(ls.getProdotto(i).getPrezzo());
			ps.println(ls.getProdotto(i).getCodice());
		}
	}
	
	public static ListaSpesa Carica(String nomeFile) throws IOException{
		FileReader carica = new FileReader(nomeFile);
		BufferedReader cs = new BufferedReader(carica);
		int max=Num(cs.readLine());
		ListaSpesa lista = new ListaSpesa();
		for(int i=0; i<max;i++){
			String nome = cs.readLine();
			float prezzo = Prezzo(cs.readLine());
			String codice = cs.readLine();
			Prodotto temp=new Prodotto(nome,codice,prezzo);
			lista.aggiungiProdotto(temp);
		}
		
		return lista;
	}
	
	private static float Prezzo(String s){
		try{
			return Float.valueOf(s);
		}catch(NumberFormatException nE){
			return 0;
		}
	}
	private static int Num(String s){
		try{
			return Integer.valueOf(s);
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