package it.prova.gestionecompagniapatterndao.model;

import java.time.LocalDate;

public class Impiegato {
	
	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private LocalDate dataNascita;
	private LocalDate dataAssunzione;
	private Compagnia compagnia;
	
	public Impiegato() {}

	public Impiegato(String nome,String cognome, String codiceFiscale,LocalDate dataNascita,LocalDate dataAssunzione,Compagnia compagnia) {
	this.nome=nome;
	this.cognome=cognome;
	this.codiceFiscale=codiceFiscale;
	this.dataNascita=dataNascita;
	this.dataAssunzione= dataAssunzione;
	this.compagnia=compagnia;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Compagnia getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(Compagnia compagnia) {
		this.compagnia = compagnia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", dataNascita=" + dataNascita + ", dataAssunzione=" + dataAssunzione + "]";
	}
	
	
	
}
