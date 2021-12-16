package com.iscte.travelreview.delegate;

import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class AnalyzeTravelDatesDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelReviewProcessDTO travelPlanProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");
        if(travelPlanProcess.getTravelReview().getTravelEndDate().isBefore(travelPlanProcess.getTravelReview().getTravelStartDate()))
            throw new BpmnError("Error_Invalid_Dates", "The travel dates are invalid, please correct dates.");
        Boolean travelWithAccommodation = travelPlanProcess.getTravelReview().getTravelEndDate().isAfter(travelPlanProcess.getTravelReview().getTravelStartDate());
        delegateExecution.setVariable("travelWithAccommodation", travelWithAccommodation);
    }
}
