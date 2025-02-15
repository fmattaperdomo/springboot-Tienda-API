package com.fmattaperdomo.tiendaAPI.services;

import com.fmattaperdomo.tiendaAPI.dtos.ProductDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDto getProductById(Integer productId);
    ProductResponseDto getProductsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Integer productId, ProductDto productDto);
    ProductDto deleteProduct(Integer id);
    ProductDto updateProductByPrice(Integer productId, Double percentage);
    ProductResponseDto getLaggingProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
