package com.azserve.esenzioniministeriali;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.fit.pdfdom.PDFToHTML;

public class TestPdf {

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		// load the PDF file using PDFBox
//		PDDocument pdf = PDDocument.load(new java.io.File("C:/eumed/esenzioni/ministero/esenzioniministero-rotated.pdf"));
//		// create the DOM parser
//		PDFDomTree parser = new PDFDomTree();
//		StringWriter sw = new StringWriter();
//		parser.writeText(pdf, sw);
//		
////		String r = new String(sw.toString().getBytes(),"UTF-8");
////		System.out.println(r);
//		
//		System.out.println(sw.toString());
//		
		String[] as = {"C:/eumed/esenzioni/ministero/esenzioniministero-rotated.pdf"};
		PDFToHTML.main(as);
	}
}
