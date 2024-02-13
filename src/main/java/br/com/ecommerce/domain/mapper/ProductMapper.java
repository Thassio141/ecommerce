package br.com.ecommerce.domain.mapper;

import br.com.ecommerce.domain.dto.ProductCreateDto;
import br.com.ecommerce.domain.dto.ProductDto;
import br.com.ecommerce.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductCreateDto convertEntityToCreateDto(Product product);

    Product convertCreateDtoToEntity(ProductCreateDto productCreateDto);

    ProductDto convertEntityToDto(Product product);

    Product updateProduct(ProductCreateDto productCreateDto, @MappingTarget Product product);


}
