package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;

import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.dao.Constants;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAOImpl;

public class testCompagnia {
	public static void main(String[] args) {

		CompagniaDAO compagniaDaoInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDaoInstance = new CompagniaDAOImpl(connection);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
