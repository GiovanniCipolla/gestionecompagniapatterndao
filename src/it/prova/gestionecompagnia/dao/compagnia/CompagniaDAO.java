package it.prova.gestionecompagnia.dao.compagnia;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	
//	//
//	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataFondazione)throws Exception;
	
	public Compagnia findAllByRagioneSocialeContiene(String ragioneSociale)throws Exception;
	
	public Compagnia findAllByCodFisImpiegatoContieneContiene(String codFis)throws Exception;
}

