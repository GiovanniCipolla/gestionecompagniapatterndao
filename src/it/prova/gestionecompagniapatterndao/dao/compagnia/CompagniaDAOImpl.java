package it.prova.gestionecompagniapatterndao.dao.compagnia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.AbstractMySQLDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public class CompagniaDAOImpl  extends AbstractMySQLDAO implements CompagniaDAO {
	
	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

//	//
//	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataFondazione){}
//	ssj
	
//	public Compagnia findAllByRagioneSocialeContiene(String ragioneSociale) {}
//	
	public Compagnia findAllByCodFisImpiegatoContieneContiene(String codFis) {
		
	}
	

}
