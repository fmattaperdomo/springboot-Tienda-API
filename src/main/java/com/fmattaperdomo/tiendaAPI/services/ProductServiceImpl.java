package com.fmattaperdomo.tiendaAPI.services;

import com.fmattaperdomo.tiendaAPI.dtos.ProductDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;
import com.fmattaperdomo.tiendaAPI.entities.Category;
import com.fmattaperdomo.tiendaAPI.entities.Product;
import com.fmattaperdomo.tiendaAPI.exceptions.ResourceNotFoundException;
import com.fmattaperdomo.tiendaAPI.repositories.CategoryRepository;
import com.fmattaperdomo.tiendaAPI.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> pageProducts = productRepository.findAll(pageDetails);

        List<Product> products = pageProducts.getContent();

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setContent(productDtos);
        productResponseDto.setPageNumber(pageProducts.getNumber());
        productResponseDto.setPageSize(pageProducts.getSize());
        productResponseDto.setTotalElements(pageProducts.getTotalElements());
        productResponseDto.setTotalPages(pageProducts.getTotalPages());
        productResponseDto.setLastPage(pageProducts.isLast());
        return productResponseDto;
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","productId", productId));
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductResponseDto getProductsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> pageProducts = productRepository.findByCategoryId(categoryId, pageDetails);

        List<Product> products = pageProducts.getContent();

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setContent(productDtos);
        productResponseDto.setPageNumber(pageProducts.getNumber());
        productResponseDto.setPageSize(pageProducts.getSize());
        productResponseDto.setTotalElements(pageProducts.getTotalElements());
        productResponseDto.setTotalPages(pageProducts.getTotalPages());
        productResponseDto.setLastPage(pageProducts.isLast());
        return productResponseDto;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Integer categoryId = productDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        productFromDb.setDescription(productDto.getDescription());
        productFromDb.setName(productDto.getName());
        productFromDb.setCategoryId(productDto.getCategoryId());
        productFromDb.setCategory(productDto.getCategory());
        productFromDb.setCreatedAt(productDto.getCreatedAt());
        productFromDb.setUpdatedAt(productDto.getUpdatedAt());

        Product savedProduct = productRepository.save(productFromDb);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        ProductDto deletedProductDto = modelMapper.map(product, ProductDto.class);

        productRepository.deleteById(productId);
        return deletedProductDto;
    }

    @Override
    public ProductDto updateProductByPrice(Integer productId, Double percentage) {
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Double newPrice = productFromDb.getPrice() + (percentage * productFromDb.getPrice());
        productFromDb.setPrice(newPrice);

        Product updatedProduct = productRepository.save(productFromDb);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public ProductResponseDto getLaggingProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> pageProducts = productRepository.getProductsLagging(pageDetails);

        List<Product> products = pageProducts.getContent();

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setContent(productDtos);
        productResponseDto.setPageNumber(pageProducts.getNumber());
        productResponseDto.setPageSize(pageProducts.getSize());
        productResponseDto.setTotalElements(pageProducts.getTotalElements());
        productResponseDto.setTotalPages(pageProducts.getTotalPages());
        productResponseDto.setLastPage(pageProducts.isLast());
        return productResponseDto;
    }

}
