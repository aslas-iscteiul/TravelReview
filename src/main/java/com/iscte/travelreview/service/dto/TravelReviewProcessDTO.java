package com.iscte.travelreview.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.iscte.travelreview.domain.TravelReviewProcess} entity.
 */
public class TravelReviewProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private TravelReviewDTO travelReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public TravelReviewDTO getTravelReview() {
        return travelReview;
    }

    public void setTravelReview(TravelReviewDTO travelReview) {
        this.travelReview = travelReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelReviewProcessDTO)) {
            return false;
        }

        TravelReviewProcessDTO travelReviewProcessDTO = (TravelReviewProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, travelReviewProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelReviewProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", travelReview=" + getTravelReview() +
            "}";
    }
}
