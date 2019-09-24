package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
Workbook wb; //WorkBook InterFace
// loading Excel File
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\prasanth.s\\workspace\\StockAccounting\\TestInputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
// Row Count
    public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
// Column Count
    public int colCount(String sheetName,int row)
    {
    	return wb.getSheet(sheetName).getRow(row).getLastCellNum();
    }
//reading Data
	@SuppressWarnings("deprecation")
	public String getData(String sheetname,int row,int column)
	{
		String data="";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
				
		}
		else
		{
				
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
// Writing Data
	public void setData(String sheetname,int row, int column,String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum= sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("PASS"))
		{
			// Create CellStyle
			CellStyle style=wb.createCellStyle();
			// Create font
			Font font=wb.createFont();
			// apply Color
			font.setColor(IndexedColors.GREEN.index);
			// apply Bold
			font.setBold(true);
			// set Font
			style.setFont(font);
			// set Cell style
			rownum.getCell(column).setCellStyle(style);
		}
		else
			if(status.equalsIgnoreCase("FAIL"))
			{
				// Create CellStyle
				CellStyle style=wb.createCellStyle();
				// Create font
				Font font=wb.createFont();
				// apply Color
				font.setColor(IndexedColors.RED.index);
				// apply Bold
				font.setBold(true);
				// set Font
				
				style.setFont(font);
				// set Cell style
				rownum.getCell(column).setCellStyle(style);
			}
			else
				if(status.equalsIgnoreCase("Not Executed"))
				{
					// Create CellStyle
					CellStyle style=wb.createCellStyle();
					// Create font
					Font font=wb.createFont();
					// apply Color
					font.setColor(IndexedColors.BLUE.index);
					// apply Bold
					font.setBold(true);
					// set Font
					style.setFont(font);
					// set Cell style
					rownum.getCell(column).setCellStyle(style);
				}
	FileOutputStream fos=new FileOutputStream("C:\\Users\\prasanth.s\\workspace\\StockAccounting\\TestOutput\\OutputSheet.xlsx");
	wb.write(fos);
	fos.close();
	}
	
	
	
public static void main(String[] args) throws Throwable 
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		System.out.println(xl.rowCount("Sheet1"));
		System.out.println(xl.colCount("Sheet1", 1));
		System.out.println(xl.getData("Sheet1", 1, 1));
		xl.setData("Sheet1",1,2,"PASS");
		xl.setData("Sheet1",2,2,"FAIL");
		xl.setData("Sheet1",3,2,"NotEXECUTED");
	}
	
}


 