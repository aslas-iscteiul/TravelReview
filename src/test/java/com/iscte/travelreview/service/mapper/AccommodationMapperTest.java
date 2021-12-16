package com.iscte.travelreview.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccommodationMapperTest {

    private AccommodationMapper accommodationMapper;

    @BeforeEach
    public void setUp() {
        accommodationMapper = new AccommodationMapperImpl();
    }
}
