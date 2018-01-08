package utility;

import org.apache.log4j.Logger;

public class Log {

	// Initialize Log4j logs
	private static Logger log = Logger.getLogger(Log.class.getName());

	// This is to print log for the beginning of the test case, as we usually run so
	// many test cases as a test suite
	public static void startTestCase(String sTestCaseName) {
		System.out.println("START TESTCASE: " + sTestCaseName);
	}

	// This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName) {
		System.out.println("END TESTCASE: " + sTestCaseName);
	}

	// Need to create these methods, so that they can be called
	public static void info(String message) {
		log.info(message);
		System.out.println(message);
	}

	public static void warn(String message) {
		log.warn(message);
		System.out.println(message);
	}

	public static void error(String message) {
		log.error(message);
		System.out.println(message);
	}

	public static void fatal(String message) {
		log.fatal(message);
		System.out.println(message);
	}

	public static void debug(String message) {
		log.debug(message);
		System.out.println(message);
	}

}