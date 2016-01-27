package it.roxanarotaru.prodotti;

import org.eclipse.jface.dialogs.MessageDialog;

public class Alimentare extends Prodotto {

	Data scadenza;
	
	public Alimentare(String nome, String codice, float prezzo, Data data) {
		super(nome, codice, prezzo); // Chiama il costruttore della classe estesa
		this.scadenza = data; // Imposta il nuovo attributo della classe Alimentare
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
	
}
