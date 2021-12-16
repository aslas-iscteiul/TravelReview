package com.iscte.travelreview.web.rest;

import com.iscte.travelreview.service.TravelReviewProcessService;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import com.iscte.travelreview.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.iscte.travelreview.domain.TravelReviewProcess}.
 */
@RestController
@RequestMapping("/api")
public class TravelReviewProcessResource {

    private final Logger log = LoggerFactory.getLogger(TravelReviewProcessResource.class);

    private static final String ENTITY_NAME = "travelReviewProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelReviewProcessService travelReviewProcessService;

    public TravelReviewProcessResource(TravelReviewProcessService travelReviewProcessService) {
        this.travelReviewProcessService = travelReviewProcessService;
    }

    /**
     * {@code POST  /travel-review-processes} : Create a new travelReviewProcess.
     *
     * @param travelReviewProcessDTO the travelReviewProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelReviewProcessDTO, or with status {@code 400 (Bad Request)} if the travelReviewProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-review-processes")
    public ResponseEntity<TravelReviewProcessDTO> create(@RequestBody TravelReviewProcessDTO travelReviewProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save TravelReviewProcess : {}", travelReviewProcessDTO);
        if (travelReviewProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new travelReviewProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelReviewProcessDTO result = travelReviewProcessService.create(travelReviewProcessDTO);
        return ResponseEntity
            .created(new URI("/api/travel-review-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-review-processes} : get all the travelReviewProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelReviewProcesss in body.
     */
    @GetMapping("/travel-review-processes")
    public List<TravelReviewProcessDTO> getAllTravelReviewProcesss() {
        log.debug("REST request to get all TravelReviewProcesss");
        return travelReviewProcessService.findAll();
    }

    /**
     * {@code GET  /travel-review-processes/:id} : get the "id" travelReviewProcess.
     *
     * @param processInstanceId the id of the travelReviewProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelReviewProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-review-processes/{processInstanceId}")
    public ResponseEntity<TravelReviewProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get TravelReviewProcess by processInstanceId : {}", processInstanceId);
        Optional<TravelReviewProcessDTO> travelReviewProcessDTO = travelReviewProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(travelReviewProcessDTO);
    }
}
