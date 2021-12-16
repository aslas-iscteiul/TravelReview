package com.iscte.travelreview.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TravelReviewMapperTest {

    private TravelReviewMapper travelReviewMapper;

    @BeforeEach
    public void setUp() {
        travelReviewMapper = new TravelReviewMapperImpl();
    }
}
