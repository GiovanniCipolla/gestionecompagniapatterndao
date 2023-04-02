package it.prova.gestionecompagniapatterndao.dao.compagnia;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	
	// ESERCIZIO 1
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate data) throws Exception;
	// ESERCIZIO 2
	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception;
	// ESERCIZIO 3
	public List<Compagnia> findAllByCodFisImpiegatoContiene (String codFisInput) throws Exception;
}

