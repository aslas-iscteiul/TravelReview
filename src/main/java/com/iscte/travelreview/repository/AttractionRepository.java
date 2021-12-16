package com.iscte.travelreview.repository;

import com.iscte.travelreview.domain.Attraction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Attraction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {}
