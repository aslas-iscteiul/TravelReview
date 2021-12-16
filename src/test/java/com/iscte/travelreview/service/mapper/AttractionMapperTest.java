package com.iscte.travelreview.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttractionMapperTest {

    private AttractionMapper attractionMapper;

    @BeforeEach
    public void setUp() {
        attractionMapper = new AttractionMapperImpl();
    }
}
