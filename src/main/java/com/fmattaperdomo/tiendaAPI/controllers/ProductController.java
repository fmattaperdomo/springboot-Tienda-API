package com.fmattaperdomo.tiendaAPI.controllers;

import com.fmattaperdomo.tiendaAPI.configurations.AppConstants;
import com.fmattaperdomo.tiendaAPI.dtos.ProductDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;
import com.fmattaperdomo.tiendaAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productos")
    public ResponseEntity<ProductResponseDto> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponseDto productResponseDto = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @GetMapping("/productos/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId){
        ProductDto productDto = productService.getProductById(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/categorias/{categoryId}/productos")
    public ResponseEntity<ProductResponseDto> getProductsByCategory(@PathVariable Integer categoryId,
                                                                          @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                          @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                          @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY, required = false) String sortBy,
                                                                          @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponseDto productResponseDto = productService.getProductsByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/productos")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        ProductDto savedProductDto = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
    }

    @PutMapping("/productos/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
                                                         @PathVariable Integer productId){
        ProductDto updatedProductDto = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }
    @DeleteMapping("/productos/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Integer productId){
        ProductDto deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
    @PutMapping("/productos/{productId}/precio/{percentage}")
    public ResponseEntity<ProductDto> updateProductByPrice(@PathVariable Integer productId,
                                                           @PathVariable Double percentage){
        ProductDto updatedProductDto = productService.updateProductByPrice(productId, percentage);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    @GetMapping("/productos/rezagados")
    public ResponseEntity<ProductResponseDto> getLaggingProducts(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                    @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                    @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY, required = false) String sortBy,
                                                                    @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponseDto productResponseDto = productService.getLaggingProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

}
