package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TravelReview} and its DTO {@link TravelReviewDTO}.
 */
@Mapper(componentModel = "spring", uses = { AirlineCompanyMapper.class })
public interface TravelReviewMapper extends EntityMapper<TravelReviewDTO, TravelReview> {
    @Mapping(target = "airlineCompany", source = "airlineCompany", qualifiedByName = "name")
    TravelReviewDTO toDto(TravelReview s);
}
