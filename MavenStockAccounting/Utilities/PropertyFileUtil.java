package Utilities;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String Key) throws Throwable
	{
		Properties cp=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\prasanth.s\\workspace\\StockAccounting\\PropertyFile\\Enviroment.properties");
		cp.load(fis);
		
		return cp.getProperty(Key);
	}
}
