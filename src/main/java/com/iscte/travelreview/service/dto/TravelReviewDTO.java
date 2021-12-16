package com.iscte.travelreview.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.iscte.travelreview.domain.TravelReview} entity.
 */
public class TravelReviewDTO implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String travelOrigin;

    private String travelDestination;

    private Boolean travelWithFlight;

    private LocalDate travelStartDate;

    private LocalDate travelEndDate;

    private String flightTicketNumber;

    private String flightClass;

    private Double flightDuration;

    private Double flightPrice;

    private String flightReview;

    private String accommodationBookingNumber;

    private Double accommodationBookingPrice;

    private String accommodationBookingReview;

    private Double attractionPrice;

    private String attractionReview;

    private Double travelCost;

    private AirlineCompanyDTO airlineCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTravelOrigin() {
        return travelOrigin;
    }

    public void setTravelOrigin(String travelOrigin) {
        this.travelOrigin = travelOrigin;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public Boolean getTravelWithFlight() {
        return travelWithFlight;
    }

    public void setTravelWithFlight(Boolean travelWithFlight) {
        this.travelWithFlight = travelWithFlight;
    }

    public LocalDate getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(LocalDate travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public LocalDate getTravelEndDate() {
        return travelEndDate;
    }

    public void setTravelEndDate(LocalDate travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public String getFlightTicketNumber() {
        return flightTicketNumber;
    }

    public void setFlightTicketNumber(String flightTicketNumber) {
        this.flightTicketNumber = flightTicketNumber;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public Double getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Double flightDuration) {
        this.flightDuration = flightDuration;
    }

    public Double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getFlightReview() {
        return flightReview;
    }

    public void setFlightReview(String flightReview) {
        this.flightReview = flightReview;
    }

    public String getAccommodationBookingNumber() {
        return accommodationBookingNumber;
    }

    public void setAccommodationBookingNumber(String accommodationBookingNumber) {
        this.accommodationBookingNumber = accommodationBookingNumber;
    }

    public Double getAccommodationBookingPrice() {
        return accommodationBookingPrice;
    }

    public void setAccommodationBookingPrice(Double accommodationBookingPrice) {
        this.accommodationBookingPrice = accommodationBookingPrice;
    }

    public String getAccommodationBookingReview() {
        return accommodationBookingReview;
    }

    public void setAccommodationBookingReview(String accommodationBookingReview) {
        this.accommodationBookingReview = accommodationBookingReview;
    }

    public Double getAttractionPrice() {
        return attractionPrice;
    }

    public void setAttractionPrice(Double attractionPrice) {
        this.attractionPrice = attractionPrice;
    }

    public String getAttractionReview() {
        return attractionReview;
    }

    public void setAttractionReview(String attractionReview) {
        this.attractionReview = attractionReview;
    }

    public Double getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(Double travelCost) {
        this.travelCost = travelCost;
    }

    public AirlineCompanyDTO getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompanyDTO airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelReviewDTO)) {
            return false;
        }

        TravelReviewDTO travelReviewDTO = (TravelReviewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, travelReviewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelReviewDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", travelOrigin='" + getTravelOrigin() + "'" +
            ", travelDestination='" + getTravelDestination() + "'" +
            ", travelWithFlight='" + getTravelWithFlight() + "'" +
            ", travelStartDate='" + getTravelStartDate() + "'" +
            ", travelEndDate='" + getTravelEndDate() + "'" +
            ", flightTicketNumber='" + getFlightTicketNumber() + "'" +
            ", flightClass='" + getFlightClass() + "'" +
            ", flightDuration=" + getFlightDuration() +
            ", flightPrice=" + getFlightPrice() +
            ", flightReview='" + getFlightReview() + "'" +
            ", accommodationBookingNumber='" + getAccommodationBookingNumber() + "'" +
            ", accommodationBookingPrice=" + getAccommodationBookingPrice() +
            ", accommodationBookingReview='" + getAccommodationBookingReview() + "'" +
            ", attractionPrice=" + getAttractionPrice() +
            ", attractionReview='" + getAttractionReview() + "'" +
            ", travelCost=" + getTravelCost() +
            ", airlineCompany=" + getAirlineCompany() +
            "}";
    }
}
