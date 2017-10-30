package com.azserve.esenzioniministeriali;

public class InfoPrestazioneEsenzione {

	private String codicePrestazione;
	private String descrizionePrestazione;
	private String frequenza;
	private String limitazione;
	private String note;
	public InfoPrestazioneEsenzione() {
		super();
	}
	public String getCodicePrestazione() {
		return codicePrestazione;
	}
	public void setCodicePrestazione(String codicePrestazione) {
		this.codicePrestazione = codicePrestazione;
	}
	public String getDescrizionePrestazione() {
		return descrizionePrestazione;
	}
	public void setDescrizionePrestazione(String descrizionePrestazione) {
		this.descrizionePrestazione = descrizionePrestazione;
	}
	public String getFrequenza() {
		return frequenza;
	}
	public void setFrequenza(String frequenza) {
		this.frequenza = frequenza;
	}
	public String getLimitazione() {
		return limitazione;
	}
	public void setLimitazione(String limitazione) {
		this.limitazione = limitazione;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String sintesi(){
		return codicePrestazione+"|"+descrizionePrestazione+"|"+frequenza+"|"+limitazione+"|"+note;
	}
}
