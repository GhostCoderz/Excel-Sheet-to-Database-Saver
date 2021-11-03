package com.ghostcoderz.ExcelToDatabaseSaver.controller;

import com.ghostcoderz.ExcelToDatabaseSaver.entity.Product;
import com.ghostcoderz.ExcelToDatabaseSaver.helper.ExcelHelper;
import com.ghostcoderz.ExcelToDatabaseSaver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> uploadExcelDoc(@RequestParam("file") MultipartFile file){

        boolean isExcelFile = ExcelHelper.checkExcelFormat(file);

        if (isExcelFile){

            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and the data is saved to db"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel sheet");
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

}
