package com.iscte.travelreview.web.rest;

import com.iscte.travelreview.repository.AccommodationRepository;
import com.iscte.travelreview.service.AccommodationService;
import com.iscte.travelreview.service.dto.AccommodationDTO;
import com.iscte.travelreview.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.iscte.travelreview.domain.Accommodation}.
 */
@RestController
@RequestMapping("/api")
public class AccommodationResource {

    private final Logger log = LoggerFactory.getLogger(AccommodationResource.class);

    private static final String ENTITY_NAME = "accommodation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccommodationService accommodationService;

    private final AccommodationRepository accommodationRepository;

    public AccommodationResource(AccommodationService accommodationService, AccommodationRepository accommodationRepository) {
        this.accommodationService = accommodationService;
        this.accommodationRepository = accommodationRepository;
    }

    /**
     * {@code POST  /accommodations} : Create a new accommodation.
     *
     * @param accommodationDTO the accommodationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accommodationDTO, or with status {@code 400 (Bad Request)} if the accommodation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accommodations")
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO) throws URISyntaxException {
        log.debug("REST request to save Accommodation : {}", accommodationDTO);
        if (accommodationDTO.getId() != null) {
            throw new BadRequestAlertException("A new accommodation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccommodationDTO result = accommodationService.save(accommodationDTO);
        return ResponseEntity
            .created(new URI("/api/accommodations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /accommodations/:id} : Updates an existing accommodation.
     *
     * @param id the id of the accommodationDTO to save.
     * @param accommodationDTO the accommodationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accommodationDTO,
     * or with status {@code 400 (Bad Request)} if the accommodationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accommodationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/accommodations/{id}")
    public ResponseEntity<AccommodationDTO> updateAccommodation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AccommodationDTO accommodationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Accommodation : {}, {}", id, accommodationDTO);
        if (accommodationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accommodationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accommodationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AccommodationDTO result = accommodationService.save(accommodationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accommodationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /accommodations/:id} : Partial updates given fields of an existing accommodation, field will ignore if it is null
     *
     * @param id the id of the accommodationDTO to save.
     * @param accommodationDTO the accommodationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accommodationDTO,
     * or with status {@code 400 (Bad Request)} if the accommodationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the accommodationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the accommodationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/accommodations/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AccommodationDTO> partialUpdateAccommodation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AccommodationDTO accommodationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Accommodation partially : {}, {}", id, accommodationDTO);
        if (accommodationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accommodationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accommodationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AccommodationDTO> result = accommodationService.partialUpdate(accommodationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accommodationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /accommodations} : get all the accommodations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accommodations in body.
     */
    @GetMapping("/accommodations")
    public List<AccommodationDTO> getAllAccommodations() {
        log.debug("REST request to get all Accommodations");
        return accommodationService.findAll();
    }

    /**
     * {@code GET  /accommodations/:id} : get the "id" accommodation.
     *
     * @param id the id of the accommodationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accommodationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accommodations/{id}")
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable Long id) {
        log.debug("REST request to get Accommodation : {}", id);
        Optional<AccommodationDTO> accommodationDTO = accommodationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accommodationDTO);
    }

    /**
     * {@code DELETE  /accommodations/:id} : delete the "id" accommodation.
     *
     * @param id the id of the accommodationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/accommodations/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        log.debug("REST request to delete Accommodation : {}", id);
        accommodationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
