package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.dao.Constants;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public class testCompagnia {
	public static void main(String[] args) {

		CompagniaDAO compagniaDaoInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDaoInstance = new CompagniaDAOImpl(connection);
		// =======================================TEST================================
		
//		testGetCompagnia(compagniaDaoInstance);
		
//		TestUpdateCompagnia(compagniaDaoInstance);
		
//		testDeleteCompagnia(compagniaDaoInstance);
			
//		testInsertCompagnia(compagniaDaoInstance);
		
//	    testFindAllByDataAssunzioneMaggioreDi(compagniaDaoInstance);
//		
//	    testFindAllByRagioneSocialeContiene(compagniaDaoInstance);
//		
//	    testFindAllByCodFisImpiegatoContiene(compagniaDaoInstance);
		
		
		
		
		
		
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//---------------------------------------------- TEST DEI MOETODI-----------------------------
	
	// -------------------------------------------- TEST GET ------------------------------------
	private static void testGetCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		
		System.out.println("............testGetCompagnia inizio...........");
		// carichiamo la lista con le compagnie presenti nel database
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		// verifichiamo se ha almeno un elemento
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("TEST FAILED : non ci sono elementi");
		// carichiamo la compagnia dda prendere
		Compagnia compagniaDaLeggere = elencoVociPresenti.get(0);
		
		Compagnia compagniaDaCercare = compagniaDAOInstance.get(compagniaDaLeggere.getId());
		if (compagniaDaCercare == null )
			throw new RuntimeException("TEST FAILED: verifica della compagnia andata male");
		
		System.out.println(compagniaDaCercare);
		System.out.println("....................testGetCompagnia fine: PASSED......................");
	}
	
	//-------------------------------------------TEST  UPDATE---------------------------------
	
	private static void TestUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("...............TestUpdateCompagnia inizio..................");
		
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("TEST FAILED: non ci sono elementi");
		
		Compagnia compagniaDaModificare = elencoVociPresenti.get(0);
		
		String nomeNuovo = "noi immortali";
		
		System.out.println("before update: " + compagniaDaModificare);
		
		compagniaDaModificare.setRagioneSociale(nomeNuovo);
		
		int result=compagniaDAOInstance.update(compagniaDaModificare);
		Long idAggiornato = compagniaDaModificare.getId();
		
		Compagnia compagniaAggiornata = compagniaDAOInstance.get(idAggiornato);
		
		System.out.println("after update: " + compagniaAggiornata);
		
		if (compagniaAggiornata == null || !compagniaAggiornata.getRagioneSociale().equals(nomeNuovo)|| result < 1)
			throw new RuntimeException("TEST FAILED");
		
		System.out.println("....................TEST PASSED....................");
	}
	
	
	// ---------------------------------------TEST DELETE-----------------------

	private static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("..............testDeleteCompagnia inizio..............");
		
		int quantiIseriti = compagniaDAOInstance
				.insert(new Compagnia("NOI DEI PC", 10000, LocalDate.now()));
		
		if (quantiIseriti < 1)
			throw new RuntimeException("TEST FAILED");
		
		List<Compagnia> elementiPresenti = compagniaDAOInstance.list();
		
		int quantiPrimaDelete = elementiPresenti.size();
		
		if (quantiPrimaDelete < 1)
			throw new RuntimeException("TEST FAILED");
		
		Compagnia compagniaDaEliminare = elementiPresenti.get(quantiPrimaDelete - 1);
		
		
		int result = compagniaDAOInstance.delete(compagniaDaEliminare);
		
		int quantiDopoDelete = compagniaDAOInstance.list().size();
		
		if (quantiDopoDelete != quantiPrimaDelete - 1 || result < 1)
			throw new RuntimeException("TEST FAILED: il test non è andato a buon fine");

		System.out.println(".......testDeleteCompagnia fine: PASSED......");
	}
	
	// ------------------------------- TEST INSERT -------------------------
	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		
		System.out.println(".....................testInsertCompagnia inizio................");
		
		int quantiElementiInseriti = compagniaDAOInstance
				.insert(new Compagnia("cronache di golf", 60000, LocalDate.now()));
		
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("TEST FAILED");
		
		System.out.println(".......testInsertCompagnia fine: PASSED......");
	}
	
	// ----------------------------- TEST FIND COMPAGNIA CON DATA ASSUNZIONE MAGGIORE----------
	private static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAOInstance)throws Exception{
		System.out.println(".....................testFindAllByDataAssunzioneMaggioreDi inizio................");
		
		LocalDate data = LocalDate.parse("2020-01-01");
		
		List<Compagnia> elementiPresenti = compagniaDAOInstance.findAllByDataAssunzioneMaggioreDi(data);
		if(elementiPresenti.isEmpty())
			throw new RuntimeException("TEST FAILED: DB VUOTO");
		
		for (Compagnia compagniaItem : elementiPresenti) {
			System.out.println(compagniaItem);
		}
		
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi fine: PASSED......");
		
	}
	
	//----------------------------------- TEST findAllByRagioneSocialeContiene-----------------------
	private static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAOInstance)throws Exception{
		System.out.println(".....................testFindAllByRagioneSocialeContiene inizio................");
		
		String daCercare = "no";
		
		List<Compagnia> elementiPresenti = compagniaDAOInstance.findAllByRagioneSocialeContiene(daCercare);
		if(elementiPresenti.isEmpty())
			throw new RuntimeException("TEST FAILED: DB VUOTO");
		
		for (Compagnia compagniaItem : elementiPresenti) {
			System.out.println(compagniaItem);
		}
		
		System.out.println(".......testFindAllByRagioneSocialeContiene fine: PASSED......");
		
	}
	
	//------------------------------- testFindAllByCodFisImpiegatoContiene ---------------
	private static void testFindAllByCodFisImpiegatoContiene(CompagniaDAO compagniaDAOInstance)throws Exception{
		System.out.println(".....................testFindAllByCodFisImpiegatoContiene inizio................");
		
		String daCercare = "efe342423";
		
		List<Compagnia> elementiPresenti = compagniaDAOInstance.findAllByCodFisImpiegatoContiene(daCercare);
		if(elementiPresenti.isEmpty())
			throw new RuntimeException("TEST FAILED: DB VUOTO");
		
		for (Compagnia compagniaItem : elementiPresenti) {
			System.out.println(compagniaItem);
		}
		
		System.out.println(".......testFindAllByCodFisImpiegatoContiene fine: PASSED......");
	}
	
}
