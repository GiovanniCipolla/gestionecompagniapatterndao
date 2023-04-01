package it.prova.gestionecompagniapatterndao.dao.impiegato;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public interface ImpegatoDAO extends IBaseDAO<Impiegato> {
	
	public List<Impiegato> findAllByCompagnia(Int compagniaId)throws Exception;
	
//	public countByDataFondazioneCompagniaGreaterThan()throws Exception;
	
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput)throws Exception;
	
	public List<Impiegato> findAllErroriAssunzione()throws Exception;

}
