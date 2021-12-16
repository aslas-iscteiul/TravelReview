package com.iscte.travelreview.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreMapperTest {

    private ScoreMapper scoreMapper;

    @BeforeEach
    public void setUp() {
        scoreMapper = new ScoreMapperImpl();
    }
}
