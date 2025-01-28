package com.spring_market.persistence.mapper;

import com.spring_market.domain.Category;
import com.spring_market.persistence.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descriptio", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "proudctos", ignore = true)
    Categoria toCategoria(Category categoria);
}
