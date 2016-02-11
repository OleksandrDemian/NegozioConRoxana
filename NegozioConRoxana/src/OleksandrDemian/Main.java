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
import org.eclipse.wb.swt.SWTResourceManager;

public class Main {
	ListaSpesa negozio = new ListaSpesa();
	ListaSpesa spesa = new ListaSpesa();
	List negozioLista;
	List spesaLista;
	Combo tipo;
	DateTime dateTime;
	Button btnEliminadaspesa;

	protected Shell shlNegozioDiRoxana;
	private Text txtNomeprodotto;
	private Text txtPrezzo;
	private Text txtCodiceABarre;
	private Text material;
	private Button compra;
	private Button eliminaNegozio;
	private Button calcPrezzo;
	private Button btnTesseraFedelta;
	private Button btnCaricaprodotti;
	private Text txtByRoxy;
	private Button button_1;

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
		shlNegozioDiRoxana.open();
		shlNegozioDiRoxana.layout();
		while (!shlNegozioDiRoxana.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlNegozioDiRoxana = new Shell();
		//shlNegozioDiRoxana.setSize(550, 562);
		shlNegozioDiRoxana.setBackgroundImage(SWTResourceManager.getImage("spesassss.jpg"));
		shlNegozioDiRoxana.setSize(550,550);
		//shlNegozioDiRoxana.setBackground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		//shlNegozioDiRoxana.setSize(550, 500);*/
		shlNegozioDiRoxana.setText("Negozio di Roxana e Demian");

		negozioLista = new List(shlNegozioDiRoxana, SWT.BORDER);
		negozioLista.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		negozioLista.setBounds(10, 31, 172, 267);
		
		try {
			negozio = Functions.Carica("Negozio.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aggiornaNegozio();
		aggiornaNegozio();
		
		Label lblProdotti = new Label(shlNegozioDiRoxana, SWT.NONE);
		lblProdotti.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblProdotti.setAlignment(SWT.CENTER);
		lblProdotti.setBounds(10, 10, 172, 15);
		lblProdotti.setText("Negozio");
		
		spesaLista = new List(shlNegozioDiRoxana, SWT.BORDER);
		spesaLista.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		spesaLista.setBounds(188, 31, 172, 236);
		
		Label lblSpesa = new Label(shlNegozioDiRoxana, SWT.NONE);
		lblSpesa.setForeground(SWTResourceManager.getColor(173, 255, 47));
		lblSpesa.setBackground(SWTResourceManager.getColor(0, 0, 0));
		lblSpesa.setText("Spesa");
		lblSpesa.setAlignment(SWT.CENTER);
		lblSpesa.setBounds(188, 10, 172, 15);
		
		txtNomeprodotto = new Text(shlNegozioDiRoxana, SWT.BORDER);
		txtNomeprodotto.setTouchEnabled(true);
		txtNomeprodotto.setText("NomeProdotto");
		txtNomeprodotto.setToolTipText("NomeProdotto");
		txtNomeprodotto.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtNomeprodotto.setBackground(SWTResourceManager.getColor(152, 251, 152));
		txtNomeprodotto.setBounds(417, 32, 108, 21);
		
		txtPrezzo = new Text(shlNegozioDiRoxana, SWT.BORDER);
		txtPrezzo.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtPrezzo.setBackground(SWTResourceManager.getColor(152, 251, 152));
		txtPrezzo.setText("Prezzo");
		txtPrezzo.setBounds(417, 59, 108, 21);
		
		txtCodiceABarre = new Text(shlNegozioDiRoxana, SWT.BORDER);
		txtCodiceABarre.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtCodiceABarre.setBackground(SWTResourceManager.getColor(152, 251, 152));
		txtCodiceABarre.setText("Codice a barre");
		txtCodiceABarre.setBounds(417, 86, 108, 21);
		
		material = new Text(shlNegozioDiRoxana, SWT.BORDER);
		material.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		material.setBackground(SWTResourceManager.getColor(152, 251, 152));
		material.setText("Material");
		material.setBounds(417, 142, 108, 21);
		
		dateTime = new DateTime(shlNegozioDiRoxana, SWT.BORDER);
		dateTime.setBackground(SWTResourceManager.getColor(152, 251, 152));
		dateTime.setBounds(417, 169, 108, 24);
		
		tipo = new Combo(shlNegozioDiRoxana, SWT.NONE);
		tipo.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		tipo.setBackground(SWTResourceManager.getColor(152, 251, 152));
		tipo.setItems(new String[] {"Alimentari", "Non alimentari"});
		tipo.setBounds(417, 113, 108, 15);
		tipo.setText("Tipo prodotto");
		tipo.select(0);
		
		Button btnAggiungiProdotto = new Button(shlNegozioDiRoxana, SWT.NONE);
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
		
		compra = new Button(shlNegozioDiRoxana, SWT.NONE);
		compra.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
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
		
		eliminaNegozio = new Button(shlNegozioDiRoxana, SWT.NONE);
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
		
		btnEliminadaspesa = new Button(shlNegozioDiRoxana, SWT.NONE);
		btnEliminadaspesa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(spesaLista.getSelectionIndex()!= -1){
					spesa.eliminaProdotto(spesaLista.getSelectionIndex());
					aggiornaSpesa();
				}
			}
		});
		btnEliminadaspesa.setBounds(188, 304, 172, 25);
		btnEliminadaspesa.setText("Elimina");
		
