package com.iscte.travelreview.delegate;

import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GenerateTravelCostDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        double flightPrice;
        double bookingPrice;
        double attractionPrice;

        TravelReviewProcessDTO travelPlanProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");
        try {
            flightPrice = travelPlanProcess.getTravelReview().getFlightPrice();
        } catch (NullPointerException e) {
            flightPrice = 0;
        }
        try {
            bookingPrice = travelPlanProcess.getTravelReview().getAccommodationBookingPrice();
        } catch (NullPointerException e) {
            bookingPrice = 0;
        }
        try {
            attractionPrice = travelPlanProcess.getTravelReview().getAttractionPrice();
        } catch (NullPointerException e) {
            attractionPrice = 0;
        }

        double travelCost = flightPrice+bookingPrice+attractionPrice;

        travelPlanProcess.getTravelReview().setTravelCost(travelCost);
    }
}
