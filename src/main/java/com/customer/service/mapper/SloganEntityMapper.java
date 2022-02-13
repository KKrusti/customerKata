package com.customer.service.mapper;

import com.customer.domain.Slogan;
import com.customer.entity.SloganEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SloganEntityMapper {

	@Mapping(source = "sloganText", target = "slogan")
	SloganEntity toEntity(Slogan slogan);

	@Mapping(source = "slogan", target = "sloganText")
	Slogan toDomain(SloganEntity sloganEntity);
}
