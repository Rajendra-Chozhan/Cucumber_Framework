//package utilities;
//
//import java.io.FileInputStream;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.annotations.Test;
//
//import static java.sql.Types.NUMERIC;
//
//public class ExcelOutput {
//
//	@Test
//	public static void main(String[] args) throws Exception {
//
//		String excelFilePath="E:\\POM-Chozhan\\Excel_Output_Sheet\\Excelreport.xlsx";
//		FileInputStream inputstream=new FileInputStream(excelFilePath);
//
//		XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
//		XSSFSheet sheet=workbook.getSheetAt(0);	//XSSFSheet sheet=workbook.getSheet("Sheet1");
//
//		////  USING FOR LOOP
//
//		int rows=sheet.getLastRowNum();
//		int cols=sheet.getRow(1).getLastCellNum();
//
//		for(int r=0;r<=rows;r++)
//		{
//			XSSFRow row=sheet.getRow(r); //0
//
//			for(int c=0;c<cols;c++)
//			{
//				XSSFCell cell=row.getCell(c);
//
//				switch(cell.getCellType())
//				{
//				case STRING: System.out.print(cell.getStringCellValue()); break;
//				case NUMERIC: System.out.print(cell.getNumericCellValue());break;
//				case BOOLEAN: System.out.print(cell.getBooleanCellValue()); break;
//				}
//				System.out.print(" | ");
//			}
//			System.out.println();
//		}
//
//	}
//
//}
