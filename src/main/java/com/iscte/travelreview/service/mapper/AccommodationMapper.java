package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.AccommodationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Accommodation} and its DTO {@link AccommodationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccommodationMapper extends EntityMapper<AccommodationDTO, Accommodation> {}
