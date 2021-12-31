package kekaAssignmentstart;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SetExcelSheet {
    public static void main(String[] args) throws IOException {

        File src=new File("/home/knoldus/Downloads/Untitled spreadsheet.xlsx");
        FileInputStream kekafile=new FileInputStream(src);

        XSSFWorkbook wb=new XSSFWorkbook(kekafile);

        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data0= sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println("data from excel is "+ data0);
        String data1= sheet1.getRow(1).getCell(0).getStringCellValue();
        System.out.println("data from excel is "+ data1);

        wb.close();

    }

}
