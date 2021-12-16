package com.iscte.travelreview.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.iscte.travelreview.IntegrationTest;
import com.iscte.travelreview.domain.TravelReview;
import com.iscte.travelreview.repository.TravelReviewRepository;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import com.iscte.travelreview.service.mapper.TravelReviewMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TravelReviewResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TravelReviewResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TRAVEL_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_TRAVEL_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_TRAVEL_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_TRAVEL_DESTINATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TRAVEL_WITH_FLIGHT = false;
    private static final Boolean UPDATED_TRAVEL_WITH_FLIGHT = true;

    private static final LocalDate DEFAULT_TRAVEL_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRAVEL_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TRAVEL_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRAVEL_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FLIGHT_TICKET_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FLIGHT_TICKET_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FLIGHT_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_FLIGHT_CLASS = "BBBBBBBBBB";

    private static final Double DEFAULT_FLIGHT_DURATION = 1D;
    private static final Double UPDATED_FLIGHT_DURATION = 2D;

    private static final Double DEFAULT_FLIGHT_PRICE = 1D;
    private static final Double UPDATED_FLIGHT_PRICE = 2D;

    private static final String DEFAULT_FLIGHT_REVIEW = "AAAAAAAAAA";
    private static final String UPDATED_FLIGHT_REVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOMMODATION_BOOKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOMMODATION_BOOKING_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_ACCOMMODATION_BOOKING_PRICE = 1D;
    private static final Double UPDATED_ACCOMMODATION_BOOKING_PRICE = 2D;

    private static final String DEFAULT_ACCOMMODATION_BOOKING_REVIEW = "AAAAAAAAAA";
    private static final String UPDATED_ACCOMMODATION_BOOKING_REVIEW = "BBBBBBBBBB";

    private static final Double DEFAULT_ATTRACTION_PRICE = 1D;
    private static final Double UPDATED_ATTRACTION_PRICE = 2D;

    private static final String DEFAULT_ATTRACTION_REVIEW = "AAAAAAAAAA";
    private static final String UPDATED_ATTRACTION_REVIEW = "BBBBBBBBBB";

    private static final Double DEFAULT_TRAVEL_COST = 1D;
    private static final Double UPDATED_TRAVEL_COST = 2D;

    private static final String ENTITY_API_URL = "/api/travel-reviews";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TravelReviewRepository travelReviewRepository;

    @Autowired
    private TravelReviewMapper travelReviewMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTravelReviewMockMvc;

    private TravelReview travelReview;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelReview createEntity(EntityManager em) {
        TravelReview travelReview = new TravelReview()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .travelOrigin(DEFAULT_TRAVEL_ORIGIN)
            .travelDestination(DEFAULT_TRAVEL_DESTINATION)
            .travelWithFlight(DEFAULT_TRAVEL_WITH_FLIGHT)
            .travelStartDate(DEFAULT_TRAVEL_START_DATE)
            .travelEndDate(DEFAULT_TRAVEL_END_DATE)
            .flightTicketNumber(DEFAULT_FLIGHT_TICKET_NUMBER)
            .flightClass(DEFAULT_FLIGHT_CLASS)
            .flightDuration(DEFAULT_FLIGHT_DURATION)
            .flightPrice(DEFAULT_FLIGHT_PRICE)
            .flightReview(DEFAULT_FLIGHT_REVIEW)
            .accommodationBookingNumber(DEFAULT_ACCOMMODATION_BOOKING_NUMBER)
            .accommodationBookingPrice(DEFAULT_ACCOMMODATION_BOOKING_PRICE)
            .accommodationBookingReview(DEFAULT_ACCOMMODATION_BOOKING_REVIEW)
            .attractionPrice(DEFAULT_ATTRACTION_PRICE)
            .attractionReview(DEFAULT_ATTRACTION_REVIEW)
            .travelCost(DEFAULT_TRAVEL_COST);
        return travelReview;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelReview createUpdatedEntity(EntityManager em) {
        TravelReview travelReview = new TravelReview()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .travelOrigin(UPDATED_TRAVEL_ORIGIN)
            .travelDestination(UPDATED_TRAVEL_DESTINATION)
            .travelWithFlight(UPDATED_TRAVEL_WITH_FLIGHT)
            .travelStartDate(UPDATED_TRAVEL_START_DATE)
            .travelEndDate(UPDATED_TRAVEL_END_DATE)
            .flightTicketNumber(UPDATED_FLIGHT_TICKET_NUMBER)
            .flightClass(UPDATED_FLIGHT_CLASS)
            .flightDuration(UPDATED_FLIGHT_DURATION)
            .flightPrice(UPDATED_FLIGHT_PRICE)
            .flightReview(UPDATED_FLIGHT_REVIEW)
            .accommodationBookingNumber(UPDATED_ACCOMMODATION_BOOKING_NUMBER)
            .accommodationBookingPrice(UPDATED_ACCOMMODATION_BOOKING_PRICE)
            .accommodationBookingReview(UPDATED_ACCOMMODATION_BOOKING_REVIEW)
            .attractionPrice(UPDATED_ATTRACTION_PRICE)
            .attractionReview(UPDATED_ATTRACTION_REVIEW)
            .travelCost(UPDATED_TRAVEL_COST);
        return travelReview;
    }

    @BeforeEach
    public void initTest() {
        travelReview = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTravelReviews() throws Exception {
        // Initialize the database
        travelReviewRepository.saveAndFlush(travelReview);

        // Get all the travelReviewList
        restTravelReviewMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(travelReview.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].travelOrigin").value(hasItem(DEFAULT_TRAVEL_ORIGIN)))
            .andExpect(jsonPath("$.[*].travelDestination").value(hasItem(DEFAULT_TRAVEL_DESTINATION)))
            .andExpect(jsonPath("$.[*].travelWithFlight").value(hasItem(DEFAULT_TRAVEL_WITH_FLIGHT.booleanValue())))
            .andExpect(jsonPath("$.[*].travelStartDate").value(hasItem(DEFAULT_TRAVEL_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].travelEndDate").value(hasItem(DEFAULT_TRAVEL_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].flightTicketNumber").value(hasItem(DEFAULT_FLIGHT_TICKET_NUMBER)))
            .andExpect(jsonPath("$.[*].flightClass").value(hasItem(DEFAULT_FLIGHT_CLASS)))
            .andExpect(jsonPath("$.[*].flightDuration").value(hasItem(DEFAULT_FLIGHT_DURATION.doubleValue())))
            .andExpect(jsonPath("$.[*].flightPrice").value(hasItem(DEFAULT_FLIGHT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].flightReview").value(hasItem(DEFAULT_FLIGHT_REVIEW)))
            .andExpect(jsonPath("$.[*].accommodationBookingNumber").value(hasItem(DEFAULT_ACCOMMODATION_BOOKING_NUMBER)))
            .andExpect(jsonPath("$.[*].accommodationBookingPrice").value(hasItem(DEFAULT_ACCOMMODATION_BOOKING_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].accommodationBookingReview").value(hasItem(DEFAULT_ACCOMMODATION_BOOKING_REVIEW)))
            .andExpect(jsonPath("$.[*].attractionPrice").value(hasItem(DEFAULT_ATTRACTION_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].attractionReview").value(hasItem(DEFAULT_ATTRACTION_REVIEW)))
            .andExpect(jsonPath("$.[*].travelCost").value(hasItem(DEFAULT_TRAVEL_COST.doubleValue())));
    }

    @Test
    @Transactional
    void getTravelReview() throws Exception {
        // Initialize the database
        travelReviewRepository.saveAndFlush(travelReview);

        // Get the travelReview
        restTravelReviewMockMvc
            .perform(get(ENTITY_API_URL_ID, travelReview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(travelReview.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.travelOrigin").value(DEFAULT_TRAVEL_ORIGIN))
            .andExpect(jsonPath("$.travelDestination").value(DEFAULT_TRAVEL_DESTINATION))
            .andExpect(jsonPath("$.travelWithFlight").value(DEFAULT_TRAVEL_WITH_FLIGHT.booleanValue()))
            .andExpect(jsonPath("$.travelStartDate").value(DEFAULT_TRAVEL_START_DATE.toString()))
            .andExpect(jsonPath("$.travelEndDate").value(DEFAULT_TRAVEL_END_DATE.toString()))
            .andExpect(jsonPath("$.flightTicketNumber").value(DEFAULT_FLIGHT_TICKET_NUMBER))
            .andExpect(jsonPath("$.flightClass").value(DEFAULT_FLIGHT_CLASS))
            .andExpect(jsonPath("$.flightDuration").value(DEFAULT_FLIGHT_DURATION.doubleValue()))
            .andExpect(jsonPath("$.flightPrice").value(DEFAULT_FLIGHT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.flightReview").value(DEFAULT_FLIGHT_REVIEW))
            .andExpect(jsonPath("$.accommodationBookingNumber").value(DEFAULT_ACCOMMODATION_BOOKING_NUMBER))
            .andExpect(jsonPath("$.accommodationBookingPrice").value(DEFAULT_ACCOMMODATION_BOOKING_PRICE.doubleValue()))
            .andExpect(jsonPath("$.accommodationBookingReview").value(DEFAULT_ACCOMMODATION_BOOKING_REVIEW))
            .andExpect(jsonPath("$.attractionPrice").value(DEFAULT_ATTRACTION_PRICE.doubleValue()))
            .andExpect(jsonPath("$.attractionReview").value(DEFAULT_ATTRACTION_REVIEW))
            .andExpect(jsonPath("$.travelCost").value(DEFAULT_TRAVEL_COST.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingTravelReview() throws Exception {
        // Get the travelReview
        restTravelReviewMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
