package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

import executionEngine.RunTestscript;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			Log.error("Class Utils | Method setExcelFile | Exception desc : " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static String getCellData(int rowNumber, int columnNumber, String sheeetName) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheeetName);
			Cell = ExcelWSheet.getRow(rowNumber).getCell(columnNumber);
			String cellData = Cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			Log.error("Class Utils | Method getCellData | Exception desc : " + e.getMessage());
			RunTestscript.bResult = false;
			return "";
		}
	}

	public static int getRowCount(String sheeetName) {
		int iNumber = 0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheeetName);
			iNumber = ExcelWSheet.getLastRowNum() + 1;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowCount | Exception desc : " + e.getMessage());
			RunTestscript.bResult = false;
		}
		return iNumber;
	}

	public static int getRowContains(String sTestCaseName, int colNum, String sheeetName) throws Exception {
		int iRowNum = 0;
		try {
			// ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(sheeetName);
			for (; iRowNum < rowCount; iRowNum++) {
				if (ExcelUtils.getCellData(iRowNum, colNum, sheeetName).equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : " + e.getMessage());
			RunTestscript.bResult = false;
		}
		return iRowNum;
	}

	public static int getTestStepsCount(String sheeetName, String sTestCaseID, int iTestCaseStart) throws Exception {
		try {
			for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(sheeetName); i++) {
				if (!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.TESTCASE_ID, sheeetName))) {
					int number = i;
					return number;
				}
			}
			ExcelWSheet = ExcelWBook.getSheet(sheeetName);
			int number = ExcelWSheet.getLastRowNum() + 1;
			return number;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : " + e.getMessage());
			RunTestscript.bResult = false;
			return 0;
		}
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String result, int rowNumber, int columnNumber, String sheetName) throws Exception {
		try {

			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			Row = ExcelWSheet.getRow(rowNumber);
			Cell = Row.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(columnNumber);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(Constants.PATH_TEST_DATA);
			ExcelWBook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PATH_TEST_DATA));
		} catch (Exception e) {
			RunTestscript.bResult = false;

		}
	}

}