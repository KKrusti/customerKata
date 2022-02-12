package com.customer.controller.mapper;

import com.customer.controller.request.SloganRequest;
import com.customer.controller.response.SloganResponse;
import com.customer.domain.Slogan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SloganDTOMapper {

	Slogan toDomain(SloganRequest sloganRequest);

	SloganResponse fromDomain(Slogan slogan);
}
