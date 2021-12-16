package com.iscte.travelreview.service.mapper;

import com.iscte.travelreview.domain.*;
import com.iscte.travelreview.service.dto.ScoreDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Score} and its DTO {@link ScoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScoreMapper extends EntityMapper<ScoreDTO, Score> {
    @Named("description")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    ScoreDTO toDtoDescription(Score score);
}
