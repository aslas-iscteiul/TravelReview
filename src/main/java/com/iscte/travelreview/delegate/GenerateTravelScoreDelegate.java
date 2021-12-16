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
        int flightScore;
        int bookingScore;
        int attractionScore;
        int score = 0;

        TravelReviewProcessDTO travelReviewProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");

        try {
            flightScore = travelReviewProcess.getTravelReview().getFlightScore().getNumber();
        } catch (Exception e) {
            flightScore = 0;
        }
        try {
            bookingScore = travelReviewProcess.getTravelReview().getBookingScore().getNumber();
        } catch (Exception e) {
            bookingScore = 0;
        }
        try {
            attractionScore = travelReviewProcess.getTravelReview().getAttractionScore().getNumber();
        } catch (Exception e) {
            attractionScore = 0;
        }

        if(flightScore != 0 && bookingScore != 0 && attractionScore != 0)
            score = (flightScore + bookingScore + attractionScore) / 3;
        else if(flightScore == 0 && bookingScore != 0 && attractionScore != 0)
            score = (bookingScore + attractionScore) / 2;
        else if(flightScore != 0 && bookingScore == 0 && attractionScore != 0)
            score = (flightScore + attractionScore) / 2;
        else if(flightScore != 0 && bookingScore != 0 && attractionScore == 0)
            score = (bookingScore + flightScore) / 2;
        else if(flightScore != 0 && bookingScore == 0 && attractionScore == 0)
            score = flightScore;
        else if(flightScore == 0 && bookingScore != 0 && attractionScore == 0)
            score = bookingScore;
        else if(flightScore == 0 && bookingScore == 0 && attractionScore != 0)
            score = attractionScore;

        ScoreDTO scoreObject = travelReviewProcess.getTravelReview().getAttractionScore();
        scoreObject.setNumber(score);
        travelReviewProcess.getTravelReview().setTravelScore(scoreObject);
    }
}
