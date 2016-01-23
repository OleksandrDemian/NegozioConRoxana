package it.roxanarotaru.prodotti;

import org.eclipse.jface.dialogs.MessageDialog;

public class Alimentare extends Prodotto {

	Data scadenza;
	boolean dataGiusta = false;
	
	public Alimentare(String nome, String codice, String descrizione, String prezzo, String data) {
		super(nome, codice, descrizione, prezzo); // Chiama il costruttore della classe estesa
		this.scadenza = scadenza; // Imposta il nuovo attributo della classe Alimentare
	}

	/**
	 * Imposta la scadenza
	 * @param scadenza
	 */
	public void setScadenza(Data scadenza) {
		this.scadenza = scadenza;
	}
	
	public Data getScadenza() {
		return scadenza;
	}
	
	public boolean DataGiusta() {
		return dataGiusta;
	}
	
	public void setDataStringa(String s){
		String d[] = s.split("/");
		if(d.length == 3){
			int g=0;
			int m=0;
			int a=0;
			
			g=ConvertiInt(d[0]);
			m=ConvertiInt(d[1]);
			a=ConvertiInt(d[2]);
			
			if(g<32 && g>0 && m<13 && m>0){
				scadenza=new Data(g,m,a);
				dataGiusta = true;
			}
			
		}else {
			return;
		}
	}
	
	public int ConvertiInt(String s){
		int n = 0;
		try{
			n = Integer.valueOf(s);
		}
		catch(NumberFormatException nE){
			System.out.println("No!");
		}
	return n;
		
	}
	@Override
	public void applicaSconto() {
		// TODO Auto-generated method stub
		if (scadenza.getDifference(new Data())<10) {
			prezzo = prezzo * 0.8f;
		} else {
			super.applicaSconto();
		}
	}
	
}
