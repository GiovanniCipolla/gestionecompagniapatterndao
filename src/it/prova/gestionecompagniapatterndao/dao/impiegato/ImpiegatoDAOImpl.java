package it.prova.gestionecompagniapatterndao.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.AbstractMySQLDAO;
import it.prova.gestionecompagniapatterndao.dao.impiegato.*;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpegatoDAO  {
	
	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	public List list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Impiegato get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByCompagnia(Compagnia compagnia) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int countByDataFondazioneCompagniaGreaterThan(LocalDate data) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllErroriAssunzione() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
