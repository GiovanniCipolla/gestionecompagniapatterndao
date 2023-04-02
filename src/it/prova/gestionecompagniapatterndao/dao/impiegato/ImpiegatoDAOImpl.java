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

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO  {
	
	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	// -----------------------LIST---------------------------------
	public List list() throws Exception {
		
		if (isNotActive())
			throw new Exception("ERRORE DI CONNESSIONE");
//		inizializziamo una lista vuota
		List<Impiegato> result = new ArrayList<>();
		
		// scriviamo la qwery 
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from impiegato")) {
			
			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("ID"));
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// ------------------------------------- GET -----------------------------------
	public Impiegato get(Long idInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("TEST FAILED : CONNESSIONE");
		
		if (idInput == null || idInput < 1)
			throw new Exception("TEST FAILED : INPUT");
		
		Impiegato result = new Impiegato();
		
		try (PreparedStatement ps = connection.prepareStatement(
				"select * from impiegato i inner join compagnia c on c.id=i.compagnia_id where i.id=?")) {
			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = new Impiegato();
					result.setNome(rs.getString("nome"));
					result.setCognome(rs.getString("cognome"));
					result.setCodiceFiscale(rs.getString("codicefiscale"));
					result.setDataNascita(
							rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
					result.setDataAssunzione(
							rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
					result.setId(rs.getLong("i.ID"));

					Compagnia compagniaTemp = new Compagnia();
					compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					compagniaTemp.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
					compagniaTemp.setId(rs.getLong("c.id"));

					result.setCompagnia(compagniaTemp);

				} else {
					result = null;
				}
			} // niente catch

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	// ---------------------------------- update ----------------------------------------
	public int update(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("TEST FAILED : CONNESSIONE");
		
		if (input == null |input.getId() < 1)
			throw new Exception("TEST FAILED : INPUT");
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"update impiegato set nome=?, cognome=?, codicefiscale=?, datanascita=?, dataassunzione=?, compagnia_id=? where id=?; ")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataNascita()));
			ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
			ps.setLong(6, input.getCompagnia().getId());
			ps.setLong(7, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	// ------------------------------ insert --------------------------------------
	public int insert(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("TEST FAILED : CONNESSIONE");
		
		if (input == null)
			throw new Exception("TEST FAILED : INPUT");
		
		int result = 0;
		
		try (PreparedStatement ps = connection.prepareStatement(
				"insert into impiegato (nome, cognome, codicefiscale, datanascita, dataassunzione, compagnia_id) values (?, ?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataNascita()));
			ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
			ps.setLong(6, input.getCompagnia().getId());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	// ------------------------ DELETE ------------------------------------
	public int delete(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("TEST FAILED : CONNESSIONE");
		
		if (input == null ||input.getId() < 1)
			throw new Exception("TEST FAILED : INPUT");
		
		int result = 0;
		
		try (PreparedStatement ps = connection.prepareStatement("delete from impiegato where id=?;")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public List<Impiegato> findAllByCompagnia(Compagnia compagnia) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<Impiegato> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from impiegato i inner join compagnia c on c.id=i.compagnia_id where i.compagnia_id=?;")) {

			ps.setLong(1, compagnia.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("i.ID"));

				Compagnia compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaTemp.setId(rs.getLong("c.ID"));

				impiegatoTemp.setCompagnia(compagniaTemp);
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int countByDataFondazioneCompagniaGreaterThan(LocalDate data) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (data == null)
			throw new Exception("Valore di input non ammesso.");
		
		int result = 0;
		
		try (PreparedStatement ps = connection.prepareStatement(
				"select count(datafondazione) from impiegato i inner join compagnia c on c.id=i.compagnia_id where datafondazione > ?")) {
			ps.setDate(1, java.sql.Date.valueOf(data));

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = rs.getInt("count(datafondazione)");

				}
			} // niente catch

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<Impiegato> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from impiegato i inner join compagnia c on c.id=i.compagnia_id where c.fatturatoannuo > ?;")) {

			ps.setInt(1, fatturatoInput);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("i.ID"));

				Compagnia compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaTemp.setId(rs.getLong("c.ID"));

				impiegatoTemp.setCompagnia(compagniaTemp);
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Impiegato> findAllErroriAssunzione() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<Impiegato> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from impiegato i inner join compagnia c on c.id=i.compagnia_id where c.datafondazione > i.dataassunzione;")) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("i.ID"));

				Compagnia compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaTemp.setId(rs.getLong("c.ID"));

				impiegatoTemp.setCompagnia(compagniaTemp);
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}


	

}
