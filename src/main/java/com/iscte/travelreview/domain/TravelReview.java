package com.iscte.travelreview.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TravelReview.
 */
@Entity
@Table(name = "travel_review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TravelReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "travel_origin")
    private String travelOrigin;

    @Column(name = "travel_destination")
    private String travelDestination;

    @Column(name = "travel_with_flight")
    private Boolean travelWithFlight;

    @Column(name = "travel_start_date")
    private LocalDate travelStartDate;

    @Column(name = "travel_end_date")
    private LocalDate travelEndDate;

    @Column(name = "flight_ticket_number")
    private String flightTicketNumber;

    @Column(name = "flight_class")
    private String flightClass;

    @Column(name = "flight_duration")
    private Double flightDuration;

    @Column(name = "flight_price")
    private Double flightPrice;

    @Column(name = "flight_review")
    private String flightReview;

    @Column(name = "accommodation_booking_number")
    private String accommodationBookingNumber;

    @Column(name = "accommodation_booking_price")
    private Double accommodationBookingPrice;

    @Column(name = "accommodation_booking_review")
    private String accommodationBookingReview;

    @Column(name = "attraction_price")
    private Double attractionPrice;

    @Column(name = "attraction_review")
    private String attractionReview;

    @Column(name = "travel_cost")
    private Double travelCost;

    @ManyToOne
    private AirlineCompany airlineCompany;

    @ManyToOne
    private Accommodation accommodation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelReview id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public TravelReview title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public TravelReview description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTravelOrigin() {
        return this.travelOrigin;
    }

    public TravelReview travelOrigin(String travelOrigin) {
        this.travelOrigin = travelOrigin;
        return this;
    }

    public void setTravelOrigin(String travelOrigin) {
        this.travelOrigin = travelOrigin;
    }

    public String getTravelDestination() {
        return this.travelDestination;
    }

    public TravelReview travelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
        return this;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public Boolean getTravelWithFlight() {
        return this.travelWithFlight;
    }

    public TravelReview travelWithFlight(Boolean travelWithFlight) {
        this.travelWithFlight = travelWithFlight;
        return this;
    }

    public void setTravelWithFlight(Boolean travelWithFlight) {
        this.travelWithFlight = travelWithFlight;
    }

    public LocalDate getTravelStartDate() {
        return this.travelStartDate;
    }

    public TravelReview travelStartDate(LocalDate travelStartDate) {
        this.travelStartDate = travelStartDate;
        return this;
    }

    public void setTravelStartDate(LocalDate travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public LocalDate getTravelEndDate() {
        return this.travelEndDate;
    }

    public TravelReview travelEndDate(LocalDate travelEndDate) {
        this.travelEndDate = travelEndDate;
        return this;
    }

    public void setTravelEndDate(LocalDate travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public String getFlightTicketNumber() {
        return this.flightTicketNumber;
    }

    public TravelReview flightTicketNumber(String flightTicketNumber) {
        this.flightTicketNumber = flightTicketNumber;
        return this;
    }

    public void setFlightTicketNumber(String flightTicketNumber) {
        this.flightTicketNumber = flightTicketNumber;
    }

    public String getFlightClass() {
        return this.flightClass;
    }

    public TravelReview flightClass(String flightClass) {
        this.flightClass = flightClass;
        return this;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public Double getFlightDuration() {
        return this.flightDuration;
    }

    public TravelReview flightDuration(Double flightDuration) {
        this.flightDuration = flightDuration;
        return this;
    }

    public void setFlightDuration(Double flightDuration) {
        this.flightDuration = flightDuration;
    }

    public Double getFlightPrice() {
        return this.flightPrice;
    }

    public TravelReview flightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
        return this;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getFlightReview() {
        return this.flightReview;
    }

    public TravelReview flightReview(String flightReview) {
        this.flightReview = flightReview;
        return this;
    }

    public void setFlightReview(String flightReview) {
        this.flightReview = flightReview;
    }

    public String getAccommodationBookingNumber() {
        return this.accommodationBookingNumber;
    }

    public TravelReview accommodationBookingNumber(String accommodationBookingNumber) {
        this.accommodationBookingNumber = accommodationBookingNumber;
        return this;
    }

    public void setAccommodationBookingNumber(String accommodationBookingNumber) {
        this.accommodationBookingNumber = accommodationBookingNumber;
    }

    public Double getAccommodationBookingPrice() {
        return this.accommodationBookingPrice;
    }

    public TravelReview accommodationBookingPrice(Double accommodationBookingPrice) {
        this.accommodationBookingPrice = accommodationBookingPrice;
        return this;
    }

    public void setAccommodationBookingPrice(Double accommodationBookingPrice) {
        this.accommodationBookingPrice = accommodationBookingPrice;
    }

    public String getAccommodationBookingReview() {
        return this.accommodationBookingReview;
    }

    public TravelReview accommodationBookingReview(String accommodationBookingReview) {
        this.accommodationBookingReview = accommodationBookingReview;
        return this;
    }

    public void setAccommodationBookingReview(String accommodationBookingReview) {
        this.accommodationBookingReview = accommodationBookingReview;
    }

    public Double getAttractionPrice() {
        return this.attractionPrice;
    }

    public TravelReview attractionPrice(Double attractionPrice) {
        this.attractionPrice = attractionPrice;
        return this;
    }

    public void setAttractionPrice(Double attractionPrice) {
        this.attractionPrice = attractionPrice;
    }

    public String getAttractionReview() {
        return this.attractionReview;
    }

    public TravelReview attractionReview(String attractionReview) {
        this.attractionReview = attractionReview;
        return this;
    }

    public void setAttractionReview(String attractionReview) {
        this.attractionReview = attractionReview;
    }

    public Double getTravelCost() {
        return this.travelCost;
    }

    public TravelReview travelCost(Double travelCost) {
        this.travelCost = travelCost;
        return this;
    }

    public void setTravelCost(Double travelCost) {
        this.travelCost = travelCost;
    }

    public AirlineCompany getAirlineCompany() {
        return this.airlineCompany;
    }

    public TravelReview airlineCompany(AirlineCompany airlineCompany) {
        this.setAirlineCompany(airlineCompany);
        return this;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Accommodation getAccommodation() {
        return this.accommodation;
    }

    public TravelReview accommodation(Accommodation accommodation) {
        this.setAccommodation(accommodation);
        return this;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelReview)) {
            return false;
        }
        return id != null && id.equals(((TravelReview) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelReview{" +
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
            "}";
    }
}
