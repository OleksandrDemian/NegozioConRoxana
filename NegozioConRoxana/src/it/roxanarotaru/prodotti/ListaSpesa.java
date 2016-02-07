package it.roxanarotaru.prodotti;

public class ListaSpesa {
	Prodotto[] lista;
	private int numProdotti = 0;
	private int maxProdotti = 100;
	
	public ListaSpesa(){
		numProdotti=0;
		maxProdotti = 100;
		lista = new Prodotto[100];
	}
	
	public void aggiungiProdotto(Prodotto p){
		if(numProdotti < maxProdotti){
			if(p instanceof Alimentare){
				Alimentare a = (Alimentare) p;
				lista[numProdotti++] = new Alimentare(a);
			}
			if(p instanceof NonAlimentare){
				NonAlimentare n = (NonAlimentare) p;
				lista[numProdotti++] = new NonAlimentare(n);
			}
			//lista[numProdotti++] = p;
		} else{
			System.out.println("la lista e' piena");
		}
	}
	
	public void listaSpesa(){
		lista = new Prodotto[maxProdotti];
		numProdotti = 0;
	}
	
	public float calcolaSpesa(boolean sconto){
		float totale = 0;
		for(int i=0; i<numProdotti; i++){
			if(sconto)
				lista[i].applicaSconto();
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
