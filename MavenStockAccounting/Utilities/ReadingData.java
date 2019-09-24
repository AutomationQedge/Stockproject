package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadingData 
{

	public static void main(String[] args) throws Throwable
	{

		Properties cp=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\prasanth.s\\workspace\\StockAccounting1\\PropertyFile\\Enviroment.properties");
		cp.load(fis);
		System.out.println(cp.getProperty("Browser"));
		System.out.println(cp.getProperty("URL"));
		System.out.println(cp.getProperty("UserName"));
		System.out.println(cp.getProperty("Password"));
	}

}
