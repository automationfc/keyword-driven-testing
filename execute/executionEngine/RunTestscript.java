package executionEngine;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;

import keywords.ActionKeywords;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;

public class RunTestscript {

	public RunTestscript() throws NoSuchMethodException, SecurityException {
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception {
		ExcelUtils.setExcelFile(Constants.PATH_TEST_DATA);
		DOMConfigurator.configure("log4j.xml");

		RunTestscript startEngine = new RunTestscript();
		startEngine.execute_TestCase();
	}

	private void execute_TestCase() throws Exception {
		// Count all rows in sheet Testcase
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.SHEET_TESTCASES);
		Log.info("Total testcase = " + iTotalTestCases);
		// Loop for every row
		for (int iTestcase = 1; iTestcase < iTotalTestCases; iTestcase++) {
			// Set result = true
			bResult = true;
			// Get Testcase ID
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.TESTCASE_ID, Constants.SHEET_TESTCASES);
			Log.info("Total testcase = " + sTestCaseID);
			// Get Run Mode (If RunMode = Yes then Run Test)
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.RUNMODE, Constants.SHEET_TESTCASES);
			Log.info("Run mode = " + sRunMode);
			if (sRunMode.equals("Yes")) {
				// Start testcase
				Log.startTestCase(sTestCaseID);
				// Map Teststep ID from TestcaseID
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.TESTCASE_ID, Constants.SHEET_TESTSTEPS);
				Log.info("Teststep = " + iTestStep);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.SHEET_TESTSTEPS, sTestCaseID, iTestStep);
				Log.info("Last step = " + iTestLastStep);
				bResult = true;
				for (; iTestStep < iTestLastStep; iTestStep++) {
					// Get data of action keyword/ page object/ data set
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.ACTION_KEYWORDS, Constants.SHEET_TESTSTEPS);
					Log.info("Action keyword = " + sActionKeyword);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.PAGE_OBJECT, Constants.SHEET_TESTSTEPS);
					Log.info("Page Object = " + sPageObject);
					sData = ExcelUtils.getCellData(iTestStep, Constants.DATA_SET, Constants.SHEET_TESTSTEPS);
					Log.info("Data set = " + sData);
					// Run test
					execute_Actions();
					if (bResult == false) {
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.RESULT, Constants.SHEET_TESTCASES);
						Log.endTestCase(sTestCaseID);
						break;
					}
				}
				if (bResult == true) {
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.RESULT, Constants.SHEET_TESTCASES);
					Log.endTestCase(sTestCaseID);
				}
			}
		}
	}

	private static void execute_Actions() throws Exception {

		for (int i = 0; i < method.length; i++) {
			if (method[i].getName().equals(sActionKeyword)) {
				method[i].invoke(actionKeywords, sPageObject, sData);
				if (bResult == true) {
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.TEST_STEP_RESULT, Constants.SHEET_TESTSTEPS);
					break;
				} else {
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.TEST_STEP_RESULT, Constants.SHEET_TESTSTEPS);
					ActionKeywords.closeBrowser("", "");
					break;
				}
			}
		}
	}

	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];

	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static boolean bResult;
}