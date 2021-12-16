package com.iscte.travelreview.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.iscte.travelreview.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TravelReviewDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelReviewDTO.class);
        TravelReviewDTO travelReviewDTO1 = new TravelReviewDTO();
        travelReviewDTO1.setId(1L);
        TravelReviewDTO travelReviewDTO2 = new TravelReviewDTO();
        assertThat(travelReviewDTO1).isNotEqualTo(travelReviewDTO2);
        travelReviewDTO2.setId(travelReviewDTO1.getId());
        assertThat(travelReviewDTO1).isEqualTo(travelReviewDTO2);
        travelReviewDTO2.setId(2L);
        assertThat(travelReviewDTO1).isNotEqualTo(travelReviewDTO2);
        travelReviewDTO1.setId(null);
        assertThat(travelReviewDTO1).isNotEqualTo(travelReviewDTO2);
    }
}
