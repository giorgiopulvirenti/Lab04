package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class IscrittiCorso {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Studente> getIscrittiCorso(String codice) {

	 String sql = "SELECT studente.matricola,cognome,studente.nome,CDS "+
"FROM iscrizione, corso, studente "+
"WHERE studente.matricola=iscrizione.matricola && corso.codins=iscrizione.codins  && iscrizione.codins=?" ;

		List<Studente> iscritti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				iscritti.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS")));
							System.out.println(rs.getInt("matricola")+rs.getString("cognome")+rs.getString("nome")+rs.getString("CDS"));
				// Crea un nuovo JAVA Bean Corso
				
				// Aggiungi il nuovo Corso alla lista
				
			}

			return iscritti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}