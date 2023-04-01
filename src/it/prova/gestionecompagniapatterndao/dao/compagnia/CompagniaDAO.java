package it.prova.gestionecompagniapatterndao.dao.compagnia;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	
//	//
//	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataFondazione)throws Exception;
	
	public Compagnia findAllByRagioneSocialeContiene(String ragioneSociale)throws Exception;
	
	public Compagnia findAllByCodFisImpiegatoContieneContiene(String codFis)throws Exception;
}

