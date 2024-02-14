package br.com.ecommerce.domain.mapper;

import br.com.ecommerce.domain.dto.CategoryCreateDto;
import br.com.ecommerce.domain.dto.CategoryDto;
import br.com.ecommerce.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryCreateDto convertEntityToCreateDto(Category category);

    Category convertCreatDtoToEntity(CategoryCreateDto categoryCreateDto);

    CategoryDto convertEntityToDto(Category category);

    Category updateCategory(CategoryCreateDto categoryCreateDto, @MappingTarget Category category);


}