		btnTesseraFedelta = new Button(shlNegozioDiRoxana, SWT.CHECK);
		btnTesseraFedelta.setBounds(188, 335, 172, 25);
		btnTesseraFedelta.setText("Tessera fedelta");
		
		calcPrezzo = new Button(shlNegozioDiRoxana, SWT.NONE);
		calcPrezzo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String elenco = "";
				for(int i = 0; i < spesa.Lunghezza(); i++){
					elenco += spesa.getProdotto(i).getNome() + " - " + spesa.getProdotto(i).getPrezzo() + "euro\n";
				}
				elenco += "Totale: " + spesa.calcolaSpesa(btnTesseraFedelta.getSelection()) + " euro";
				//Functions.Info("Il prezzo e' di: " +spesa.calcolaSpesa(btnTesseraFedelta.getSelection()));
				Functions.Info(elenco);
			}
		});
		calcPrezzo.setBounds(188, 273, 172, 25);
		calcPrezzo.setText("Calcola prezzo\r\n");
		
		Label lblCreaProdotto = new Label(shlNegozioDiRoxana, SWT.NONE);
		lblCreaProdotto.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblCreaProdotto.setBackground(SWTResourceManager.getColor(165, 42, 42));
		lblCreaProdotto.setAlignment(SWT.CENTER);
		lblCreaProdotto.setBounds(417, 10, 108, 15);
		lblCreaProdotto.setText("Crea prodotto");
		
		Button btnSovrascrivi = new Button(shlNegozioDiRoxana, SWT.CHECK);
		btnSovrascrivi.setBounds(10, 366, 172, 24);
		btnSovrascrivi.setText("Sovrascrivi");
		
		Button btnSalvaprodotti = new Button(shlNegozioDiRoxana, SWT.NONE);
		btnSalvaprodotti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Functions.Salva(negozio, "Negozio.txt", btnSovrascrivi.getSelection());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvaprodotti.setBounds(10, 396, 172, 25);
		btnSalvaprodotti.setText("SalvaProdotti");
		
		btnCaricaprodotti = new Button(shlNegozioDiRoxana, SWT.NONE);
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
		btnCaricaprodotti.setBounds(10, 427, 172, 25);
		btnCaricaprodotti.setText("CaricaProdotti");
		
		Button btnI = new Button(shlNegozioDiRoxana, SWT.NONE);
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
		btnI.setBounds(366, 30, 29, 25);
		btnI.setText("i");
		
		Button btnSalvaspessa = new Button(shlNegozioDiRoxana, SWT.NONE);
		btnSalvaspessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Functions.Salva(negozio, "Spesa.txt", button_1.getSelection());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvaspessa.setBounds(188, 396, 172, 25);
		btnSalvaspessa.setText("SalvaSpesa");
		
		Button btnCaricaspesa = new Button(shlNegozioDiRoxana, SWT.NONE);
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
		btnCaricaspesa.setBounds(188, 427, 172, 25);
		btnCaricaspesa.setText("CaricaSpesa");
		
		Button btnApriFile = new Button(shlNegozioDiRoxana, SWT.NONE);
		btnApriFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shlNegozioDiRoxana);
				fileDialog.setFilterExtensions(new String[]{"*.txt", "*.csv", "*.*"}); //opzionale
				String fileScelto = fileDialog.open();

				if(fileScelto != null) {
					MessageDialog.openInformation(shlNegozioDiRoxana, "File ", fileScelto);
					MessageDialog.openInformation(shlNegozioDiRoxana, "File (solo nome)", fileDialog.getFileName());
				}

			}
		});
		btnApriFile.setBounds(417, 241, 75, 25);
		btnApriFile.setText("Apri file");
		
		txtByRoxy = new Text(shlNegozioDiRoxana, SWT.BORDER);
		txtByRoxy.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		txtByRoxy.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		txtByRoxy.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtByRoxy.setText("By Roxy & Demian");
		txtByRoxy.setBounds(391, 278, 134, 21);
		
		Button btnCancellaTutti = new Button(shlNegozioDiRoxana, SWT.NONE);
		btnCancellaTutti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				negozio.eliminaTuttiProdotti();
				aggiornaNegozio();
			}
		});
		btnCancellaTutti.setBounds(10, 458, 172, 25);
		btnCancellaTutti.setText("Cancella tutti");
		
		Button button = new Button(shlNegozioDiRoxana, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				spesa.eliminaTuttiProdotti();
				aggiornaSpesa();
			}
		});
		button.setText("Cancella tutti");
		button.setBounds(188, 458, 172, 25);
		
		button_1 = new Button(shlNegozioDiRoxana, SWT.CHECK);
		button_1.setText("Sovrascrivi");
		button_1.setBounds(188, 366, 172, 24);
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
