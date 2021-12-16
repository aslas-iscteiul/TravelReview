package com.iscte.travelreview.web.rest;

import com.iscte.travelreview.repository.TravelReviewRepository;
import com.iscte.travelreview.service.TravelReviewService;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
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
 * REST controller for managing {@link com.iscte.travelreview.domain.TravelReview}.
 */
@RestController
@RequestMapping("/api")
public class TravelReviewResource {

    private final Logger log = LoggerFactory.getLogger(TravelReviewResource.class);

    private final TravelReviewService travelReviewService;

    private final TravelReviewRepository travelReviewRepository;

    public TravelReviewResource(TravelReviewService travelReviewService, TravelReviewRepository travelReviewRepository) {
        this.travelReviewService = travelReviewService;
        this.travelReviewRepository = travelReviewRepository;
    }

    /**
     * {@code GET  /travel-reviews} : get all the travelReviews.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelReviews in body.
     */
    @GetMapping("/travel-reviews")
    public List<TravelReviewDTO> getAllTravelReviews() {
        log.debug("REST request to get all TravelReviews");
        return travelReviewService.findAll();
    }

    /**
     * {@code GET  /travel-reviews/:id} : get the "id" travelReview.
     *
     * @param id the id of the travelReviewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelReviewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-reviews/{id}")
    public ResponseEntity<TravelReviewDTO> getTravelReview(@PathVariable Long id) {
        log.debug("REST request to get TravelReview : {}", id);
        Optional<TravelReviewDTO> travelReviewDTO = travelReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(travelReviewDTO);
    }
}
