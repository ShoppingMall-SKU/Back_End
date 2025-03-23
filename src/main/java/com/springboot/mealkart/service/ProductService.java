package com.springboot.mealkart.service;


import ch.qos.logback.core.util.StringUtil;
import com.springboot.mealkart.domain.Product;
import com.springboot.mealkart.dto.ProductDetailDto;
import com.springboot.mealkart.dto.ProductDto;
import com.springboot.mealkart.dto.ProductRegDto;
import com.springboot.mealkart.dto.ProductUpdateDto;
import com.springboot.mealkart.exception.CommonException;
import com.springboot.mealkart.exception.ErrorCode;
import com.springboot.mealkart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 구매자 기능
    /**
     * 상품 목록 조회
     * @param pageable
     * @return
     */
    private Page<ProductDto> findProductList (Pageable pageable) {
        return productRepository.findProductList(pageable);
    }

    /**
     * 상품 상세 조회
     * @param productUuid
     * @return
     */
    private ProductDetailDto findProductDetail (String productUuid) {
        if (StringUtils.hasLength(productUuid)) {
            throw new CommonException(ErrorCode.INVALID_INPUT,"ProductService.findProductDetail");
        }
        return productRepository.findProductById(productUuid)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRODUCT,"ProductService.findProductDetail",productUuid));
    }

    /**
     * 상품 검색 조회
     * @param pageable
     * @param productName
     * @return
     */
    private Page<ProductDto> findProductNameList (Pageable pageable, String productName) { // 2자 이상 입력 필요 (프론트)
        if (StringUtils.hasLength(productName)) {
            throw new CommonException(ErrorCode.INVALID_INPUT,"ProductService.findProductNameList");
        }
        Page<ProductDto> result = productRepository.findByProductNameContainingIgnoreCase(pageable, productName);

        // 검색 결과가 없으면 ProductUuid = -1을 가진 ProductDto 반환
        if (result.isEmpty()) {
            return new PageImpl<>(List.of(new ProductDto("-1", "검색 결과 없음", "", "", "", "", null, 0L, 0, BigInteger.ZERO)), pageable, 1);
        }
        return result;
    }

    // 판매자 기능
    /**
     * 상품 등록
     * @param productRegDto
     */
    private void saveProduct (ProductRegDto productRegDto) {
        if (productRegDto == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT, "ProductService.saveProduct");
        }
        Product product = Product.builder()
                .productName(productRegDto.getProductName())
                .description(productRegDto.getDescription())
                .titleImg(productRegDto.getTitleImg())
                .detailImg(productRegDto.getDetailImg())
                .brand(productRegDto.getBrand())
                .storeStatus(productRegDto.getStoreStatus())
                .price(productRegDto.getPrice())
                .saleRate(productRegDto.getSaleRate())
                .stock(productRegDto.getStock())
                .sellerUuid(productRegDto.getSellerUuid())
                .build();
        productRepository.save(product);
    }
    // TODO 상품 일괄 등록

    /**
     * 상품 삭제
     * @param productUuid
     */
    private void deleteProduct (String productUuid) {
        if (StringUtils.hasLength(productUuid)) {
            throw new CommonException(ErrorCode.INVALID_INPUT, "ProductService.deleteProduct");
        }
        Product product  = productRepository.findById(productUuid)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRODUCT,"ProductService.deleteProduct",productUuid));

        product.deactivate();
        productRepository.save(product);
    }

    /**
     * 상품 수정
     * @param productUuid
     * @param productUpdateDto
     */
    private void updateProduct (String productUuid, ProductUpdateDto productUpdateDto) {
        if (StringUtils.hasLength(productUuid) || productUpdateDto == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT, "ProductService.updateProduct");
        }
        Product product  = productRepository.findById(productUuid)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRODUCT,"ProductService.updateProduct",productUuid));

        product.updateProduct(productUpdateDto);
        productRepository.save(product);
    }

}
