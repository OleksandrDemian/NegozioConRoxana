package it.roxanarotaru.prodotti;

public class Alimentare extends Prodotto {

	Data scadenza;
	
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
