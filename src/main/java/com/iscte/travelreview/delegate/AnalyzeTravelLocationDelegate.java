package com.iscte.travelreview.delegate;

import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class AnalyzeTravelLocationDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelReviewProcessDTO travelReviewProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");
        //String origin = travelReviewProcess.getTravelReview().getTravelOrigin();
        //String destination = travelReviewProcess.getTravelReview().getTravelDestination();
        //Boolean originEqualDestination = origin.equals(destination);
        //delegateExecution.setVariable("originEqualDestination", originEqualDestination);
        delegateExecution.setVariable("travelWithFlight", travelReviewProcess.getTravelReview().getTravelWithFlight());
    }
}
