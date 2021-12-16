package com.iscte.travelreview.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.iscte.travelreview.IntegrationTest;
import com.iscte.travelreview.domain.Score;
import com.iscte.travelreview.repository.ScoreRepository;
import com.iscte.travelreview.service.dto.ScoreDTO;
import com.iscte.travelreview.service.mapper.ScoreMapper;
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
 * Integration tests for the {@link ScoreResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ScoreResourceIT {

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/scores";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restScoreMockMvc;

    private Score score;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Score createEntity(EntityManager em) {
        Score score = new Score().number(DEFAULT_NUMBER).description(DEFAULT_DESCRIPTION);
        return score;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Score createUpdatedEntity(EntityManager em) {
        Score score = new Score().number(UPDATED_NUMBER).description(UPDATED_DESCRIPTION);
        return score;
    }

    @BeforeEach
    public void initTest() {
        score = createEntity(em);
    }

    @Test
    @Transactional
    void createScore() throws Exception {
        int databaseSizeBeforeCreate = scoreRepository.findAll().size();
        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);
        restScoreMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scoreDTO)))
            .andExpect(status().isCreated());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeCreate + 1);
        Score testScore = scoreList.get(scoreList.size() - 1);
        assertThat(testScore.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testScore.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createScoreWithExistingId() throws Exception {
        // Create the Score with an existing ID
        score.setId(1L);
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        int databaseSizeBeforeCreate = scoreRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restScoreMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllScores() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        // Get all the scoreList
        restScoreMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(score.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getScore() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        // Get the score
        restScoreMockMvc
            .perform(get(ENTITY_API_URL_ID, score.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(score.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingScore() throws Exception {
        // Get the score
        restScoreMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewScore() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();

        // Update the score
        Score updatedScore = scoreRepository.findById(score.getId()).get();
        // Disconnect from session so that the updates on updatedScore are not directly saved in db
        em.detach(updatedScore);
        updatedScore.number(UPDATED_NUMBER).description(UPDATED_DESCRIPTION);
        ScoreDTO scoreDTO = scoreMapper.toDto(updatedScore);

        restScoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, scoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scoreDTO))
            )
            .andExpect(status().isOk());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
        Score testScore = scoreList.get(scoreList.size() - 1);
        assertThat(testScore.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testScore.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, scoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scoreDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateScoreWithPatch() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();

        // Update the score using partial update
        Score partialUpdatedScore = new Score();
        partialUpdatedScore.setId(score.getId());

        partialUpdatedScore.number(UPDATED_NUMBER);

        restScoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedScore))
            )
            .andExpect(status().isOk());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
        Score testScore = scoreList.get(scoreList.size() - 1);
        assertThat(testScore.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testScore.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateScoreWithPatch() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();

        // Update the score using partial update
        Score partialUpdatedScore = new Score();
        partialUpdatedScore.setId(score.getId());

        partialUpdatedScore.number(UPDATED_NUMBER).description(UPDATED_DESCRIPTION);

        restScoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedScore))
            )
            .andExpect(status().isOk());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
        Score testScore = scoreList.get(scoreList.size() - 1);
        assertThat(testScore.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testScore.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, scoreDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(scoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(scoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamScore() throws Exception {
        int databaseSizeBeforeUpdate = scoreRepository.findAll().size();
        score.setId(count.incrementAndGet());

        // Create the Score
        ScoreDTO scoreDTO = scoreMapper.toDto(score);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScoreMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(scoreDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Score in the database
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteScore() throws Exception {
        // Initialize the database
        scoreRepository.saveAndFlush(score);

        int databaseSizeBeforeDelete = scoreRepository.findAll().size();

        // Delete the score
        restScoreMockMvc
            .perform(delete(ENTITY_API_URL_ID, score.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Score> scoreList = scoreRepository.findAll();
        assertThat(scoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
