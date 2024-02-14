package br.com.ecommerce.controllers;

import br.com.ecommerce.domain.dto.ProductCreateDto;
import br.com.ecommerce.domain.dto.ProductDto;
import br.com.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAllPageable(@RequestParam Integer page, @RequestParam Integer size){
        return new ResponseEntity<>(productService.findAllPageable(page,size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable UUID id){
        return new ResponseEntity<>(productService.findProductById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCreateDto> create(@RequestBody ProductCreateDto productCreateDto){
        return new ResponseEntity<>(productService.create(productCreateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductCreateDto> update(@PathVariable UUID id, @RequestBody ProductCreateDto productCreateDto){
        return new ResponseEntity<>(productService.update(id,productCreateDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
