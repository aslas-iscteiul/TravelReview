package com.iscte.travelreview.delegate;

import com.iscte.travelreview.service.dto.ScoreDTO;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GenerateTravelScoreDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int score = 0;
        int counter = 0;

        TravelReviewProcessDTO travelReviewProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");

        try {
            score = travelReviewProcess.getTravelReview().getFlightScore().getNumber();
            counter++;
        } catch (Exception e) {

        }
        try {
            score = score + travelReviewProcess.getTravelReview().getBookingScore().getNumber();
            counter++;
        } catch (Exception e) {

        }
        score = score + travelReviewProcess.getTravelReview().getAttractionScore().getNumber();
        counter++;

        score = score / counter;
        delegateExecution.setVariable("travelScore", score);
        travelReviewProcess.getTravelReview().setTravelScore(score + "");
    }
}
