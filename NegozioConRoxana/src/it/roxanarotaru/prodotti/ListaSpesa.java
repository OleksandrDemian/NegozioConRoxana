package it.roxanarotaru.prodotti;

public class ListaSpesa {
	Prodotto[] lista;
	private int numProdotti = 0;
	private int maxProdotti = 100;
	private boolean tesseraFedelta;
	
	public ListaSpesa(){
		numProdotti=0;
		maxProdotti = 100;
		this.tesseraFedelta = tesseraFedelta;
		lista = new Prodotto[100];
	}
	
	public void aggiungiProdotto(Prodotto p){
		if(numProdotti < maxProdotti){
			/*if(tesseraFedelta){
				P.applicaSconto();
				//System.out.println("Tessera fedelt� accettata");
			}*/ 
			lista[numProdotti++] = p;
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
	
	public int Lunghezza(){
		return numProdotti;
	}
	
	public Prodotto getProdotto(int index){
		return lista[index];
	}
	
	public void eliminaProdotto(int pos){
		for(int i=pos; i<numProdotti; i++){
			lista[i] = lista[i+1];
		}
		numProdotti--;
	}

}
