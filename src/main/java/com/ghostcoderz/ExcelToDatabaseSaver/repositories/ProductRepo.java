package com.ghostcoderz.ExcelToDatabaseSaver.repositories;

import com.ghostcoderz.ExcelToDatabaseSaver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository <Product, Integer> {

}
