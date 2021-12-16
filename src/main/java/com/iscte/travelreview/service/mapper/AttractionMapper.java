package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.AttractionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attraction} and its DTO {@link AttractionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttractionMapper extends EntityMapper<AttractionDTO, Attraction> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AttractionDTO toDtoName(Attraction attraction);
}
