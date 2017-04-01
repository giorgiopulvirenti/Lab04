package it.polito.tdp.lab04.controller;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {


	private Model model= new Model();
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader
	
	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {

	}

	@FXML
	void doReset(ActionEvent event) {
		this.txtCognome.clear();
		this.txtNome.clear();
		this.txtMatricola.clear();
	}
	
	@FXML
    void doCerca(ActionEvent event) {
		if(model.StudenteIscrittoCorso(this.comboCorso.getValue().getCodice(), Integer.parseInt(this.txtMatricola.getText())))
			this.txtResult.setText("Studente iscritto al corso");
		else
			this.txtResult.setText("Studente non iscritto al corso");
		

    }


	@FXML
	void doCercaNome(ActionEvent event) {
		this.txtNome.setText(model.getNomeStudente(Integer.parseInt(this.txtMatricola.getText())));
		this.txtCognome.setText(model.getCognomeStudente(Integer.parseInt(this.txtMatricola.getText())));
	

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
	
		
		if (this.comboCorso.getValue().getNome().equals("")){
			this.txtResult.setText("nessun corso selezionato");
			this.txtResult.setVisible(true);
			return;
		}
		
		this.txtResult.setText(model.getIscrittiCorso(this.comboCorso.getValue().getCodice()));
		
		
		
			
			

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		if (model.matricolaPresente(Integer.parseInt(this.txtMatricola.getText())))
this.txtResult.setText(model.getCorsiStudente((this.txtMatricola.getText())));
		else 
			this.txtResult.setText("matricola errata");
		
	}

	@FXML
	void doIscrivi(ActionEvent event) {
	
		if (!model.matricolaPresente(Integer.parseInt(this.txtMatricola.getText()))){
			this.txtResult.setText("matricola errata");
		return;
	}
		if (this.comboCorso.getValue().getNome().equals("")){
			this.txtResult.setText("nessun corso selezionato");
			return;
		}
		if(model.StudenteIscrittoCorso(this.comboCorso.getValue().getCodice(), Integer.parseInt(this.txtMatricola.getText()))){
			this.txtResult.setText("Studente già iscritto al corso");
	return;
	}
		model.IscriviStudenteCorso(this.comboCorso.getValue().getCodice(), Integer.parseInt(this.txtMatricola.getText()));
		this.txtResult.setText("Studente iscritto al corso");
	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		
		this.comboCorso.getItems().addAll(model.getCorsi());
		this.comboCorso.getItems().addAll(new Corso("ciao",0,"",0));
		
	}

}
