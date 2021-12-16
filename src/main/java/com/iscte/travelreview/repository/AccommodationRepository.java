package com.iscte.travelreview.repository;

import com.iscte.travelreview.domain.Accommodation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Accommodation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {}
