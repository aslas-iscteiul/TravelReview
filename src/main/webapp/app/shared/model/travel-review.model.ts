import { IAirlineCompany } from '@/shared/model/airline-company.model';

export interface ITravelReview {
  id?: number;
  title?: string | null;
  description?: string | null;
  travelOrigin?: string | null;
  travelDestination?: string | null;
  travelWithFlight?: boolean | null;
  travelStartDate?: Date | null;
  travelEndDate?: Date | null;
  flightTicketNumber?: string | null;
  flightClass?: string | null;
  flightDuration?: number | null;
  flightPrice?: number | null;
  accommodationBookingNumber?: string | null;
  accommodationBookingPrice?: number | null;
  attractionPrice?: number | null;
  travelCost?: number | null;
  airlineCompany?: IAirlineCompany | null;
}

export class TravelReview implements ITravelReview {
  constructor(
    public id?: number,
    public title?: string | null,
    public description?: string | null,
    public travelOrigin?: string | null,
    public travelDestination?: string | null,
    public travelWithFlight?: boolean | null,
    public travelStartDate?: Date | null,
    public travelEndDate?: Date | null,
    public flightTicketNumber?: string | null,
    public flightClass?: string | null,
    public flightDuration?: number | null,
    public flightPrice?: number | null,
    public accommodationBookingNumber?: string | null,
    public accommodationBookingPrice?: number | null,
    public attractionPrice?: number | null,
    public travelCost?: number | null,
    public airlineCompany?: IAirlineCompany | null
  ) {
    this.travelWithFlight = this.travelWithFlight ?? false;
  }
}
