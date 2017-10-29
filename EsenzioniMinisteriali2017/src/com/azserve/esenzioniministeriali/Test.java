package com.azserve.esenzioniministeriali;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test {

	private static final String URL1 = "http://www.salute.gov.it/BancheDati/anagrafi/MCR";
	private static final String URL2 = "http://www.salute.gov.it/BancheDati/anagrafi/ricercaMCR";
	private static final String PREFIX_ELENCO = "http://www.salute.gov.it/BancheDati/anagrafi";
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Response res = 
				Jsoup.connect(URL1).method(Method.GET)
				.timeout(20000).execute();
		String jsessionId = res.cookie("JSESSIONID");
		String cookiesession1 = res.cookie("cookiesession1");
		String dtCookie = res.cookie("dtCookie");
		System.out.println(jsessionId + "\n"+cookiesession1);

		Document document = Jsoup.connect(URL2)
				.data("malattia", "")
				.data("esenzione","001")
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
//				.header("cookie", "cb-enabled=accepted; pr_cy=1; cookiesession1=37C057354LUVWTP8TLTSGOFMMSDOF5B7; JSESSIONID=00005tld6uM6lNfdOUccVbNZ5BR:19e97823t; _ga=GA1.3.1226356921.1509103577; _gid=GA1.3.1910629967.1509103577; _gat=1; dtPC=-; dtLatC=1; dtCookie=E2FE111B1DF3C2A6E28A54F6D7596BB0|X2RlZmF1bHR8MQ; dtSa=true%7CC%7C-1%7CCerca%7C-%7C1509301130933%7C301113666_463%7Chttp%3A%2F%2Fwww.salute.gov.it%2FBancheDati%2Fanagrafi%2FMCR%7CBanca%20dati%20delle%20malattie%20croniche%20esenti%7C1509301118029%7C")
				.userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36")
				.timeout(200000)
				.cookie("JSESSIONID", jsessionId)
				.cookie("cookiesession1",cookiesession1)
				.cookie("dtCookie", dtCookie)
				.post();
		
		String s = document.html();
		System.out.println(s);
		
		
		//vedi elenco
		Element es = document.select(".btnElencoPrestazioni").first();
		String link = es.attr("href");
		link = PREFIX_ELENCO+"/"+link;
		System.out.println(link);
		Document elenco = Jsoup.connect(link)
				.cookie("JSESSIONID", jsessionId)
				.cookie("cookiesession1",cookiesession1)
				.cookie("dtCookie", dtCookie)
				.get();
		System.out.println("\n\n\n****************************** DETTAGLI\n\n\n");
		System.out.println(elenco.html());
	}

}
