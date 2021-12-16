package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TravelReviewProcess} and its DTO {@link TravelReviewProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, TravelReviewMapper.class })
public interface TravelReviewProcessMapper extends EntityMapper<TravelReviewProcessDTO, TravelReviewProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "travelReview", source = "travelReview")
    TravelReviewProcessDTO toDto(TravelReviewProcess s);
}
