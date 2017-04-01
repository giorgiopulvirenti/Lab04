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

public class CorsiStudente {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getCorsiStudente(String codice) {

	 String sql = "SELECT corso.codins,corso.crediti,corso.nome,corso.pd "+
"FROM iscrizione, corso, studente "+
"WHERE studente.matricola=iscrizione.matricola && corso.codins=iscrizione.codins  && iscrizione.matricola=?" ;

		List<Corso> corsiStudente = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				corsiStudente.add(new Corso( rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd")));
						// Crea un nuovo JAVA Bean Corso
				
				// Aggiungi il nuovo Corso alla lista
				
			}

			return corsiStudente;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public boolean iscriviStudenteAlCorso(String s,int i){
		String sql="INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES (?, ?);";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, i);
			st.setString(2, s);
			int result = st.executeUpdate();
			if (result==1)
				return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return false;
}
}