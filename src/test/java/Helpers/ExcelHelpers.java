package Helpers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.io.Files;

public class ExcelHelpers 
{
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static String Path_TestData ="src//main//resources//Resources//";
	public static String File_TestData = "TestData.xlsx";
	
	public static void setExcelFile(String Path,String SheetName) throws Exception
	{
		try 
		{		
			FileInputStream ExcelFile = new FileInputStream(Path);		
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e)
		{
			throw (e);
		}

	}
	
	public static double getCellNum(int RowNum, int ColNum) throws Exception
	{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			double CellData = Cell.getNumericCellValue();		
			return CellData;
		}
		catch (Exception e)
		{
			return Double.NaN;
		}
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception
	{
		try
		{
			String CellData = getCellString(RowNum, ColNum);
			if(CellData.length()==0)
			{
				CellData = Integer.toString((int)getCellNum(RowNum, ColNum));;
			}
			return CellData;
		}
		catch (Exception e) {
			return"";
		}
		
	}
	
	public static String getCellString(int RowNum, int ColNum) throws Exception
	{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();			
			return CellData;
		}
		catch (Exception e)
		{
			return"";
		}
	}
	@SuppressWarnings("static-access")
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception 
	{
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Path_TestData+File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch(Exception e)
		{
			throw (e);
		}
	}
	
	public static int getRowCount(String Path, String Sheet)
	{
		try
		{
			FileInputStream fis = new FileInputStream(Path);
			Workbook wb = WorkbookFactory.create(fis);
			return wb.getSheet(Sheet).getLastRowNum();
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	public static String getCurrentDate() {
		SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formDate.format(new Date());
		return strDate;
	}

	public static void dateReplace() throws IOException
	{
		File fileToBeModified = new File("D:\\Selenium\\Java\\Projects\\ZenoNext\\src\\main\\java\\TestData\\Original\\TestData.xlsx"); 
        String oldContent = "";         
        BufferedReader reader = null;         
        FileWriter writer = null;         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));           
            String line = reader.readLine();
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();                
                line = reader.readLine();
            }  
            String newContent = oldContent.replaceAll("2019-08-03",ExcelHelpers.getCurrentDate());
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();                
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
     
	
	
	public static void DeleteCurrentFile()
	{
		File CurrentDatafile = new File("D:\\Selenium\\Java\\Projects\\ZenoNext\\src\\main\\java\\TestData\\TestData.xlsx"); 
        
        if(CurrentDatafile.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
    } 
	
	public static void CopyFromOriginal() throws IOException
	{
		 File ActualDataFile=new File("D:\\Selenium\\Java\\Projects\\ZenoNext\\src\\main\\java\\TestData\\TestData.xlsx");
		 File OriginalDatafile = new File("D:\\Selenium\\Java\\Projects\\ZenoNext\\src\\main\\java\\TestData\\Original\\TestData.xlsx"); 
		 if(ActualDataFile.delete()) {
			 System.out.println("File Deleted");
		 }
		 else
		 {
			 System.out.println("File Not Deleted");
		 }
		   
	        Files.copy(OriginalDatafile, ActualDataFile);
	  
	}
}

