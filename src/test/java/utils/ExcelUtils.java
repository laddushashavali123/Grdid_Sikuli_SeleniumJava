package utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;



public class ExcelUtils {
    public static String[][] getExcelDataToDataProvider(String fileLocation, String sheetName) throws IOException {
        InputStream ExcelFileToRead = null;
        XSSFWorkbook xssfWorkbook = null;
        HSSFWorkbook hssfworkbook = null;
        String[][] result = null;
        String fileExtensionName =  FilenameUtils.getExtension(fileLocation);

        if (fileExtensionName.equalsIgnoreCase("xlsx")){
            System.out.println("Excel file is xlsx type.");
            try {
                // Open the excel file
                ExcelFileToRead = new FileInputStream(fileLocation);


                //Getting the workbook instance for xlsx file
                xssfWorkbook = new XSSFWorkbook(ExcelFileToRead);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Getting the first sheet from the workbook using sheet name.
            // We can also pass the index of the sheet which starts from '0'.
            XSSFSheet xssfsheet = xssfWorkbook.getSheet(sheetName);
            XSSFRow xssfRow;
            XSSFCell xssfCell;

            //Iterating all the rows in the sheet
            Iterator rows = xssfsheet.rowIterator();

            while (rows.hasNext()) {
                xssfRow = (XSSFRow) rows.next();

                //Iterating all the cells of the current row
                Iterator cells = xssfRow.cellIterator();

                while (cells.hasNext()) {
                    xssfCell = (XSSFCell) cells.next();


                    // If you want handle for each data type
                    if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        System.out.print(xssfCell.getStringCellValue() + " ");
                    } else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        System.out.print(xssfCell.getNumericCellValue() + " ");
                    } else {
                        // Here if require, we can also add below methods to read the cell content
                        // CELL_TYPE_BOOLEAN
                        // XSSFCell.CELL_TYPE_BLANK
                        // XSSFCell.CELL_TYPE_FORMULA
                        // XSSFCell.CELL_TYPE_ERROR
                    }
                }
                try {
                    ExcelFileToRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (fileExtensionName.equalsIgnoreCase("xls")){
            System.out.println("Excel file is xls type.");
            try {
                ExcelFileToRead = new FileInputStream(fileLocation);

                //Getting the workbook instance for xls file
                hssfworkbook = new HSSFWorkbook(ExcelFileToRead);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //getting the first sheet from the workbook using sheet name.
            // We can also pass the index of the sheet which starts from '0'.
            HSSFSheet hssfsheet = hssfworkbook.getSheet(sheetName);
            HSSFRow hssfRow;
            HSSFCell hssfCell;


            //Iterating all the rows in the sheet
            Iterator rows = hssfsheet.rowIterator();

            while (rows.hasNext()) {
                hssfRow = (HSSFRow) rows.next();


                //Iterating all the cells of the current row
                Iterator cells = hssfRow.cellIterator();

                while (cells.hasNext()) {
                    hssfCell = (HSSFCell) cells.next();



                    // If you want handle for each data type
                    /*if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        System.out.print(hssfCell.getStringCellValue() + " ");
                    } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        System.out.print(hssfCell.getNumericCellValue() + " ");
                    } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                        System.out.print(hssfCell.getBooleanCellValue() + " ");
                    } else { // //Here if require, we can also add below methods to
                        // read the cell content
                        // HSSFCell.CELL_TYPE_BLANK
                        // HSSFCell.CELL_TYPE_FORMULA
                        // HSSFCell.CELL_TYPE_ERROR
                    }*/
                }
                System.out.println();
                try {
                    ExcelFileToRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            System.out.println("Incorrect File Type. File should be xlsx or xls type");
            throw new IllegalArgumentException ();
        }

        return result;
    }


    @Test
    public void test() throws IOException {
        String fileLocation = System.getProperty("user.dir")+ "\\src\\test\\java\\testsdata\\data.xls";
        System.out.println(fileLocation);
        getExcelDataToDataProvider(fileLocation,"Sheet1");
    }
}
