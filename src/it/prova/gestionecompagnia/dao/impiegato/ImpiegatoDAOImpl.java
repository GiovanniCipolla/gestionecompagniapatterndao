package it.prova.gestionecompagnia.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagnia.dao.AbstractMySQLDAO;
import it.prova.gestionecompagnia.dao.impiegato.*;
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

	public List findAllWhereDateCreatedGreaterThan(LocalDate dateCreatedInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByCognome(String cognomeInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByLoginIniziaCon(String caratteriInizialiInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Impiegato findByLoginAndPassword(String loginInput, String passwordInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByPasswordIsNull() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
