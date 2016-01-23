package OleksandrDemian;

import org.eclipse.swt.widgets.Display;
import it.roxanarotaru.*;
import it.roxanarotaru.prodotti.Alimentare;
import it.roxanarotaru.prodotti.Data;
import it.roxanarotaru.prodotti.ListaSpesa;
import it.roxanarotaru.prodotti.NonAlimentare;
import it.roxanarotaru.prodotti.Prodotto;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;

public class Main {
	ListaSpesa negozio = new ListaSpesa();
	ListaSpesa spesa = new ListaSpesa();
	List negozioLista;
	List spesaLista;
	Combo tipo;

	protected Shell shell;
	private Text txtNomeprodotto;
	private Text txtPrezzo;
	private Text txtCodiceABarre;
	private Text txtDescrizione;
	private Text type;
	private Button compra;
	private Button eliminaNegozio;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(588, 398);
		shell.setText("SWT Application");
		
		negozioLista = new List(shell, SWT.BORDER);
		negozioLista.setBounds(10, 31, 172, 267);
		
		Label lblProdotti = new Label(shell, SWT.NONE);
		lblProdotti.setAlignment(SWT.CENTER);
		lblProdotti.setBounds(10, 10, 172, 15);
		lblProdotti.setText("Prodotti");
		
		spesaLista = new List(shell, SWT.BORDER);
		spesaLista.setBounds(230, 31, 172, 267);
		
		Label lblSpesa = new Label(shell, SWT.NONE);
		lblSpesa.setText("Spesa");
		lblSpesa.setAlignment(SWT.CENTER);
		lblSpesa.setBounds(230, 10, 172, 15);
		
		txtNomeprodotto = new Text(shell, SWT.BORDER);
		txtNomeprodotto.setText("NomeProdotto");
		txtNomeprodotto.setBounds(450, 31, 108, 21);
		
		txtPrezzo = new Text(shell, SWT.BORDER);
		txtPrezzo.setText("Prezzo");
		txtPrezzo.setBounds(450, 58, 108, 21);
		
		txtCodiceABarre = new Text(shell, SWT.BORDER);
		txtCodiceABarre.setText("Codice a barre");
		txtCodiceABarre.setBounds(450, 85, 108, 21);
		
		txtDescrizione = new Text(shell, SWT.BORDER);
		txtDescrizione.setText("Descrizione");
		txtDescrizione.setBounds(450, 112, 108, 21);
		
		type = new Text(shell, SWT.BORDER);
		type.setText("gg/mm/aaaa");
		type.setBounds(450, 168, 108, 21);
		
		tipo = new Combo(shell, SWT.NONE);
		tipo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tipo.getSelectionIndex()== 0){
					type.setText("gg/mm/aaaa");
				}
				if(tipo.getSelectionIndex() == 1){
					type.setText("Materiale");
				}
			}
		});
		tipo.setItems(new String[] {"Alimentari", "Non alimentari"});
		tipo.setBounds(450, 139, 108, 15);
		tipo.setText("Tipo prodotto");
		tipo.select(0);
		
		Button btnAggiungiProdotto = new Button(shell, SWT.NONE);
		btnAggiungiProdotto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Prodotto p = new Prodotto();
				
				if(p.getPrezzo() != 0){
					if(tipo.getSelectionIndex() == 0){
						p = new Alimentare(txtNomeprodotto.getText(), txtCodiceABarre.getText(), txtDescrizione.getText(), txtPrezzo.getText(), type.getText());
					}
					if(tipo.getSelectionIndex() == 1){
						p = new NonAlimentare(txtNomeprodotto.getText(), txtCodiceABarre.getText(), txtDescrizione.getText(), txtPrezzo.getText(), type.getText());
					}
					negozio.aggiungiProdotto(p);
					aggiornaNegozio();
				}
				else{
					MessageDialog.openError(shell, "Erorre", "Erorre nel aggingere prodotto");
				}
			}
		});
		btnAggiungiProdotto.setBounds(450, 195, 108, 25);
		btnAggiungiProdotto.setText("Aggiungi prodotto");
		
		compra = new Button(shell, SWT.NONE);
		compra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(negozioLista.getSelectionIndex() == -1){
					MessageDialog.openError(shell, "Erorre", "Non hai scelto nessun prodotto");
				}
				else {
					spesa.aggiungiProdotto(negozio.getProdotto(negozioLista.getSelectionIndex()));
					aggiornaSpesa();
				}
			}
		});
		compra.setBounds(10, 304, 172, 25);
		compra.setText("Compra");
		
		eliminaNegozio = new Button(shell, SWT.NONE);
		eliminaNegozio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(negozioLista.getSelectionIndex()!= -1){
					negozio.eliminaProdotto(negozioLista.getSelectionIndex());
					aggiornaNegozio();
				}
			}
		});
		eliminaNegozio.setBounds(10, 335, 172, 25);
		eliminaNegozio.setText("Elimina");
	}
	
	void aggiornaSpesa(){
		spesaLista.removeAll();
		for(int i = 0; i < spesa.Lunghezza(); i++){
			spesaLista.add(spesa.getProdotto(i).getNome());
		}
	}
	
	void aggiornaNegozio(){
		negozioLista.removeAll();
		for(int i = 0; i < negozio.Lunghezza(); i++){
			negozioLista.add(negozio.getProdotto(i).getNome());
		}
	}
}
