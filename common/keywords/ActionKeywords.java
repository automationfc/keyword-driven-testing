package keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import executionEngine.RunTestscript;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.Constants;
import utility.Log;

public class ActionKeywords {

	public static WebDriver driver;

	public static void openBrowser(String locator, String browser) {
		Log.info("Opening Browser");
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				Log.info("Firefox browser started");
			} else if (browser.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				Log.info("IE browser started");
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				Log.info("Chrome browser started");
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			Log.info("Not able to open the Browser --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void navigate(String locator, String data) {
		try {
			Log.info("Navigating to URL");
			driver.get(Constants.URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			Log.info("Not able to navigate " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void click(String locator, String data) {
		try {
			Log.info("Clicking on Webelement " + locator);
			driver.findElement(By.xpath(locator)).click();
		} catch (Exception e) {
			Log.error("Not able to click --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void input(String locator, String data) {
		try {
			Log.info("Entering the text in " + locator);
			driver.findElement(By.xpath(locator)).sendKeys(data);
		} catch (Exception e) {
			Log.error("Not able to Enter UserName --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void sleep(String locator, String data) throws Exception {
		try {
			Log.info("Wait for 5 seconds");
			Thread.sleep(5000);
		} catch (Exception e) {
			Log.error("Not able to Wait --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void selectDropdownList(String locator, String data) throws Exception {
		try {
			Log.info("Select dropdown list");
			WebElement element = driver.findElement(By.xpath(locator));
			Select select = new Select(element);
			select.selectByVisibleText(data);
		} catch (Exception e) {
			Log.error("Can not select item in dropdown or not found item --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void selectDropdown(String locator, String data) {
		try {
			Log.info("Selecting to Dropdown/List " + locator);
			Select dropdown = new Select(driver.findElement(By.xpath(locator)));
			dropdown.selectByVisibleText(data);
		} catch (Exception e) {
			Log.error("Not able to select --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void isControlDisplayed(String locator, String data) {
		try {
			Log.info("Check WebElement is displayed " + locator);
			driver.findElement(By.xpath(locator)).isDisplayed();
		} catch (Exception e) {
			Log.error("Control not displayed --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void isControlSelected(String locator, String data) {
		try {
			Log.info("Check WebElement is displayed " + locator);
			driver.findElement(By.xpath(locator)).isSelected();
		} catch (Exception e) {
			Log.error("Control not displayed --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void closeBrowser(String locator, String data) {
		try {
			Log.info("Closing the browser");
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

}