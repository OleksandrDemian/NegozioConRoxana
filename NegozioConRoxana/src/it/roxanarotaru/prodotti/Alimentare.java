package it.roxanarotaru.prodotti;

import org.eclipse.jface.dialogs.MessageDialog;

public class Alimentare extends Prodotto {

	Data scadenza;
	
	public Alimentare(String nome, String codice, float prezzo, Data data) {
		super(nome, codice, prezzo); // Chiama il costruttore della classe estesa
		this.scadenza = data; // Imposta il nuovo attributo della classe Alimentare
	}
	
	public Alimentare(String nome, String codice, float prezzo, String data) {
		super(nome, codice, prezzo); // Chiama il costruttore della classe estesa
		setScadenza(data);
		//this.scadenza = data; // Imposta il nuovo attributo della classe Alimentare
	}

	/**
	 * Imposta la scadenza
	 * @param scadenza
	 */
	public void setScadenza(Data scadenza) {
		this.scadenza = scadenza;
	}
	
	public void setScadenza(String s) {
		String[] temp = s.split("/");
		if(temp.length == 3){
			try{
				scadenza = new Data(Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Integer.valueOf(temp[2]));
			}catch(Exception e){
				System.out.println("Erorre nel creare data");
			}
		}
	}
	
	public Data getScadenza() {
		return scadenza;
	}
	
	@Override
	public void applicaSconto() {
		// TODO Auto-generated method stub
		if(!scontato){
			if (scadenza.getDifference(new Data())<10) {
				prezzo = prezzo * 0.8f;
				System.out.println(this.nome + "Sconto");
			} else {
				super.applicaSconto();
			}
			scontato = true;
		}
	}

	@Override
	public String toString() {
		return "\nNome: " + nome + "\nCodice: " + codice + "\nPrezzo: " + prezzo + "\nScadenza: " + scadenza;
	}
	
}
