package it.roxanarotaru.prodotti;

public class Prodotto implements Cloneable {
	protected String nome;
	protected String codice;
	protected float prezzo;
	protected boolean scontato;
	
	public Prodotto(){
		this.nome="Nullo";
		this.codice = "123456";
		this.prezzo = 0.0f;
		scontato = false;
	}
	
	public Prodotto(Prodotto p){
		this.nome = p.getNome();
		this.codice = p.getCodice();
		this.prezzo = p.getPrezzo();
		scontato = false;
	}

	public Prodotto(String nome, String codice, float prezzo) {
		super();
		this.nome = nome;
		this.codice = codice;
		this.prezzo = prezzo;
		scontato = false;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void applicaSconto() {
		if(!scontato){
			this.prezzo = this.prezzo * 0.95f;
			scontato = true;
		}
	}

	@Override
	public String toString() {
		return "Cod: " + codice + " nome: " + nome + " prezzo:" + prezzo;
	}

	@Override
	public boolean equals(Object obj) {
		// Sono uguali se sono uguali tutti i campi
		Prodotto p = (Prodotto) obj;
		if (p.getCodice().equals(nome) && p.getNome().equals(nome) && p.getPrezzo() == prezzo) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
