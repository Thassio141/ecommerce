package br.com.ecommerce.services;

import br.com.ecommerce.domain.dto.ProductCreateDto;
import br.com.ecommerce.domain.dto.ProductDto;
import br.com.ecommerce.domain.entity.Category;
import br.com.ecommerce.domain.entity.Product;
import br.com.ecommerce.domain.mapper.ProductMapper;
import br.com.ecommerce.repositories.CategoryRepository;
import br.com.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public Page<ProductDto> findAllPageable(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(productMapper.INSTANCE::convertEntityToDto);
    }

    public ProductDto findCategoryById(UUID id){
        return productMapper.INSTANCE.convertEntityToDto(findEntityById(id));
    }

    public ProductCreateDto create(ProductCreateDto productCreateDto){
        //Verifica se a categoria digitada existe
        Category category = categoryRepository.findById(productCreateDto.category()).orElseThrow();
        // Não deve continuar a criacao se der erro
        Product product = productMapper.INSTANCE.convertCreateDtoToEntity(productCreateDto);
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.INSTANCE.convertEntityToCreateDto(product);
    }

    public ProductCreateDto update(UUID id, ProductCreateDto productCreateDto){
        Product product = findEntityById(id);
        Product updateProduct = productMapper.INSTANCE.updateProduct(productCreateDto,product);
        Product savedProduct = productRepository.save(updateProduct);
        return productMapper.INSTANCE.convertEntityToCreateDto(savedProduct);
    }

    public void delete(UUID id){
        Product product = findEntityById(id);
        productRepository.delete(product);
    }

    private Product findEntityById(UUID id){
        return productRepository.findById(id).orElseThrow();
    }

}
