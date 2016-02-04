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
	
	public static void Salva(ListaSpesa ls, String nomeFile) throws FileNotFoundException{
		FileOutputStream salva = new FileOutputStream(nomeFile);
		PrintStream ps = new PrintStream(salva);
		ps.println(ls.Lunghezza());
		for(int i=0; i<ls.Lunghezza();i++){
			
			if(ls.getProdotto(i) instanceof Alimentare){
				Alimentare al = (Alimentare) ls.getProdotto(i);
				ps.println("A");
				ps.println(al.getNome());
				ps.println(al.getPrezzo());
				ps.println(al.getCodice());
				ps.println(al.getScadenza());
			}
			if(ls.getProdotto(i) instanceof NonAlimentare){
				NonAlimentare nl = (NonAlimentare) ls.getProdotto(i);
				ps.println("NA");
				ps.println(nl.getNome());
				ps.println(nl.getPrezzo());
				ps.println(nl.getCodice());
				ps.println(nl.getMateriale());
			}
			
		}
		ps.close();
	}
	
	public static ListaSpesa Carica(String nomeFile) throws IOException{
		FileReader carica = new FileReader(nomeFile);
		BufferedReader cs = new BufferedReader(carica);
		int max=Num(cs.readLine());
		ListaSpesa lista = new ListaSpesa();
		for(int i=0; i<max;i++){
			String tipo=cs.readLine();
			if(tipo.equals("A")){
				String nome = cs.readLine();
				float prezzo = Prezzo(cs.readLine());
				String codice = cs.readLine();
				String data = cs.readLine();
				Alimentare temp = new Alimentare(nome,codice,prezzo,data);
				lista.aggiungiProdotto(temp);
			}
			if(tipo.equals("NA")){
				String nome = cs.readLine();
				float prezzo = Prezzo(cs.readLine());
				String codice = cs.readLine();
				String materiale= cs.readLine();
				NonAlimentare temp = new NonAlimentare(nome,codice,prezzo,materiale);
				lista.aggiungiProdotto(temp);
			}
		}
		cs.close();
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