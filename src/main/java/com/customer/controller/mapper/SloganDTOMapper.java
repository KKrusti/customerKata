package com.customer.controller.mapper;

import com.customer.controller.response.SloganDTO;
import com.customer.domain.Slogan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SloganDTOMapper {

	SloganDTO fromDomain(Slogan slogan);
}
