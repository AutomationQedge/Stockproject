package CommonFuntionalLibrary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{
WebDriver driver;

//startBrowser
// Return type is"driver" of type "WebDriver"
public static WebDriver startBrowser(WebDriver driver)throws Throwable
{
	 if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
	 {
		System.setProperty("webdriver.gecko.driver","");
		driver=new FirefoxDriver();
	 }
	else
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\prasanth.s\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
			driver=new ChromeDriver();
		}
	else
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
		{
				System.setProperty("webdriver.ie.driver","");
				driver=new InternetExplorerDriver();
		}
	return driver;
	
}

//openApplication
public static void openApplication(WebDriver driver)throws Throwable
{
	driver.get(PropertyFileUtil.getValueForKey("URL"));
	driver.manage().window().maximize();
}

//clickAction
public static void clickAction(WebDriver driver, String locatorType,String locatorValue)
{
	if(locatorType.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorValue)).click();
	}else
		if(locatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorValue)).click();
			}
}

//typeAction
public static void typeAction(WebDriver driver, String locatorType,String locatorValue,String data)
{
	if(locatorType.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorValue)).clear();
		driver.findElement(By.id(locatorValue)).sendKeys(data);
	}else
		if(locatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorValue)).clear();
			driver.findElement(By.name(locatorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorValue)).clear();
				driver.findElement(By.xpath(locatorValue)).sendKeys(data);
			}
}
//closeBrowser
public static void closeBrowser(WebDriver driver)
{
	driver.close();
}
//waitForElememt
public static void waitForElememt(WebDriver driver, String locatorType,String locatorValue,String waittime)
{
	WebDriverWait myWait=new WebDriverWait(driver, Integer.parseInt(waittime));
	if(locatorType.equalsIgnoreCase("id"))
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
	}else
		if(locatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}
}

//Scroll Down page
public static void pageDown(WebDriver driver)
{
	Actions pageDown =new Actions(driver);
	pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
}
// Mouse Hover  and ClicK
public static void mouseAction(WebDriver driver)
{
	Actions mouse=new Actions(driver);
	
	mouse.moveToElement(driver.findElement(By.id("mi_a_stock_items"))).build().perform();
	mouse.moveToElement(driver.findElement(By.id("mi_a_stock_categories"))).click().build().perform();
}

//Capture Data
public static void captureData(WebDriver driver ,String locatorValue ,String locatorType) throws Throwable
{
	String data="";
	if(locatorType.equalsIgnoreCase("id"))
	{
		data=driver.findElement(By.id(locatorValue)).getAttribute("value");
	}else
		if(locatorType.equalsIgnoreCase("name"))
		{
			data=driver.findElement(By.name(locatorValue)).getAttribute("value");
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
			}

FileWriter fw = new FileWriter("C:\\Users\\prasanth.s\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
BufferedWriter bw =new BufferedWriter(fw);
bw.write(data);

bw.flush();
bw.close();
}
//Table Validation1
public static  void tableValidation1(WebDriver driver ,String column) throws Throwable
{
	// Reading Data from Text File
	FileReader fr=new FileReader("C:\\Users\\prasanth.s\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
	BufferedReader br=new BufferedReader(fr);
	String exp_data=br.readLine();
	
	// Converting String Value to Integer
	int colNum=Integer.parseInt(column);
	// Search Panel
	if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel1"))).isDisplayed())
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel1"))).click();
		Thread.sleep(3000);
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box1"))).clear();
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box1"))).sendKeys(exp_data);
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button1"))).click();
	}
	else
	{
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box1"))).clear();
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box1"))).sendKeys(exp_data);
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button1"))).click();
	}
//Supplier WebTable
	WebElement webtable= driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable1")));
	List<WebElement> rows=webtable.findElements(By.tagName("tr"));
	
	for(int i=1 ;i<=rows.size(); i++)
	{
		// Capturing Supplier Number
		String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]"+"/div/span")).getText();
		System.out.println(act_data);	
		//validation
		Assert.assertEquals(exp_data, act_data);
		break;
	}
	
}

// Generate Date
public static String generateDate()
{
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss");
	return sdf.format(date);
}


// Table Validation 2

public static void tableValidation2(WebDriver driver,String exp_data) throws Throwable, Throwable
{
	
	 if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel2"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel2"))).click();
			
		    driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box2"))).clear();
		 
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box2"))).sendKeys(exp_data);
	
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button2"))).click();
		}else
		{
			
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box2"))).isDisplayed();
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box2"))).clear();
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box2"))).sendKeys(exp_data);
			driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button2"))).click();
		}
		 
		WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable2")));
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		 
		 for (int i=1; i <=rows.size(); i++)
		 {
			String act_data=driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
			System.out.println("Expected Data-->"+exp_data);
			System.out.println("Actual Data-->"+act_data);
			Assert.assertEquals(exp_data, act_data);
			break;
		 } 
}


}

