package com.iscte.travelreview.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.iscte.travelreview.IntegrationTest;
import com.iscte.travelreview.domain.Accommodation;
import com.iscte.travelreview.repository.AccommodationRepository;
import com.iscte.travelreview.service.dto.AccommodationDTO;
import com.iscte.travelreview.service.mapper.AccommodationMapper;
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
 * Integration tests for the {@link AccommodationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AccommodationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SUSTAINABLE_ACCOMMODATION = false;
    private static final Boolean UPDATED_SUSTAINABLE_ACCOMMODATION = true;

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_CLASSIFICATION = 1D;
    private static final Double UPDATED_CLASSIFICATION = 2D;

    private static final String ENTITY_API_URL = "/api/accommodations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationMapper accommodationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccommodationMockMvc;

    private Accommodation accommodation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accommodation createEntity(EntityManager em) {
        Accommodation accommodation = new Accommodation()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .sustainableAccommodation(DEFAULT_SUSTAINABLE_ACCOMMODATION)
            .website(DEFAULT_WEBSITE)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .classification(DEFAULT_CLASSIFICATION);
        return accommodation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accommodation createUpdatedEntity(EntityManager em) {
        Accommodation accommodation = new Accommodation()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .sustainableAccommodation(UPDATED_SUSTAINABLE_ACCOMMODATION)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .classification(UPDATED_CLASSIFICATION);
        return accommodation;
    }

    @BeforeEach
    public void initTest() {
        accommodation = createEntity(em);
    }

    @Test
    @Transactional
    void createAccommodation() throws Exception {
        int databaseSizeBeforeCreate = accommodationRepository.findAll().size();
        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);
        restAccommodationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeCreate + 1);
        Accommodation testAccommodation = accommodationList.get(accommodationList.size() - 1);
        assertThat(testAccommodation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAccommodation.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAccommodation.getSustainableAccommodation()).isEqualTo(DEFAULT_SUSTAINABLE_ACCOMMODATION);
        assertThat(testAccommodation.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testAccommodation.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAccommodation.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testAccommodation.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAccommodation.getClassification()).isEqualTo(DEFAULT_CLASSIFICATION);
    }

    @Test
    @Transactional
    void createAccommodationWithExistingId() throws Exception {
        // Create the Accommodation with an existing ID
        accommodation.setId(1L);
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        int databaseSizeBeforeCreate = accommodationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccommodationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAccommodations() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        // Get all the accommodationList
        restAccommodationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accommodation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].sustainableAccommodation").value(hasItem(DEFAULT_SUSTAINABLE_ACCOMMODATION.booleanValue())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].classification").value(hasItem(DEFAULT_CLASSIFICATION.doubleValue())));
    }

    @Test
    @Transactional
    void getAccommodation() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        // Get the accommodation
        restAccommodationMockMvc
            .perform(get(ENTITY_API_URL_ID, accommodation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accommodation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.sustainableAccommodation").value(DEFAULT_SUSTAINABLE_ACCOMMODATION.booleanValue()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.classification").value(DEFAULT_CLASSIFICATION.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingAccommodation() throws Exception {
        // Get the accommodation
        restAccommodationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAccommodation() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();

        // Update the accommodation
        Accommodation updatedAccommodation = accommodationRepository.findById(accommodation.getId()).get();
        // Disconnect from session so that the updates on updatedAccommodation are not directly saved in db
        em.detach(updatedAccommodation);
        updatedAccommodation
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .sustainableAccommodation(UPDATED_SUSTAINABLE_ACCOMMODATION)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .classification(UPDATED_CLASSIFICATION);
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(updatedAccommodation);

        restAccommodationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accommodationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isOk());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
        Accommodation testAccommodation = accommodationList.get(accommodationList.size() - 1);
        assertThat(testAccommodation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccommodation.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAccommodation.getSustainableAccommodation()).isEqualTo(UPDATED_SUSTAINABLE_ACCOMMODATION);
        assertThat(testAccommodation.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAccommodation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAccommodation.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAccommodation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAccommodation.getClassification()).isEqualTo(UPDATED_CLASSIFICATION);
    }

    @Test
    @Transactional
    void putNonExistingAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accommodationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAccommodationWithPatch() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();

        // Update the accommodation using partial update
        Accommodation partialUpdatedAccommodation = new Accommodation();
        partialUpdatedAccommodation.setId(accommodation.getId());

        partialUpdatedAccommodation.name(UPDATED_NAME).phone(UPDATED_PHONE).address(UPDATED_ADDRESS);

        restAccommodationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccommodation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccommodation))
            )
            .andExpect(status().isOk());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
        Accommodation testAccommodation = accommodationList.get(accommodationList.size() - 1);
        assertThat(testAccommodation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccommodation.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAccommodation.getSustainableAccommodation()).isEqualTo(DEFAULT_SUSTAINABLE_ACCOMMODATION);
        assertThat(testAccommodation.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testAccommodation.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAccommodation.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAccommodation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAccommodation.getClassification()).isEqualTo(DEFAULT_CLASSIFICATION);
    }

    @Test
    @Transactional
    void fullUpdateAccommodationWithPatch() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();

        // Update the accommodation using partial update
        Accommodation partialUpdatedAccommodation = new Accommodation();
        partialUpdatedAccommodation.setId(accommodation.getId());

        partialUpdatedAccommodation
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .sustainableAccommodation(UPDATED_SUSTAINABLE_ACCOMMODATION)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .classification(UPDATED_CLASSIFICATION);

        restAccommodationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccommodation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccommodation))
            )
            .andExpect(status().isOk());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
        Accommodation testAccommodation = accommodationList.get(accommodationList.size() - 1);
        assertThat(testAccommodation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccommodation.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAccommodation.getSustainableAccommodation()).isEqualTo(UPDATED_SUSTAINABLE_ACCOMMODATION);
        assertThat(testAccommodation.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAccommodation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAccommodation.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAccommodation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAccommodation.getClassification()).isEqualTo(UPDATED_CLASSIFICATION);
    }

    @Test
    @Transactional
    void patchNonExistingAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, accommodationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAccommodation() throws Exception {
        int databaseSizeBeforeUpdate = accommodationRepository.findAll().size();
        accommodation.setId(count.incrementAndGet());

        // Create the Accommodation
        AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccommodationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accommodationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Accommodation in the database
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAccommodation() throws Exception {
        // Initialize the database
        accommodationRepository.saveAndFlush(accommodation);

        int databaseSizeBeforeDelete = accommodationRepository.findAll().size();

        // Delete the accommodation
        restAccommodationMockMvc
            .perform(delete(ENTITY_API_URL_ID, accommodation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Accommodation> accommodationList = accommodationRepository.findAll();
        assertThat(accommodationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
