package com.iscte.travelreview.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.iscte.travelreview.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TravelReviewTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelReview.class);
        TravelReview travelReview1 = new TravelReview();
        travelReview1.setId(1L);
        TravelReview travelReview2 = new TravelReview();
        travelReview2.setId(travelReview1.getId());
        assertThat(travelReview1).isEqualTo(travelReview2);
        travelReview2.setId(2L);
        assertThat(travelReview1).isNotEqualTo(travelReview2);
        travelReview1.setId(null);
        assertThat(travelReview1).isNotEqualTo(travelReview2);
    }
}
