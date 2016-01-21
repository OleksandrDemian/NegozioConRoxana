package it.roxanarotaru.prodotti;

public class ListaSpesa {
	Prodotto[] lista;
	private int numProdotti = 0;
	private int maxProdotti = 100;
	private boolean tesseraFedelta;
	
	public ListaSpesa(boolean tesseraFedelta){
		numProdotti=0;
		maxProdotti = 100;
		this.tesseraFedelta = tesseraFedelta;
		lista = new Prodotto[100];
	}
	
	public void aggiungiProdotto(Prodotto P){
		if(numProdotti < maxProdotti){
			if(tesseraFedelta){
				P.applicaSconto();
				//System.out.println("Tessera fedeltà accettata");
			} lista[numProdotti++] = P;
		} else{
			System.out.println("la lista e' piena");
		}
	}
	
	public void listaSpesa(){
		lista = new Prodotto[maxProdotti];
		numProdotti = 0;
	}
	
	public float calcolaSpesa(){
		float totale = 0;
		for(int i=0; i<numProdotti; i++){
			totale = totale + lista[i].getPrezzo();
		}
		return totale;
	}
	
	public void eliminaProdotto(int pos, Prodotto P){
		for(int i=pos; i<numProdotti; i++){
			lista[numProdotti--] = P;
		}
	}

}
