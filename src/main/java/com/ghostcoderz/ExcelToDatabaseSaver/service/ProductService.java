package com.ghostcoderz.ExcelToDatabaseSaver.service;

import com.ghostcoderz.ExcelToDatabaseSaver.entity.Product;
import com.ghostcoderz.ExcelToDatabaseSaver.helper.ExcelHelper;
import com.ghostcoderz.ExcelToDatabaseSaver.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void save(MultipartFile file) {

        try{

            List<Product> productList = ExcelHelper.convertExcelToListOfProducts(file.getInputStream());
            productRepo.saveAll(productList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Product> getAllProducts(){

        return this.productRepo.findAll();

    }

}
