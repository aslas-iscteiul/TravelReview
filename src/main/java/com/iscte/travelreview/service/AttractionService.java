package com.iscte.travelreview.service;

import com.iscte.travelreview.domain.Attraction;
import com.iscte.travelreview.repository.AttractionRepository;
import com.iscte.travelreview.service.dto.AttractionDTO;
import com.iscte.travelreview.service.mapper.AttractionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Attraction}.
 */
@Service
@Transactional
public class AttractionService {

    private final Logger log = LoggerFactory.getLogger(AttractionService.class);

    private final AttractionRepository attractionRepository;

    private final AttractionMapper attractionMapper;

    public AttractionService(AttractionRepository attractionRepository, AttractionMapper attractionMapper) {
        this.attractionRepository = attractionRepository;
        this.attractionMapper = attractionMapper;
    }

    /**
     * Save a attraction.
     *
     * @param attractionDTO the entity to save.
     * @return the persisted entity.
     */
    public AttractionDTO save(AttractionDTO attractionDTO) {
        log.debug("Request to save Attraction : {}", attractionDTO);
        Attraction attraction = attractionMapper.toEntity(attractionDTO);
        attraction = attractionRepository.save(attraction);
        return attractionMapper.toDto(attraction);
    }

    /**
     * Partially update a attraction.
     *
     * @param attractionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AttractionDTO> partialUpdate(AttractionDTO attractionDTO) {
        log.debug("Request to partially update Attraction : {}", attractionDTO);

        return attractionRepository
            .findById(attractionDTO.getId())
            .map(
                existingAttraction -> {
                    attractionMapper.partialUpdate(existingAttraction, attractionDTO);
                    return existingAttraction;
                }
            )
            .map(attractionRepository::save)
            .map(attractionMapper::toDto);
    }

    /**
     * Get all the attractions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttractionDTO> findAll() {
        log.debug("Request to get all Attractions");
        return attractionRepository.findAll().stream().map(attractionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one attraction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttractionDTO> findOne(Long id) {
        log.debug("Request to get Attraction : {}", id);
        return attractionRepository.findById(id).map(attractionMapper::toDto);
    }

    /**
     * Delete the attraction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Attraction : {}", id);
        attractionRepository.deleteById(id);
    }
}
