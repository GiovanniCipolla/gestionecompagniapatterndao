package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.dao.Constants;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagniapatterndao.dao.impiegato.ImpiegatoDAO;
import it.prova.gestionecompagniapatterndao.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class testImpiegato {

	public static void main(String[] args) {
		ImpiegatoDAO impiegatoDaoInstance = null;
		CompagniaDAO compagniaDaoInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			impiegatoDaoInstance = new ImpiegatoDAOImpl(connection);
			compagniaDaoInstance = new CompagniaDAOImpl(connection);
			// ================================= TEST
			// =======================================0

//		testInsertImpiegato(compagniaDaoInstance,impiegatoDaoInstance);

//		testGetImpiegato(impiegatoDaoInstance);

//		testUpdateImpiegato(impiegatoDaoInstance,compagniaDaoInstance);

//		testDeleteImpiegato(impiegatoDaoInstance);

		testFindAllByCompagnia(impiegatoDaoInstance,compagniaDaoInstance);
			
		testFindAllByCompagniaConFatturatoMaggioreDi(impiegatoDaoInstance,compagniaDaoInstance);
		
		testCountByDataFondazioneCompagniaGreaterThan(impiegatoDaoInstance,compagniaDaoInstance);
		
		testFindAllErroriAssunzione(impiegatoDaoInstance,compagniaDaoInstance);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------TEST DEI METODI
	// ------------------------------------

	// ----------------------------- TEST INSERT -----------------------

	private static void testInsertImpiegato(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance)
			throws Exception {
		System.out.println(".................testInsertImpiegato inizio...........");

		List<Compagnia> elementiPresenti = compagniaDAOInstance.list();

		if (elementiPresenti.size() < 1)
			throw new RuntimeException("TEST FAILED : DB VUOTO");

		Compagnia compagniaPerImpiegato = elementiPresenti.get(0);

		LocalDate dataNascita = LocalDate.parse("1998-01-28");
		LocalDate dataAssunzione = LocalDate.parse("2022-09-10");

		int quantiInseriti = impiegatoDAOInstance.insert(new Impiegato("Angelica", "Cast", "adadf66198fd89s8",
				dataNascita, dataAssunzione, compagniaPerImpiegato));

		if (quantiInseriti < 1)
			throw new RuntimeException("TEST FAILED");

		List<Impiegato> listaImpiegati = impiegatoDAOInstance.list();

		System.out.println(listaImpiegati);
		System.out.println(".......testInsertImpiegato fine: PASSED......");
	}

	// ------------------------ TEST GET ---------------------------

	private static void testGetImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {

		System.out.println("...........testGetImpiegato inizio..............");

		List<Impiegato> elementiPresenti = impiegatoDAOInstance.list();

		if (elementiPresenti.size() < 1)
			throw new RuntimeException("TEST FAILED");

		Impiegato impiegatoDaLeggere = elementiPresenti.get(0);

		if (impiegatoDaLeggere == null)
			throw new RuntimeException("TEST FAILED");

		System.out.println(impiegatoDaLeggere);
		System.out.println(".............TEST PASSED................");
	}

	// ---------------------------TEST UPDATE ------------------------------------

	private static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance)
			throws Exception {
		System.out.println("......................TestUpdateImpiegato inizio.................");

		List<Compagnia> elencoCompagnie = compagniaDAOInstance.list();

		if (elencoCompagnie.size() < 1)
			throw new RuntimeException("TEST FAILED");

		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();

		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("TEST PRESENTI");

		Impiegato impiegatoDaAggiornare = elencoImpiegatiPresenti.get(0);

		String nuovoNomeImpiegato = "Lebron";

		Compagnia nuovaCompagniaDaAssegnare = elencoCompagnie.get(1);

		System.out.println(nuovaCompagniaDaAssegnare);

		System.out.println("before update: " + impiegatoDaAggiornare);

		impiegatoDaAggiornare.setNome(nuovoNomeImpiegato);
		impiegatoDaAggiornare.setCompagnia(nuovaCompagniaDaAssegnare);

		impiegatoDAOInstance.update(impiegatoDaAggiornare);

		Long idImpiegatoAggiornato = impiegatoDaAggiornare.getId();

		Impiegato impiegatoAggiornato = impiegatoDAOInstance.get(idImpiegatoAggiornato);

		System.out.println("after update: " + impiegatoAggiornato);

		if (impiegatoAggiornato == null || !impiegatoAggiornato.getNome().equals(nuovoNomeImpiegato))
			throw new RuntimeException("TEST FAILED");

		System.out.println(".......TestUpdateImpiegato fine: PASSED......");
	}

	// --------------------------------TEST DELETE
	// ----------------------------------------------
	private static void testDeleteImpiegato(ImpiegatoDAO impiegatoDaoInstance) throws Exception {
		System.out.println("..............testDeleteImpiegato inizio..............");

		List<Impiegato> elementiPresenti = impiegatoDaoInstance.list();

		int quantiPrimaDelete = elementiPresenti.size();

		if (quantiPrimaDelete < 1)
			throw new RuntimeException("TEST FAILED");

		Impiegato impiegatoDaEliminare = elementiPresenti.get(quantiPrimaDelete - 1);

		int result = impiegatoDaoInstance.delete(impiegatoDaEliminare);

		int quantiDopoDelete = impiegatoDaoInstance.list().size();

		if (quantiDopoDelete != quantiPrimaDelete - 1 || result < 1)
			throw new RuntimeException("TEST FAILED: il test non è andato a buon fine");

		System.out.println(".......testDeleteImpiegato fine: PASSED......");
	}

//	// ------------testFindAllByRagioneSocialeContiene---------------------------
	private static void testFindAllByCompagnia(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance)
			throws Exception {
		System.out.println(".......testFindAllByCompagnia inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono impiegati sul DB");
		Compagnia compagniaDaRicercare = elencoCompagniePresenti.get(2);
		List<Impiegato> listaImpiegatiByCompagnia = impiegatoDAOInstance.findAllByCompagnia(compagniaDaRicercare);
		
		if (listaImpiegatiByCompagnia.size() < 1) {
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiByCompagnia.size());
		System.out.println(listaImpiegatiByCompagnia);
		System.out.println(".......testFindAllByCompagnia fine: PASSED.............");
	}

	
	private static void testCountByDataFondazioneCompagniaGreaterThan(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThan inizio......");
		
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"testCountByDataFondazioneCompagniaGreaterThan : FAILED, non ci sono compagnia sul DB");
		
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"testCountByDataFondazioneCompagniaGreaterThan : FAILED, non ci sono impiegati sul DB");
		
		LocalDate dataDaRicercare = LocalDate.parse("1980-01-01");
		int countImpiegati = impiegatoDAOInstance.countByDataFondazioneCompagniaGreaterThan(dataDaRicercare);
		System.out.println("Il contatore segna: " + countImpiegati);
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThan fine: PASSED.............");
	}
	
	private static void testFindAllByCompagniaConFatturatoMaggioreDi(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"testFindAllByCompagniaConFatturatoMaggioreDi : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"testFindAllByCompagniaConFatturatoMaggioreDi : FAILED, non ci sono impiegati sul DB");

		int fatturatoMaggioreDi = 8000;
		List<Impiegato> listaImpiegatiTrovata = impiegatoDAOInstance
				.findAllByCompagniaConFatturatoMaggioreDi(fatturatoMaggioreDi);
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiTrovata.size());
		System.out.println(listaImpiegatiTrovata);
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi fine: PASSED.............");
	}
	
	private static void testFindAllErroriAssunzione(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindAllErroriAssunzione inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllErroriAssunzione : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllErroriAssunzione : FAILED, non ci sono impiegati sul DB");

		List<Impiegato> listaImpiegatiTrovata = impiegatoDAOInstance.findAllErroriAssunzione();
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiTrovata.size());
		System.out.println(listaImpiegatiTrovata);
		System.out.println(".......testFindAllErroriAssunzione fine: PASSED.............");
	}

}
