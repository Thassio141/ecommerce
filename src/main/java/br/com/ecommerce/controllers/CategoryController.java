package br.com.ecommerce.controllers;

import br.com.ecommerce.domain.dto.CategoryCreateDto;
import br.com.ecommerce.domain.dto.CategoryDto;
import br.com.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> findAllPageable(@RequestParam Integer page, @RequestParam Integer size){
        return new ResponseEntity<>(categoryService.findAllPageable(page,size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable UUID id){
        return new ResponseEntity<>(categoryService.findCategoryById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryCreateDto> create(@RequestBody CategoryCreateDto categoryCreateDto){
        return new ResponseEntity<>(categoryService.create(categoryCreateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryCreateDto> update(@PathVariable UUID id, @RequestBody CategoryCreateDto categoryCreateDto){
        return new ResponseEntity<>(categoryService.update(id,categoryCreateDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
