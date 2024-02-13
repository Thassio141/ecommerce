package br.com.ecommerce.services;

import br.com.ecommerce.domain.dto.CategoryCreateDto;
import br.com.ecommerce.domain.dto.CategoryDto;
import br.com.ecommerce.domain.entity.Category;
import br.com.ecommerce.domain.mapper.CategoryMapper;
import br.com.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Page<CategoryDto> findAllPageable(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper.INSTANCE::convertEntityToDto);
    }

    public CategoryDto findCategoryById(UUID id){
        return categoryMapper.INSTANCE.convertEntityToDto(findEntityById(id));
    }

    public CategoryCreateDto create(CategoryCreateDto categoryCreateDto){
        Category category = categoryMapper.INSTANCE.convertCreatDtoToEntity(categoryCreateDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.INSTANCE.convertEntityToCreateDto(savedCategory);
    }

    public CategoryCreateDto update(UUID id, CategoryCreateDto categoryCreateDto){
        Category category = findEntityById(id);
        Category updateCategory = categoryMapper.INSTANCE.updateCategory(categoryCreateDto,category);
        Category savedCategory = categoryRepository.save(updateCategory);
        return categoryMapper.INSTANCE.convertEntityToCreateDto(savedCategory);
    }

    public void delete(UUID id){
        Category category = findEntityById(id);
        categoryRepository.delete(category);
    }
    private Category findEntityById(UUID id){
        return categoryRepository.findById(id).orElseThrow();
    }
}
