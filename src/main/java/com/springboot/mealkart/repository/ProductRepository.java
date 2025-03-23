package com.springboot.mealkart.repository;

import com.springboot.mealkart.domain.Product;
import com.springboot.mealkart.dto.ProductDetailDto;
import com.springboot.mealkart.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAll(Pageable pageable);

    Optional<ProductDetailDto> findByProductUuid(String productUuid);

    Page<ProductDto> findByProductNameContainingIgnoreCase(Pageable pageable, String productName);
}
