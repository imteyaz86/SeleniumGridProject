package com.test.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib {
    XSSFWorkbook workBook;
    XSSFSheet sheet;
	FileInputStream fileIOStream;
	private String strExcelPath;
	// ============================================================================================
	// FunctionName : ExcelDataRetrieval_Utility
	// Description : To Access The Required Excel File
	// Input Parameter : StrExcelPath Of The Type String Providing The Path Of
	// The Excel To Be Fetched
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public ExcelLib(String strExcelPath) throws Exception {
		try {
		    this.strExcelPath = strExcelPath;
			File filePath = new File(strExcelPath);
		    fileIOStream = new FileInputStream(filePath);
		    workBook = new XSSFWorkbook(fileIOStream);
			
		} catch (Exception e) {
			System.out.println("The ExcelFile Was Not Retrieved As" + e.getMessage());
			throw e;
		}
	}


	// ====================================================================================================
	// FunctionName : WriteDataToExcel
	// Description : To Write Data To An Excel File
	// Input Parameter : IntSheetNumber, IntRow, IntColumn Of The Type Integer,
	// strValueToWrite, StrExcelPath Of The Type String
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ====================================================================================================
	public synchronized void writeDataToExcel(String strSheetName, String rowName, String colName,
			String strValueToWrite) throws Exception {
	    String value = rowName;
		try {
			FileInputStream inputStream = new FileInputStream(strExcelPath);
			Workbook Wb = WorkbookFactory.create(inputStream);
			Sheet sheet = Wb.getSheet(strSheetName);
			Row row = sheet.getRow(excelGetRowNumber(strSheetName, rowName, 0));
			Cell cell = row.getCell(excelColNumber(strSheetName, colName));
			if (cell == null) {
				cell = row.createCell(excelColNumber(strSheetName, colName));
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(strValueToWrite);
			FileOutputStream outputStream = new FileOutputStream(strExcelPath);
			Wb.write(outputStream);
			outputStream.close();
			
			for (int i=0; i<100;i++) {
			    System.out.println("row name is : "+value);
			   // Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Exception Occurred While Writing Data To An Excel File" + e.toString());
throw e;
		}
	}


	// ====================================================================================================
	// FunctionName : ExcelStringFetchData_Utility
	// Description : To Read String Data From The Required Excel File
	// Input Parameter : String SheetName , String RowValue , IntColumn Of The
	// Type Integer
	// Revision : Imteyaz
	// ====================================================================================================
	public String excelStringFetchDataUtility(String strSheetName, String rowValue, int intColumn) {
		String strLocatorvalue;
		int rowNum = -1;
		int rowCount, i;

		sheet = workBook.getSheet(strSheetName);
		rowCount = sheet.getLastRowNum();
		
		for (i = 1; i <= rowCount; i++) {
			if (rowValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue().trim())) {
				rowNum = i;
				break;
			}

		}
		strLocatorvalue = sheet.getRow(rowNum).getCell(intColumn).getStringCellValue().trim();
		return strLocatorvalue.trim();
	}

	// ====================================================================================================
    // FunctionName : excelstringdataread_utility
    // Description : To Read String Data From The Required Excel File
    // Input Parameter : IntSheetNumber, IntRow, colName Of The Type String
    // Revision : 0.0 - Imteyaz Ahmad-07-11-2016
    // ====================================================================================================
    public String excelStringFetchDataUtility(String strSheetName, String rowValue, String colName)
            throws Exception, Error {
        String strLocatorvalue;
        int rowNum = -1;
        int rowCount, i;
        int colNum = -1;
        try {
            sheet = workBook.getSheet(strSheetName);
            rowCount = sheet.getLastRowNum();
            for (i = 1; i <= rowCount; i++) {
                if (rowValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue().trim())) {
                    rowNum = i;
                    break;
                }

            }
            colNum = excelColNumber(strSheetName, colName);
            strLocatorvalue = sheet.getRow(rowNum).getCell(colNum).getStringCellValue().trim();
            return strLocatorvalue.trim();
        } catch (Throwable e) {
            throw (e);
        }
    }
	// ============================================================================================
	// FunctionName : excelGetRowNumber
	// Description : Get the Row for the search value in particular column
	// Input Parameter : StrSheetName, strSearchValue, logval
	// Revision : 0.0 - Imteyaz Ahmad-20-10-2016
	// ============================================================================================

	public int excelGetRowNumber(String strSheetName, String strSearchValue, int colNumber) {
		int rowNum = -1;
		int rowCount, i;
		sheet = workBook.getSheet(strSheetName);
		rowCount = sheet.getLastRowNum();

		for (i = 1; i <= rowCount; i++) {
			if (strSearchValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(colNumber).getStringCellValue().trim())) {
				rowNum = i;
				break;
			}

		}

		return rowNum;
	}

	// ============================================================================================
	// FunctionName : excelColNumber
	// Description : Get the Row for the search value in particular column
	// Input Parameter : StrSheetName, strSearchValue, logval
	// Revision : 0.0 - Imteyaz Ahmad-04-11-2016
	// ============================================================================================

	public int excelColNumber(String strSheetName, String colName) {
		int colNum = -1;
		int colCount, i;

		try {
			sheet = workBook.getSheet(strSheetName);
			colCount = sheet.getRow(0).getLastCellNum();

			for (i = 1; i <= colCount; i++) {
				if (colName.trim().equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue().trim())) {
					colNum = i;
					break;
				}

			}
			return colNum;
		} catch (Exception | Error e) {
			throw (e);
		}
	}
}
