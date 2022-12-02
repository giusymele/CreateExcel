package com.cExcel.cExcel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cExcel.utility.UtilityExcel;
import com.cExcel.utility.UtilityPDF;

@SpringBootApplication
public class CExcelApplication {

	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(CExcelApplication.class, args);

		UtilityPDF utiity = new UtilityPDF();

		//inserisco path del file excel
		String path = "/Users/giusy/Documents/file.xlsx";

		File fileTest = new File(path);
		UtilityExcel excel = new UtilityExcel();
		java.io.ByteArrayOutputStream stream = excel.listToExcel(utiity.read());

		try (OutputStream outputStream = new FileOutputStream(path)) {
			stream.writeTo(outputStream);
		}

	}

}
