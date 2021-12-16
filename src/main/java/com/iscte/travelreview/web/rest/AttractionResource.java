package com.iscte.travelreview.web.rest;

import com.iscte.travelreview.repository.AttractionRepository;
import com.iscte.travelreview.service.AttractionService;
import com.iscte.travelreview.service.dto.AttractionDTO;
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
 * REST controller for managing {@link com.iscte.travelreview.domain.Attraction}.
 */
@RestController
@RequestMapping("/api")
public class AttractionResource {

    private final Logger log = LoggerFactory.getLogger(AttractionResource.class);

    private static final String ENTITY_NAME = "attraction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttractionService attractionService;

    private final AttractionRepository attractionRepository;

    public AttractionResource(AttractionService attractionService, AttractionRepository attractionRepository) {
        this.attractionService = attractionService;
        this.attractionRepository = attractionRepository;
    }

    /**
     * {@code POST  /attractions} : Create a new attraction.
     *
     * @param attractionDTO the attractionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attractionDTO, or with status {@code 400 (Bad Request)} if the attraction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attractions")
    public ResponseEntity<AttractionDTO> createAttraction(@RequestBody AttractionDTO attractionDTO) throws URISyntaxException {
        log.debug("REST request to save Attraction : {}", attractionDTO);
        if (attractionDTO.getId() != null) {
            throw new BadRequestAlertException("A new attraction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttractionDTO result = attractionService.save(attractionDTO);
        return ResponseEntity
            .created(new URI("/api/attractions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attractions/:id} : Updates an existing attraction.
     *
     * @param id the id of the attractionDTO to save.
     * @param attractionDTO the attractionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attractionDTO,
     * or with status {@code 400 (Bad Request)} if the attractionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attractionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attractions/{id}")
    public ResponseEntity<AttractionDTO> updateAttraction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AttractionDTO attractionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Attraction : {}, {}", id, attractionDTO);
        if (attractionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, attractionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!attractionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AttractionDTO result = attractionService.save(attractionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attractionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /attractions/:id} : Partial updates given fields of an existing attraction, field will ignore if it is null
     *
     * @param id the id of the attractionDTO to save.
     * @param attractionDTO the attractionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attractionDTO,
     * or with status {@code 400 (Bad Request)} if the attractionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the attractionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the attractionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/attractions/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AttractionDTO> partialUpdateAttraction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AttractionDTO attractionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Attraction partially : {}, {}", id, attractionDTO);
        if (attractionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, attractionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!attractionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AttractionDTO> result = attractionService.partialUpdate(attractionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attractionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /attractions} : get all the attractions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attractions in body.
     */
    @GetMapping("/attractions")
    public List<AttractionDTO> getAllAttractions() {
        log.debug("REST request to get all Attractions");
        return attractionService.findAll();
    }

    /**
     * {@code GET  /attractions/:id} : get the "id" attraction.
     *
     * @param id the id of the attractionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attractionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attractions/{id}")
    public ResponseEntity<AttractionDTO> getAttraction(@PathVariable Long id) {
        log.debug("REST request to get Attraction : {}", id);
        Optional<AttractionDTO> attractionDTO = attractionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attractionDTO);
    }

    /**
     * {@code DELETE  /attractions/:id} : delete the "id" attraction.
     *
     * @param id the id of the attractionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attractions/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        log.debug("REST request to delete Attraction : {}", id);
        attractionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
