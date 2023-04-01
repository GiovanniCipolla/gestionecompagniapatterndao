package it.prova.gestionecompagnia.test;

import java.sql.Connection;

import it.prova.connection.MyConnection;
import it.prova.gestionecompagnia.dao.Constants;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;

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
