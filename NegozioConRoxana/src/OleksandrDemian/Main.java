package OleksandrDemian;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import it.roxanarotaru.prodotti.Alimentare;
import it.roxanarotaru.prodotti.Data;
import it.roxanarotaru.prodotti.ListaSpesa;
import it.roxanarotaru.prodotti.NonAlimentare;
import it.roxanarotaru.prodotti.Prodotto;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	DateTime dateTime;
	Button btnEliminadaspesa;

	protected Shell shell;
	private Text txtNomeprodotto;
	private Text txtPrezzo;
	private Text txtCodiceABarre;
	private Text material;
	private Button compra;
	private Button eliminaNegozio;
	private Button calcPrezzo;
	private Button btnTesseraFedelta;
	private Button btnCaricaprodotti;

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
		shell.setSize(551, 463);
		shell.setText("SWT Application");
		
		negozioLista = new List(shell, SWT.BORDER);
		negozioLista.setBounds(10, 31, 172, 267);
		
		//negozio = Functions.Load("Negozio");
		aggiornaNegozio();
		
		Label lblProdotti = new Label(shell, SWT.NONE);
		lblProdotti.setAlignment(SWT.CENTER);
		lblProdotti.setBounds(10, 10, 172, 15);
		lblProdotti.setText("Prodotti");
		
		spesaLista = new List(shell, SWT.BORDER);
		spesaLista.setBounds(223, 31, 172, 244);
		
		Label lblSpesa = new Label(shell, SWT.NONE);
		lblSpesa.setText("Spesa");
		lblSpesa.setAlignment(SWT.CENTER);
		lblSpesa.setBounds(223, 10, 172, 15);
		
		txtNomeprodotto = new Text(shell, SWT.BORDER);
		txtNomeprodotto.setText("NomeProdotto");
		txtNomeprodotto.setBounds(417, 32, 108, 21);
		
		txtPrezzo = new Text(shell, SWT.BORDER);
		txtPrezzo.setText("Prezzo");
		txtPrezzo.setBounds(417, 59, 108, 21);
		
		txtCodiceABarre = new Text(shell, SWT.BORDER);
		txtCodiceABarre.setText("Codice a barre");
		txtCodiceABarre.setBounds(417, 86, 108, 21);
		
		material = new Text(shell, SWT.BORDER);
		material.setText("Material");
		material.setBounds(417, 142, 108, 21);
		
		dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(417, 169, 108, 24);
		
		tipo = new Combo(shell, SWT.NONE);
		tipo.setItems(new String[] {"Alimentari", "Non alimentari"});
		tipo.setBounds(417, 113, 108, 15);
		tipo.setText("Tipo prodotto");
		tipo.select(0);
		
		Button btnAggiungiProdotto = new Button(shell, SWT.NONE);
		btnAggiungiProdotto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Prodotto p = new Prodotto();
				float prezzo = Prezzo(txtPrezzo.getText());
				
				if(prezzo != 0){
					if(tipo.getSelectionIndex() == 0){
						p = new Alimentare(txtNomeprodotto.getText(), txtCodiceABarre.getText(), prezzo, new Data(dateTime.getDay(), dateTime.getMonth()+1, dateTime.getYear()));
					}
					if(tipo.getSelectionIndex() == 1){
						p = new NonAlimentare(txtNomeprodotto.getText(), txtCodiceABarre.getText(), prezzo, material.getText());
					}
					negozio.aggiungiProdotto(p);
					aggiornaNegozio();
				}
				else{
					Functions.Errore("Erorre nel aggiungere prodotto");
				}
			}
		});
		
		btnAggiungiProdotto.setBounds(417, 199, 108, 25);
		btnAggiungiProdotto.setText("Aggiungi prodotto");
		
		compra = new Button(shell, SWT.NONE);
		compra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(negozioLista.getSelectionIndex() == -1){
					Functions.Info("Non hai scelto nessun prodotto");
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
		
		btnEliminadaspesa = new Button(shell, SWT.NONE);
		btnEliminadaspesa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(spesaLista.getSelectionIndex()!= -1){
					spesa.eliminaProdotto(spesaLista.getSelectionIndex());
					aggiornaSpesa();
				}
			}
		});
		btnEliminadaspesa.setBounds(223, 304, 172, 25);
		btnEliminadaspesa.setText("Elimina");
		
		btnTesseraFedelta = new Button(shell, SWT.CHECK);
		btnTesseraFedelta.setBounds(223, 282, 172, 16);
		btnTesseraFedelta.setText("Tessera fedelta");
		
		calcPrezzo = new Button(shell, SWT.NONE);
		calcPrezzo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Functions.Info("Il prezzo e' di: " +spesa.calcolaSpesa(btnTesseraFedelta.getSelection()));
			}
		});
		calcPrezzo.setBounds(223, 335, 172, 25);
		calcPrezzo.setText("Calcola prezzo\r\n");
		
		Label lblCreaProdotto = new Label(shell, SWT.NONE);
		lblCreaProdotto.setAlignment(SWT.CENTER);
		lblCreaProdotto.setBounds(417, 10, 108, 15);
		lblCreaProdotto.setText("Crea prodotto");
		
		Button btnSalvaprodotti = new Button(shell, SWT.NONE);
		btnSalvaprodotti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Functions.Salva(negozio, "Negozio.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvaprodotti.setBounds(10, 366, 172, 25);
		btnSalvaprodotti.setText("SalvaProdotti");
		
		btnCaricaprodotti = new Button(shell, SWT.NONE);
		btnCaricaprodotti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					negozio = Functions.Carica("Negozio.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				aggiornaNegozio();
			}
		});
		btnCaricaprodotti.setBounds(10, 397, 172, 25);
		btnCaricaprodotti.setText("CaricaProdotti");
		
		Button btnI = new Button(shell, SWT.NONE);
		btnI.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(negozioLista.getSelectionIndex() >= 0){
					if(negozio.getProdotto(negozioLista.getSelectionIndex()) instanceof Alimentare){
						Alimentare temp = (Alimentare)negozio.getProdotto(negozioLista.getSelectionIndex());
						Functions.Info(temp.toString());
					}
					if(negozio.getProdotto(negozioLista.getSelectionIndex()) instanceof NonAlimentare){
						NonAlimentare temp = (NonAlimentare)negozio.getProdotto(negozioLista.getSelectionIndex());
						Functions.Info(temp.toString());
					}
				}
			}
		});
		btnI.setBounds(188, 31, 29, 25);
		btnI.setText("i");
		
		Button btnSalvaspessa = new Button(shell, SWT.NONE);
		btnSalvaspessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Functions.Salva(negozio, "Spesa.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvaspessa.setBounds(223, 366, 172, 25);
		btnSalvaspessa.setText("SalvaSpesa");
		
		Button btnCaricaspesa = new Button(shell, SWT.NONE);
		btnCaricaspesa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					spesa = Functions.Carica("Spesa.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				aggiornaSpesa();
			}
		});
		btnCaricaspesa.setBounds(223, 397, 172, 25);
		btnCaricaspesa.setText("CaricaSpesa");
		
		Button btnApriFile = new Button(shell, SWT.NONE);
		btnApriFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell);
				fileDialog.setFilterExtensions(new String[]{"*.txt", "*.csv", "*.*"}); //opzionale
				String fileScelto = fileDialog.open();

				if(fileScelto != null) {
					MessageDialog.openInformation(shell, "File ", fileScelto);
					MessageDialog.openInformation(shell, "File (solo nome)", fileDialog.getFileName());
				}

			}
		});
		btnApriFile.setBounds(460, 304, 75, 25);
		btnApriFile.setText("Apri file");
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
	
	float Prezzo(String s){
		try{
			return Float.valueOf(s);
		} catch(NumberFormatException n){
			return 0;
		}
	}
}
