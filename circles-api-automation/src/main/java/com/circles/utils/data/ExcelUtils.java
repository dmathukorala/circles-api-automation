package com.circles.utils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private String file;
	private String sheet;
	private String extension;

	public ExcelUtils(String file, String sheet) {

		this.file = file;
		this.sheet = sheet;
		this.extension = FilenameUtils.getExtension(file);

	}

	public List<List<String>> readExcelData() throws Exception {

		if (file != null && sheet != null && (extension.equals("xls") || extension.equals("xlsx"))) {

			Sheet excelWSheet = getDataSheet(file, sheet, extension);
			return genarateDataList(excelWSheet);

		} else {
			throw new Exception("Invalid file");
		}

	}
	
	@SuppressWarnings("resource")
	private Sheet getDataSheet(String file, String sheet, String extention) throws Exception {

		try {
			
			FileInputStream fis = new FileInputStream(new File(file));
			Workbook excelWBook = (extention == "xls") ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);
			return excelWBook.getSheet(sheet);
			
		}
		catch (FileNotFoundException e) {
			throw e;
		} 
		catch (IOException e) {
			throw e;
		}

	}
	
	private List<List<String>> genarateDataList(Sheet excelWSheet) {

		List<List<String>> outer = new ArrayList<List<String>>();
		
		for(Row row : excelWSheet){
			if(row.getRowNum()==0) continue;
			List<String> inner = new ArrayList<String>();
			
			for(Cell cell : row){
				
				String cellValue = null;
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellValue = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cellValue = cell.getNumericCellValue() + "";
						break;
					case Cell.CELL_TYPE_BLANK:
						cellValue = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cellValue = Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_ERROR:
						cellValue = Byte.toString(cell.getErrorCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						cellValue = cell.getCellFormula();
						break;
					default:

				}
				
				inner.add(cellValue);
			}
			
			outer.add(inner);
		}
		
		return outer;

	}
	
}
