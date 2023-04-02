package it.prova.gestionecompagniapatterndao.model;

import java.time.LocalDate;
import java.util.List;

public class Compagnia {
	
	private Long id;
	private String ragioneSociale;
	private int fatturatoAnnuo;
	private LocalDate dataFondazione;
	private List<Impiegato> impiegati;
	
	public Compagnia() {}

	public Compagnia(String ragioneSociale, int fatturatoAnnuo,LocalDate datafondazione) {
		this.ragioneSociale=ragioneSociale;
		this.fatturatoAnnuo=fatturatoAnnuo;
		this.dataFondazione=datafondazione;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Impiegato> getImpiegati() {
		return impiegati;
	}


	public void setImpiegati(List<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}


	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public int getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(int fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public LocalDate getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(LocalDate dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	@Override
	public String toString() {
		return "Compagnia [id=" + id + ", ragioneSociale=" + ragioneSociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ ", dataFondazione=" + dataFondazione + "]";
	}
	
	
	
}
