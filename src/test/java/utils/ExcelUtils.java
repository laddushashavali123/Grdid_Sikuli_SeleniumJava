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
        HSSFWorkbook hssfWorkbook = null;
        String[][] result = null;
        int startRow = 1;
        String fileExtensionName =  FilenameUtils.getExtension(fileLocation);

        if (fileExtensionName.equalsIgnoreCase("xlsx")){
            System.out.println("Excel file is xlsx type.");
            try {
                // Open the excel file
                ExcelFileToRead = new FileInputStream(fileLocation);


                // Getting the workbook instance for xlsx file
                xssfWorkbook = new XSSFWorkbook(ExcelFileToRead);
            } catch (IOException e) {
                e.printStackTrace();
            }

            XSSFSheet xssfsheet = xssfWorkbook.getSheet(sheetName);
            int noOfRow  = xssfsheet.getPhysicalNumberOfRows();
            int noOfColumn  = xssfsheet.getRow(0).getPhysicalNumberOfCells();

            // Loop throw each cell and get the value
            int noOfRowGet = noOfRow - startRow;
            result = new String[noOfRowGet][noOfColumn];
            for (int i = 0; i < noOfRowGet; i++){
                for (int j = 0; j< noOfColumn; j++){
                    XSSFCell xssfCell = xssfsheet.getRow(i + startRow).getCell(j);
                    if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        result[i][j] = xssfCell.getStringCellValue();
                    }
                    else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
                        result[i][j] = String.valueOf(xssfCell.getNumericCellValue());
                    }
                    /*else {
                        // Here if require, we can also add below methods to read the cell content
                        // CELL_TYPE_BOOLEAN
                        // XSSFCell.CELL_TYPE_BLANK
                        // XSSFCell.CELL_TYPE_FORMULA
                        // XSSFCell.CELL_TYPE_ERROR
                    }*/
                }
            }


        }
        else if (fileExtensionName.equalsIgnoreCase("xls")){
            System.out.println("Excel file is xls type.");
            try {
                // Open the excel file
                ExcelFileToRead = new FileInputStream(fileLocation);

                //Getting the workbook instance for xls file
                hssfWorkbook = new HSSFWorkbook(ExcelFileToRead);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Getting the first sheet from the workbook using sheet name.
            // We can also pass the index of the sheet which starts from '0'.
            HSSFSheet hssfSheet = hssfWorkbook.getSheet(sheetName);

            // Get total number of rows and columns used in the spreadsheet which are actually filled with contents
            // If the 2nd row of 10 rows is not filled you will get 9
            int noOfRow  = hssfSheet.getPhysicalNumberOfRows();
            int noOfColumn  = hssfSheet.getRow(0).getPhysicalNumberOfCells();

            // Loop throw each cell and get the value
            int noOfRowGet = noOfRow - startRow;
            result = new String[noOfRowGet][noOfColumn];
            for (int i = 0; i < noOfRowGet; i++){
                for (int j = 0; j< noOfColumn; j++){
                    HSSFCell hssfCell = hssfSheet.getRow(i + startRow).getCell(j);
                    if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        result[i][j] = hssfCell.getStringCellValue();
                    }
                    else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        result[i][j] = String.valueOf(hssfCell.getNumericCellValue());
                    }
                    /*else {
                        // Here if require, we can also add below methods to read the cell content
                        // CELL_TYPE_BOOLEAN
                        // XSSFCell.CELL_TYPE_BLANK
                        // XSSFCell.CELL_TYPE_FORMULA
                        // XSSFCell.CELL_TYPE_ERROR
                    }*/
                }
            }
        }
        else {
            System.out.println("Incorrect File Type. File should be xlsx or xls type");
            throw new IllegalArgumentException ();
        }

        // Closed excel file
        try {
            ExcelFileToRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<4; i++)
        {
            for(int j = 0; j<2; j++)
            {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }

        return result;
    }


    @Test
    public void test() throws IOException {
        String fileLocation = System.getProperty("user.dir")+ "\\src\\test\\java\\testsdata\\data.xlsx";
        System.out.println(fileLocation);
        getExcelDataToDataProvider(fileLocation,"Sheet1");
    }
}
