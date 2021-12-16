package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.AirlineCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AirlineCompany} and its DTO {@link AirlineCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AirlineCompanyMapper extends EntityMapper<AirlineCompanyDTO, AirlineCompany> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AirlineCompanyDTO toDtoName(AirlineCompany airlineCompany);
}
