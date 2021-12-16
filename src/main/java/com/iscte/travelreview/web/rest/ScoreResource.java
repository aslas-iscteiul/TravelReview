package com.iscte.travelreview.web.rest;

import com.iscte.travelreview.repository.ScoreRepository;
import com.iscte.travelreview.service.ScoreService;
import com.iscte.travelreview.service.dto.ScoreDTO;
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
 * REST controller for managing {@link com.iscte.travelreview.domain.Score}.
 */
@RestController
@RequestMapping("/api")
public class ScoreResource {

    private final Logger log = LoggerFactory.getLogger(ScoreResource.class);

    private static final String ENTITY_NAME = "score";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScoreService scoreService;

    private final ScoreRepository scoreRepository;

    public ScoreResource(ScoreService scoreService, ScoreRepository scoreRepository) {
        this.scoreService = scoreService;
        this.scoreRepository = scoreRepository;
    }

    /**
     * {@code POST  /scores} : Create a new score.
     *
     * @param scoreDTO the scoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreDTO, or with status {@code 400 (Bad Request)} if the score has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/scores")
    public ResponseEntity<ScoreDTO> createScore(@RequestBody ScoreDTO scoreDTO) throws URISyntaxException {
        log.debug("REST request to save Score : {}", scoreDTO);
        if (scoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new score cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScoreDTO result = scoreService.save(scoreDTO);
        return ResponseEntity
            .created(new URI("/api/scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /scores/:id} : Updates an existing score.
     *
     * @param id the id of the scoreDTO to save.
     * @param scoreDTO the scoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreDTO,
     * or with status {@code 400 (Bad Request)} if the scoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/scores/{id}")
    public ResponseEntity<ScoreDTO> updateScore(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ScoreDTO scoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Score : {}, {}", id, scoreDTO);
        if (scoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ScoreDTO result = scoreService.save(scoreDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /scores/:id} : Partial updates given fields of an existing score, field will ignore if it is null
     *
     * @param id the id of the scoreDTO to save.
     * @param scoreDTO the scoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreDTO,
     * or with status {@code 400 (Bad Request)} if the scoreDTO is not valid,
     * or with status {@code 404 (Not Found)} if the scoreDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the scoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/scores/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ScoreDTO> partialUpdateScore(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ScoreDTO scoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Score partially : {}, {}", id, scoreDTO);
        if (scoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ScoreDTO> result = scoreService.partialUpdate(scoreDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scoreDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /scores} : get all the scores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scores in body.
     */
    @GetMapping("/scores")
    public List<ScoreDTO> getAllScores() {
        log.debug("REST request to get all Scores");
        return scoreService.findAll();
    }

    /**
     * {@code GET  /scores/:id} : get the "id" score.
     *
     * @param id the id of the scoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/scores/{id}")
    public ResponseEntity<ScoreDTO> getScore(@PathVariable Long id) {
        log.debug("REST request to get Score : {}", id);
        Optional<ScoreDTO> scoreDTO = scoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scoreDTO);
    }

    /**
     * {@code DELETE  /scores/:id} : delete the "id" score.
     *
     * @param id the id of the scoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        log.debug("REST request to delete Score : {}", id);
        scoreService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
