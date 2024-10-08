package com.sparta.logistics.product.service;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.entity.Product;
import com.sparta.logistics.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponseDto.Create createProduct(ProductRequestDto.Create requestDto) {
        return ProductResponseDto.Create.of(productRepository.save(Product.create(requestDto)));
    }

    public ProductResponseDto.Get getProduct(UUID productId) {
        return ProductResponseDto.Get.of(findById(productId));
    }

    public List<ProductResponseDto.Get> getProducts() {
        return productRepository.findAllByQuerydsl().stream().map(ProductResponseDto.Get::of).toList();
    }

    public ProductResponseDto.Update updateProduct(ProductRequestDto.Update requestDto) {
        Product product = findById(requestDto.getProductId());
        product.update(requestDto);
        return ProductResponseDto.Update.of(product);
    }

    public ProductResponseDto.Delete deleteProduct(UUID productId) {
        Product product = findById(productId);
        product.delete();
        return ProductResponseDto.Delete.of(product);
    }

    public Page<ProductResponseDto.Get> search(Pageable pageable) {
        return productRepository.search(pageable);
    }
    public Product findById(UUID productId){
        return productRepository.findByProductId(productId).orElseThrow(
                NoSuchElementException::new);
    }

}
