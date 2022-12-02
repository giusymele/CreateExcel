package com.cExcel.utility;

import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class UtilityExcel {

	public ByteArrayOutputStream listToExcel(HashMap<String, String> list) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try (Workbook workbook = new XSSFWorkbook()) {

			Sheet sheet = workbook.createSheet("Lista Domanda");

			Row row = sheet.createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// Creating header
			Cell cell = null;

			cell = row.createCell(0);
			cell.setCellValue("DOMANDA");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(1);
			cell.setCellValue("RISPOSTA");
			cell.setCellStyle(headerCellStyle);

			// Creating data rows for each customer
			for (int i = 0; i < list.size(); i++) {
				Row dataRow = sheet.createRow(i + 1);
				// dataRow.createCell(0).setCellValue(listRecords.get(i).getFirstName());
				Object firstKey = list.keySet().toArray()[i];
				Object valueForFirstKey = list.get(firstKey);
				dataRow.createCell(0).setCellValue(firstKey.toString());
				dataRow.createCell(1).setCellValue(valueForFirstKey.toString());
			}

			// Making size of column auto resize to fit with data
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);

			workbook.write(outputStream);



		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return outputStream;

	}
	

}