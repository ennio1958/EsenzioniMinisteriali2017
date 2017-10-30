package com.azserve.esenzioniministeriali;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Launch {


	private static final String FILE_ESENZIONI = "/res/elencocodiciesenzione";

	public static void main(String[] args) throws IOException {
		
		String fileName = System.getProperty("user.dir")+FILE_ESENZIONI;

		System.out.println("CodiceEsenzione|CodiceAccertamento|DescrizioneAccertamento|Frequenza|Limitazione|Note");

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(esenzione->{
				EsenzioneMinisteriale em = new EsenzioneMinisteriale(esenzione);
				List<InfoPrestazioneEsenzione> prestazioni = em.elencoPrestazioni();
				prestazioni.forEach(ic->{
					System.out.println(esenzione+"|"+ic.sintesi());
				});

			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
