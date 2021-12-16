package com.iscte.travelreview.process.travelReviewProcess;

import com.iscte.travelreview.domain.TravelReview;
import com.iscte.travelreview.domain.TravelReviewProcess;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskRegisterTravelMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    TravelReviewProcessDTO toTravelReviewProcessDTO(TravelReviewProcess travelReviewProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "travelOrigin", source = "travelOrigin")
    @Mapping(target = "travelDestination", source = "travelDestination")
    @Mapping(target = "travelWithFlight", source = "travelWithFlight")
    @Mapping(target = "travelStartDate", source = "travelStartDate")
    @Mapping(target = "travelEndDate", source = "travelEndDate")
    TravelReviewDTO toTravelReviewDTO(TravelReview travelReview);
}
