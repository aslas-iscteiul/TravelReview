package com.iscte.travelreview.service;

import com.iscte.travelreview.domain.TravelReview;
import com.iscte.travelreview.repository.TravelReviewRepository;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import com.iscte.travelreview.service.mapper.TravelReviewMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TravelReview}.
 */
@Service
@Transactional
public class TravelReviewService {

    private final Logger log = LoggerFactory.getLogger(TravelReviewService.class);

    private final TravelReviewRepository travelReviewRepository;

    private final TravelReviewMapper travelReviewMapper;

    public TravelReviewService(TravelReviewRepository travelReviewRepository, TravelReviewMapper travelReviewMapper) {
        this.travelReviewRepository = travelReviewRepository;
        this.travelReviewMapper = travelReviewMapper;
    }

    /**
     * Save a travelReview.
     *
     * @param travelReviewDTO the entity to save.
     * @return the persisted entity.
     */
    public TravelReviewDTO save(TravelReviewDTO travelReviewDTO) {
        log.debug("Request to save TravelReview : {}", travelReviewDTO);
        TravelReview travelReview = travelReviewMapper.toEntity(travelReviewDTO);
        travelReview = travelReviewRepository.save(travelReview);
        return travelReviewMapper.toDto(travelReview);
    }

    /**
     * Partially update a travelReview.
     *
     * @param travelReviewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TravelReviewDTO> partialUpdate(TravelReviewDTO travelReviewDTO) {
        log.debug("Request to partially update TravelReview : {}", travelReviewDTO);

        return travelReviewRepository
            .findById(travelReviewDTO.getId())
            .map(
                existingTravelReview -> {
                    travelReviewMapper.partialUpdate(existingTravelReview, travelReviewDTO);
                    return existingTravelReview;
                }
            )
            .map(travelReviewRepository::save)
            .map(travelReviewMapper::toDto);
    }

    /**
     * Get all the travelReviews.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TravelReviewDTO> findAll() {
        log.debug("Request to get all TravelReviews");
        return travelReviewRepository.findAll().stream().map(travelReviewMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one travelReview by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelReviewDTO> findOne(Long id) {
        log.debug("Request to get TravelReview : {}", id);
        return travelReviewRepository.findById(id).map(travelReviewMapper::toDto);
    }

    /**
     * Delete the travelReview by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TravelReview : {}", id);
        travelReviewRepository.deleteById(id);
    }
}
