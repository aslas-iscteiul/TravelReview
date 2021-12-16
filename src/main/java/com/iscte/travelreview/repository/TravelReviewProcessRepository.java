package com.iscte.travelreview.repository;

import com.iscte.travelreview.domain.TravelReviewProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TravelReviewProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelReviewProcessRepository extends JpaRepository<TravelReviewProcess, Long> {
    Optional<TravelReviewProcess> findByProcessInstanceId(Long processInstanceId);
}
