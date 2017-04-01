package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsiStudente;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrittiCorso;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	CorsoDAO dao=new CorsoDAO();
	List<Corso> corsi ;
	StudenteDAO sdao=new StudenteDAO();
	List<Studente> studenti ;
	IscrittiCorso s1dao=new IscrittiCorso();
	List<Studente> iscritti ;
	CorsiStudente dao2=new CorsiStudente();
	List<Corso> corsiStudente ;
	
	public Model() {
		super();
		corsi =  dao.getTuttiICorsi();
		studenti=sdao.getTuttiStudenti();
//		iscritti=s1dao.getIscrittiCorso(codice);
	}



	
	public Collection getCorsi() {
		System.out.println("CIAOOOOOOO");
		return corsi;
	}
	public Collection getStudenti() {
		
		return studenti;
	}


		public String getIscrittiCorso(String codice){
			String c="";
			iscritti=s1dao.getIscrittiCorso(codice);
			for(Studente s:iscritti)
				c+=s.toString()+"\n";
			return c.trim();
		}
		public String getCorsiStudente(String codice){
			String c="";
			corsiStudente=dao2.getCorsiStudente(codice);
			for(Corso s: corsiStudente)
				c+=s.toString()+"\n";
			return c.trim();
		}

	public String getNomeStudente(int matricola) {
		for (Studente s:studenti)
			if (s.getMatricola()==matricola)
				return s.getNome();
		return studenti.get(studenti.indexOf(new Studente(matricola,"","",""))).getNome();
	}
	
public String getCognomeStudente(int matricola) {
		
		return studenti.get(studenti.indexOf(new Studente(matricola,"","",""))).getCognome();
	}
	public boolean matricolaPresente(int m){
		if (studenti.contains
				(
								new Studente(m,"","","")))
			return true;
			else return false;
	}
	public boolean StudenteIscrittoCorso(String s,int i){
		iscritti=s1dao.getIscrittiCorso(s);
		for (Studente f:iscritti)
			if (f.getMatricola()==i)
				return true;
		return false;
	}
	public boolean IscriviStudenteCorso(String s,int i){
		return dao2.iscriviStudenteAlCorso(s, i);
	}

}
