package com.customer.service.mapper;

import com.customer.domain.Slogan;
import com.customer.entity.SloganEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SloganMapper {

	SloganEntity toEntity(Slogan slogan);

	Slogan toDomain(SloganEntity sloganEntity);
}
