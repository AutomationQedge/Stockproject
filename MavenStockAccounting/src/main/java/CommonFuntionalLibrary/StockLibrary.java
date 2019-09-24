package CommonFuntionalLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockLibrary 
{
	WebDriver driver;
	String res;

	
	//Launch StockAccounting Application
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\prasanth.s\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
		// validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="PASS";
			
		}else
		{
			res="FAIL";
		}
		return res;
	}
	
	// Login into StockAccounting  Application
	
	public String appLogin(String username, String password)
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		
		// validation
		if(driver.findElement(By.id("logout")).isDisplayed())
		{
			res="PASS";
			
		}else
		{
			res="FAIL";
		}
		return res;
	}
	
	

	// Suppilers Creation
	
	public String suppliers(String sname,String add,String city, String country,String contactperson,String mobileNo ,String email, String phoneNo, String notes) throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//driver.findElement(By.id("mi_a_suppliers")).click();
		Thread .sleep(2000);
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']/a")).click();
		
		driver.findElement(By.xpath("//a[@href='a_suppliersadd.php?showdetail=']")).click();
		Thread .sleep(2000);
		String exp_data=driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		System.out.println("Expected data "+exp_data);
		
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.id("x_Address")).sendKeys(add);  
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(contactperson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(phoneNo);
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobileNo);
		driver.findElement(By.id("x_Notes")).sendKeys(notes);
		
		
		Actions pageDown=new Actions(driver);
		pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("btnAction")).click();
		Thread.sleep(3000);
		// Ok
		driver.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();	
		//ALert 
		driver.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
		Thread.sleep(1000);
		// validation
		
		
		if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed())
		{
			driver.findElement(By.xpath("//*[@class='btn btn-default ewSearchToggle']")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
		}else
		{
			Thread.sleep(3000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
		}
		
		String act_data=driver.findElement(By.id("el1_a_suppliers_Supplier_Number")).getText();
		System.out.println("Actual data"+act_data);
			if(exp_data.equals(act_data))
			{
				res="PASS";
			}
			else
			{
				res="FAIL";
			}
		
		
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		return res;
				
	}
	
	
	 
	//Logout From Stock Accounting Application
	
	public String appLogout() throws Throwable
	{
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();

		
		// validation
				if(driver.findElement(By.id("username")).isDisplayed())
				{
					res="PASS";
					
				}else
				{
					res="FAIL";
				}
				return res;
		 
	}
	
	
	// StockCatagories Creation 
		public String stockCatgories(String proName) throws Throwable
		{
			
			Actions mouse=new Actions(driver);
			mouse.moveToElement(driver.findElement(By.id("mi_a_stock_items"))).build().perform();
			mouse.moveToElement(driver.findElement(By.id("mi_a_stock_categories"))).click().build().perform();
			
			driver.findElement(By.xpath("//a[@href='a_stock_categoriesadd.php']")).click();
			
			driver.findElement(By.id("x_Category_Name")).sendKeys(proName);
			driver.findElement(By.id("btnAction")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
			
			
			if(driver.findElement(By.xpath("//*[@class='alert alert-success ewSuccess']")).isDisplayed())
			{
				res="PASS";
				
			}else
			{
				res="FAIL";
			}	
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
		
		
			return res;
							
		}
		
	
	
	// Closing Stock Accounting Application
	
	public void appClose()
	{
		driver.close();
	}
	
	public static void main(String[] args) throws Throwable 
	{
		/*FunctionalLibrary fl=new FunctionalLibrary ();
		String R1= fl.appLaunch("http://webapp.qedge.com/login.php");

		String R2= fl.appLogin("admin","master");
		String R3= fl.suppliers("Samsung","Ameerpet","Hyderabad","India","hari","555","ram@gmail.com","666","xyz");
		String R4= fl.stockCatgories("samsung");
		System.out.println(R1+"AppLaunched");
		System.out.println(R2+"Login Success");
		System.out.println(R3+"Detials Entered...");
		System.out.println(R4);
		fl.appLogout();
		fl.appClose();*/
	}

}
