package com.iscte.travelreview.service;

import com.iscte.travelreview.domain.Accommodation;
import com.iscte.travelreview.repository.AccommodationRepository;
import com.iscte.travelreview.service.dto.AccommodationDTO;
import com.iscte.travelreview.service.mapper.AccommodationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Accommodation}.
 */
@Service
@Transactional
public class AccommodationService {

    private final Logger log = LoggerFactory.getLogger(AccommodationService.class);

    private final AccommodationRepository accommodationRepository;

    private final AccommodationMapper accommodationMapper;

    public AccommodationService(AccommodationRepository accommodationRepository, AccommodationMapper accommodationMapper) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
    }

    /**
     * Save a accommodation.
     *
     * @param accommodationDTO the entity to save.
     * @return the persisted entity.
     */
    public AccommodationDTO save(AccommodationDTO accommodationDTO) {
        log.debug("Request to save Accommodation : {}", accommodationDTO);
        Accommodation accommodation = accommodationMapper.toEntity(accommodationDTO);
        accommodation = accommodationRepository.save(accommodation);
        return accommodationMapper.toDto(accommodation);
    }

    /**
     * Partially update a accommodation.
     *
     * @param accommodationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AccommodationDTO> partialUpdate(AccommodationDTO accommodationDTO) {
        log.debug("Request to partially update Accommodation : {}", accommodationDTO);

        return accommodationRepository
            .findById(accommodationDTO.getId())
            .map(
                existingAccommodation -> {
                    accommodationMapper.partialUpdate(existingAccommodation, accommodationDTO);
                    return existingAccommodation;
                }
            )
            .map(accommodationRepository::save)
            .map(accommodationMapper::toDto);
    }

    /**
     * Get all the accommodations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AccommodationDTO> findAll() {
        log.debug("Request to get all Accommodations");
        return accommodationRepository.findAll().stream().map(accommodationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one accommodation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccommodationDTO> findOne(Long id) {
        log.debug("Request to get Accommodation : {}", id);
        return accommodationRepository.findById(id).map(accommodationMapper::toDto);
    }

    /**
     * Delete the accommodation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Accommodation : {}", id);
        accommodationRepository.deleteById(id);
    }
}
