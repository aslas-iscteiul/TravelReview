package com.iscte.travelreview.service;

import com.iscte.travelreview.domain.Score;
import com.iscte.travelreview.repository.ScoreRepository;
import com.iscte.travelreview.service.dto.ScoreDTO;
import com.iscte.travelreview.service.mapper.ScoreMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Score}.
 */
@Service
@Transactional
public class ScoreService {

    private final Logger log = LoggerFactory.getLogger(ScoreService.class);

    private final ScoreRepository scoreRepository;

    private final ScoreMapper scoreMapper;

    public ScoreService(ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    /**
     * Save a score.
     *
     * @param scoreDTO the entity to save.
     * @return the persisted entity.
     */
    public ScoreDTO save(ScoreDTO scoreDTO) {
        log.debug("Request to save Score : {}", scoreDTO);
        Score score = scoreMapper.toEntity(scoreDTO);
        score = scoreRepository.save(score);
        return scoreMapper.toDto(score);
    }

    /**
     * Partially update a score.
     *
     * @param scoreDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ScoreDTO> partialUpdate(ScoreDTO scoreDTO) {
        log.debug("Request to partially update Score : {}", scoreDTO);

        return scoreRepository
            .findById(scoreDTO.getId())
            .map(
                existingScore -> {
                    scoreMapper.partialUpdate(existingScore, scoreDTO);
                    return existingScore;
                }
            )
            .map(scoreRepository::save)
            .map(scoreMapper::toDto);
    }

    /**
     * Get all the scores.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ScoreDTO> findAll() {
        log.debug("Request to get all Scores");
        return scoreRepository.findAll().stream().map(scoreMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one score by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScoreDTO> findOne(Long id) {
        log.debug("Request to get Score : {}", id);
        return scoreRepository.findById(id).map(scoreMapper::toDto);
    }

    /**
     * Delete the score by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Score : {}", id);
        scoreRepository.deleteById(id);
    }
}
