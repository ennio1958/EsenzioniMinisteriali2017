package com.azserve.esenzioniministeriali;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EsenzioneMinisteriale {
	private static final String URL1 = "http://www.salute.gov.it/BancheDati/anagrafi/MCR";
	private static final String URL2 = "http://www.salute.gov.it/BancheDati/anagrafi/ricercaMCR";
	private static final String PREFIX_ELENCO = "http://www.salute.gov.it/BancheDati/anagrafi";
	
	private final String codiceEsenzione;
	
	private String jsessionId;
	private String cookiesession1;
	private String dtCookie;
	
	public EsenzioneMinisteriale(final String codiceEsenzione) {
		super();
		this.codiceEsenzione=codiceEsenzione;
	}
	
	public List<InfoPrestazioneEsenzione> elencoPrestazioni(){
		List<InfoPrestazioneEsenzione> elencoPrestazioni = new ArrayList<>();

		setCookies();
		
		String linkDettaglio = linkDettaglio();
		if(linkDettaglio==null)
			return elencoPrestazioni;
		
		Document elenco;
		try {
			elenco = Jsoup.connect(linkDettaglio)
					.cookie("JSESSIONID", jsessionId)
					.cookie("cookiesession1",cookiesession1)
					.cookie("dtCookie", dtCookie)
					.get();
			Element table = elenco.select("table").first();
			Elements righe = table.select("tr");
			righe.forEach(riga->{
				Elements colonne = riga.select("td");
				if(!colonne.isEmpty() && colonne.size()==5){
					InfoPrestazioneEsenzione ic = new InfoPrestazioneEsenzione();
					ic.setCodicePrestazione(colonne.get(0).text());
					ic.setDescrizionePrestazione(colonne.get(1).text());
					ic.setFrequenza(colonne.get(2).text());
					ic.setLimitazione(colonne.get(3).text());
					ic.setNote(colonne.get(4).text());
					elencoPrestazioni.add(ic);
				}
			});
			
			return elencoPrestazioni;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return elencoPrestazioni;
	}

	private String linkDettaglio() {
		Document document;
		try {
			document = Jsoup.connect(URL2)
					.data("malattia", "")
					.data("esenzione",codiceEsenzione)
					.data("ICD9CM","")
					.header("Host", "www.salute.gov.it")
					.header("Connection", "keep-alive")
					.header("Content-Length", "31")
					.header("Cache-Control", "max-age=0")
					.header("Origin", "http://www.salute.gov.it")
					.header("Upgrade-Insecure-Requests", "1")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Referer", "http://www.salute.gov.it/BancheDati/anagrafi/MCR")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
					.userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36")
					.timeout(200000)
					.cookie("JSESSIONID", jsessionId)
					.cookie("cookiesession1",cookiesession1)
					.cookie("dtCookie", dtCookie)
					.post();
			
			//vedi elenco
			Element es = document.select(".btnElencoPrestazioni").first();
			if(es==null)
				return null;
			String link = es.attr("href");
			link = PREFIX_ELENCO+"/"+link;
			return link;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setCookies() {
		Response res;
		try {
			res = Jsoup.connect(URL1).method(Method.GET)
			.timeout(20000).execute();
			jsessionId = res.cookie("JSESSIONID");
			cookiesession1 = res.cookie("cookiesession1");
			dtCookie = res.cookie("dtCookie");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
