package it.roxanarotaru.prodotti;

public class ListaSPesaTest {
	public static void main(String[] args) {
		ListaSpesa P = new ListaSpesa(true);
		Prodotto p2 = new Prodotto("1234567","pane",12);
		Prodotto p3 = new Prodotto("1234425","latte",14);
		System.out.println(P);
		//System.out.print(P.aggiungiProdotto());
		P.aggiungiProdotto(p2);
		P.aggiungiProdotto(p3);
		
		
		Alimentare a = new Alimentare("aksjdfj", "banane", 100.0f, new Data(26,1,2016));
		P.aggiungiProdotto(a);
		
		NonAlimentare p4= new NonAlimentare("ksfibqok","quaderno",150.0f, "carta");
		P.aggiungiProdotto(p4);
		
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(a);
		System.out.println(p4);
		System.out.println("Il Totale è: ");
		
		System.out.println(P.calcolaSpesa());

	}
}
