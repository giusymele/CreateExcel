package com.cExcel.utility;

import java.io.File;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class UtilityPDF {

	public HashMap<String, String> read() {
		HashMap<String, String> listaDomande = new HashMap<String, String>();

		try {
			
			File f = new File("/Users/giusy/Documents/0312209INGINF05 - BASI DI DATI (1).pdf");
			PDDocument document = PDDocument.load(f);

			if (!document.isEncrypted()) {
				String domanda = "";
				String risposta = "";
				String letteraRispostaCorretta;
				boolean domandaSenzaRisposta= false;

				PDFTextStripper pdfStripper = new PDFTextStripper();
				pdfStripper.setStartPage(1);
				// pdfStripper.setEndPage();

				// load all lines into a string
				String pages = pdfStripper.getText(document);
				// System.out.println("Text:" + pages);

				// split by detecting newline
				String[] lines = pages.split("\r\n|\r|\n");

				int rigaDellaDomandaDaRecuperare = -10;
				int count = 1; // Just to indicate line number
				for (String temp : lines) {
					// Verifico se la linea è la domanda
					if (domandaSenzaRisposta==false &&!temp.contains("A.") && !temp.contains("B.") && !temp.contains("C.") && !temp.contains("D.")
							&& !temp.contains("Answer") && !temp.contains("Section") && temp.charAt(0) != ' '
							&& !temp.contains("DO NOT PAY FOR THIS DOCUMENT") && !temp.contains("CAPITOLO") && !temp.contains("Capitolo")) {
						rigaDellaDomandaDaRecuperare = count;

						// elimino il numero della domanda
						if (temp.charAt(1) == '.') {
							domanda = temp.substring(2, temp.length());
						} else if (temp.charAt(2) == '.') {
							domanda = temp.substring(3, temp.length());
						} else if (temp.charAt(3) == '.') {
							domanda = temp.substring(4, temp.length());
						} else
							domanda = temp;

						domandaSenzaRisposta=true;
						System.out.println("domanda:" + domanda);

					}

					if (temp.contains("Answer")) {
						risposta = temp;
						letteraRispostaCorretta = temp.substring(temp.length()-2, temp.length()-1);
						System.out.println("risposta:" + risposta);
						System.out.println("lettera:" + letteraRispostaCorretta);
						domandaSenzaRisposta=false;
						for (int x = rigaDellaDomandaDaRecuperare; x < rigaDellaDomandaDaRecuperare+5; x++) {
							if(lines[x].charAt(0)==letteraRispostaCorretta.charAt(0)) {
								risposta=lines[x];
							}
						}
						System.out.println("risposta:" + risposta);

						listaDomande.put(domanda, risposta);

					}

					// System.out.println(count + " " + temp);
					count++;
				}

			}
			document.close();

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return listaDomande;
	}

}
