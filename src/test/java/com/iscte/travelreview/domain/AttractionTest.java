package com.iscte.travelreview.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.iscte.travelreview.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttractionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attraction.class);
        Attraction attraction1 = new Attraction();
        attraction1.setId(1L);
        Attraction attraction2 = new Attraction();
        attraction2.setId(attraction1.getId());
        assertThat(attraction1).isEqualTo(attraction2);
        attraction2.setId(2L);
        assertThat(attraction1).isNotEqualTo(attraction2);
        attraction1.setId(null);
        assertThat(attraction1).isNotEqualTo(attraction2);
    }
}
