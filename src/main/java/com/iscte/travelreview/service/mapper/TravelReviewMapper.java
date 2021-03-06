package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TravelReview} and its DTO {@link TravelReviewDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { AirlineCompanyMapper.class, AccommodationMapper.class, ScoreMapper.class, AttractionMapper.class }
)
public interface TravelReviewMapper extends EntityMapper<TravelReviewDTO, TravelReview> {
    @Mapping(target = "airlineCompany", source = "airlineCompany", qualifiedByName = "name")
    @Mapping(target = "accommodation", source = "accommodation", qualifiedByName = "name")
    @Mapping(target = "flightScore", source = "flightScore", qualifiedByName = "number")
    @Mapping(target = "bookingScore", source = "bookingScore", qualifiedByName = "number")
    @Mapping(target = "attraction", source = "attraction", qualifiedByName = "name")
    @Mapping(target = "attractionScore", source = "attractionScore", qualifiedByName = "number")
    TravelReviewDTO toDto(TravelReview s);
}
