package it.prova.gestionecompagniapatterndao.dao.impiegato;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public interface ImpiegatoDAO extends IBaseDAO<Impiegato> {
	
	// ESERCIZIO 1
	public List<Impiegato> findAllByCompagnia(Compagnia compagnia) throws Exception;
	// ESERCIZIO 2
	public int countByDataFondazioneCompagniaGreaterThan(LocalDate data) throws Exception;
	// ESERCIZIO 3
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception;
	// ESERCIZIO 4
	public List<Impiegato> findAllErroriAssunzione() throws Exception;

}
