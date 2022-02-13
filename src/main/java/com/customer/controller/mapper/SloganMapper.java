package com.customer.controller.mapper;

import com.customer.controller.request.SloganRequest;
import com.customer.controller.response.SloganResponse;
import com.customer.domain.Slogan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SloganMapper {

	@Mapping(source = "text", target = "sloganText")
	Slogan toDomain(SloganRequest sloganRequest);

	@Mapping(source = "sloganText", target = "slogan")
	SloganResponse fromDomain(Slogan slogan);
}
