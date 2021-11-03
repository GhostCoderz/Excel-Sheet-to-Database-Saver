package com.ghostcoderz.ExcelToDatabaseSaver.helper;

import com.ghostcoderz.ExcelToDatabaseSaver.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {


    //Check if the file is in Excel format
    public static boolean checkExcelFormat(MultipartFile file){
        String fileContentType = file.getContentType();
        String excelContentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        return fileContentType.equals(excelContentType);
    }

    //Converts the products from Excel to List of Products
    public static List<Product> convertExcelToListOfProducts(InputStream is){

        List<Product> productList = new ArrayList<>();

        try{

            XSSFWorkbook workbook =  new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNum=0;

            Iterator<Row> rows = sheet.iterator();
            while(rows.hasNext()){

                Row row = rows.next();

                if (rowNum==0) {
                    rowNum++;
                    continue;
                }

                int cellNum=0;
                Product product = new Product();

                Iterator<Cell> cells = row.cellIterator();
                while(cells.hasNext()){

                    Cell cell  =  cells.next();

                    switch (cellNum){

                        case 0 : {
                            product.setProductNo((int)cell.getNumericCellValue());
                            break;
                        }
                        case 1 : {
                            product.setProductName(cell.getStringCellValue());
                            break;
                        }
                        case 2 : {
                            product.setProductDesc(cell.getStringCellValue());
                            break;
                        }
                        case 3 : {
                            product.setProductPrice(cell.getNumericCellValue());
                            break;
                        }
                        default: break;
                    }
                    cellNum++;
                }
                productList.add(product);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return productList;

    }

}
