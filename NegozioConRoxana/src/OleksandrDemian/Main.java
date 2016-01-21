package OleksandrDemian;

import org.eclipse.swt.widgets.Display;
import it.roxanarotaru.*;
import it.roxanarotaru.prodotti.Data;
import it.roxanarotaru.prodotti.ListaSpesa;
import it.roxanarotaru.prodotti.Prodotto;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Main {
	ListaSpesa l;
	List negozioLista;
	List spesaLista;

	protected Shell shell;
	private Text txtNomeprodotto;
	private Text txtPrezzo;
	private Text txtCodiceABarre;
	private Text txtDescrizione;

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
		shell.setSize(650, 398);
		shell.setText("SWT Application");
		
		List negozioLista = new List(shell, SWT.BORDER);
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
		txtNomeprodotto.setBounds(516, 31, 108, 21);
		
		txtPrezzo = new Text(shell, SWT.BORDER);
		txtPrezzo.setText("Prezzo");
		txtPrezzo.setBounds(516, 58, 108, 21);
		
		txtCodiceABarre = new Text(shell, SWT.BORDER);
		txtCodiceABarre.setText("Codice a barre");
		txtCodiceABarre.setBounds(516, 85, 108, 21);
		
		txtDescrizione = new Text(shell, SWT.BORDER);
		txtDescrizione.setText("Descrizione");
		txtDescrizione.setBounds(516, 112, 108, 21);
		
		Button btnAggiungiProdotto = new Button(shell, SWT.NONE);
		btnAggiungiProdotto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Prodotto p = new Prodotto(txtNomeprodotto.getText(), txtCodiceABarre.getText(), txtDescrizione.getText(), txtPrezzo.getText());
				
			}
		});
		btnAggiungiProdotto.setBounds(516, 325, 108, 25);
		btnAggiungiProdotto.setText("Aggiungi prodotto");

	}
}
