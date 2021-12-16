package com.iscte.travelreview.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.iscte.travelreview.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttractionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttractionDTO.class);
        AttractionDTO attractionDTO1 = new AttractionDTO();
        attractionDTO1.setId(1L);
        AttractionDTO attractionDTO2 = new AttractionDTO();
        assertThat(attractionDTO1).isNotEqualTo(attractionDTO2);
        attractionDTO2.setId(attractionDTO1.getId());
        assertThat(attractionDTO1).isEqualTo(attractionDTO2);
        attractionDTO2.setId(2L);
        assertThat(attractionDTO1).isNotEqualTo(attractionDTO2);
        attractionDTO1.setId(null);
        assertThat(attractionDTO1).isNotEqualTo(attractionDTO2);
    }
}
