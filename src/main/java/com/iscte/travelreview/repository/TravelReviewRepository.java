package com.iscte.travelreview.repository;

import com.iscte.travelreview.domain.TravelReview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TravelReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelReviewRepository extends JpaRepository<TravelReview, Long> {}
