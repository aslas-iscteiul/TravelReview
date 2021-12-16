package com.iscte.travelreview.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.iscte.travelreview.IntegrationTest;
import com.iscte.travelreview.domain.Attraction;
import com.iscte.travelreview.repository.AttractionRepository;
import com.iscte.travelreview.service.dto.AttractionDTO;
import com.iscte.travelreview.service.mapper.AttractionMapper;
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
 * Integration tests for the {@link AttractionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AttractionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/attractions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private AttractionMapper attractionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttractionMockMvc;

    private Attraction attraction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Attraction createEntity(EntityManager em) {
        Attraction attraction = new Attraction().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
        return attraction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Attraction createUpdatedEntity(EntityManager em) {
        Attraction attraction = new Attraction().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        return attraction;
    }

    @BeforeEach
    public void initTest() {
        attraction = createEntity(em);
    }

    @Test
    @Transactional
    void createAttraction() throws Exception {
        int databaseSizeBeforeCreate = attractionRepository.findAll().size();
        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);
        restAttractionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(attractionDTO)))
            .andExpect(status().isCreated());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeCreate + 1);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAttraction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createAttractionWithExistingId() throws Exception {
        // Create the Attraction with an existing ID
        attraction.setId(1L);
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        int databaseSizeBeforeCreate = attractionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttractionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(attractionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAttractions() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        // Get all the attractionList
        restAttractionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attraction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getAttraction() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        // Get the attraction
        restAttractionMockMvc
            .perform(get(ENTITY_API_URL_ID, attraction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attraction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingAttraction() throws Exception {
        // Get the attraction
        restAttractionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAttraction() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();

        // Update the attraction
        Attraction updatedAttraction = attractionRepository.findById(attraction.getId()).get();
        // Disconnect from session so that the updates on updatedAttraction are not directly saved in db
        em.detach(updatedAttraction);
        updatedAttraction.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        AttractionDTO attractionDTO = attractionMapper.toDto(updatedAttraction);

        restAttractionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attractionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttraction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attractionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(attractionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAttractionWithPatch() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();

        // Update the attraction using partial update
        Attraction partialUpdatedAttraction = new Attraction();
        partialUpdatedAttraction.setId(attraction.getId());

        partialUpdatedAttraction.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restAttractionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttraction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAttraction))
            )
            .andExpect(status().isOk());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttraction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateAttractionWithPatch() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();

        // Update the attraction using partial update
        Attraction partialUpdatedAttraction = new Attraction();
        partialUpdatedAttraction.setId(attraction.getId());

        partialUpdatedAttraction.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restAttractionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttraction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAttraction))
            )
            .andExpect(status().isOk());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttraction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, attractionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();
        attraction.setId(count.incrementAndGet());

        // Create the Attraction
        AttractionDTO attractionDTO = attractionMapper.toDto(attraction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttractionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(attractionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAttraction() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        int databaseSizeBeforeDelete = attractionRepository.findAll().size();

        // Delete the attraction
        restAttractionMockMvc
            .perform(delete(ENTITY_API_URL_ID, attraction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
