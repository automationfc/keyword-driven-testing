package utility;

public class Constants {
	public static final String PROJECT_PATH = System.getProperty("user.dir");

	// System Variables
	public static final String URL = "http://demo.guru99.com/v4";
	public static final String PATH_TEST_DATA = PROJECT_PATH + "\\data\\NewCustomer.xlsx";
	public static final String PATH_TEXT_FILE = PROJECT_PATH + "\\common\\utility\\OR.txt";
	public static final String EXCEL_FILE_NAME = "DataEngine.xlsx";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";

	// Test Cases Sheet
	public static final int RUNMODE = 2;
	public static final int RESULT = 3;
	
	// Test Steps Sheet
	public static final int TESTCASE_ID = 0;
	public static final int TESTSCENARIO_ID = 1;

	public static final int PAGE_OBJECT = 4;
	public static final int ACTION_KEYWORDS = 5;
	public static final int DATA_SET = 6;
	public static final int TEST_STEP_RESULT = 7;

	// Excel sheet
	public static final String SHEET_TESTSTEPS = "Test Steps";
	public static final String SHEET_TESTCASES = "Test Cases";

	// Test Data
	public static final String USERNAME = "mngr260207";
	public static final String PASSWORD = "ehehege";
}
