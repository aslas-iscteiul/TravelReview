package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.AttractionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attraction} and its DTO {@link AttractionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttractionMapper extends EntityMapper<AttractionDTO, Attraction> {}
