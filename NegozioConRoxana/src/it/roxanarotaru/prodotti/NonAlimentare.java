package it.roxanarotaru.prodotti;

public class NonAlimentare extends Prodotto {

	String materiale;

	public NonAlimentare(String nome, String codice, float prezzo, String materiale) {
		super(nome, codice, prezzo);
		this.materiale = materiale;
	}
	
	public NonAlimentare(NonAlimentare n) {
		super(n.getNome(), n.getCodice(), n.getPrezzo()); // Chiama il costruttore della classe estesa
		setMateriale(n.getMateriale());
		//this.scadenza = data; // Imposta il nuovo attributo della classe Alimentare
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public String getMateriale() {
		return materiale;
	}
	
	public float getPrezzoScontato() {
		if (materiale.equals("carta") || materiale.equals("vetro")) {
			return prezzo * 0.9f;
		} else {
			return super.getPrezzoScontato();
		}
	}
	
	@Override
	public void applicaSconto() {
		// TODO Auto-generated method stub
		if(!scontato){
			if (materiale.equals("carta") || materiale.equals("vetro")) {
				prezzo = prezzo * 0.90f;
				System.out.println(this.nome + "Sconto");
			} else {
				super.applicaSconto();
			}
			scontato = true;
		}
	}
	
	public String toString() {
		return "\nNome: " + nome + "\nCodice: " + codice + "\nPrezzo: " + prezzo + "\nMateriale: " + materiale;
	}
}
