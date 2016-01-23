package it.roxanarotaru.prodotti;

public class Prodotto implements Cloneable {
	String nome;
	String codice;
	String descrizione;
	float prezzo;
	
	public Prodotto(){
		this.nome="Nullo";
		this.codice = "123456";
		this.descrizione = "Niente";
		this.prezzo = 0.0f;
	}

	public Prodotto(String nome, String codice, String descrizione, String prezzo) {
		super();
		this.nome = nome;
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzoConv(prezzo);
		System.out.println(this);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void applicaSconto() {
		this.prezzo = this.prezzo * 0.95f;
	}

	@Override
	public String toString() {
		return "Cod: " + codice + " descrizione: " + descrizione + " prezzo:" + prezzo;
	}

	@Override
	public boolean equals(Object obj) {
		// Sono uguali se sono uguali tutti i campi
		Prodotto p = (Prodotto) obj;
		if (p.getCodice().equals(codice) && p.getDescrizione().equals(descrizione) && p.getPrezzo() == prezzo) {
			return true;
		} else {
			return false;
		}
	}
	
	private float prezzoConv(String stringa){
		float p = 0;
			try{
				p = Float.valueOf(stringa);
			}
			catch(NumberFormatException nE){
				System.out.println("No!");
			}
		return p;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
