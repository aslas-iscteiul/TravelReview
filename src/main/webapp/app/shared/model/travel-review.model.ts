import { IAirlineCompany } from '@/shared/model/airline-company.model';
import { IAccommodation } from '@/shared/model/accommodation.model';
import { IScore } from '@/shared/model/score.model';
import { IAttraction } from '@/shared/model/attraction.model';

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
  flightReview?: string | null;
  accommodationBookingNumber?: string | null;
  accommodationBookingPrice?: number | null;
  accommodationBookingReview?: string | null;
  attractionPrice?: number | null;
  attractionReview?: string | null;
  travelCost?: number | null;
  airlineCompany?: IAirlineCompany | null;
  accommodation?: IAccommodation | null;
  flightScore?: IScore | null;
  bookingScore?: IScore | null;
  attraction?: IAttraction | null;
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
    public flightReview?: string | null,
    public accommodationBookingNumber?: string | null,
    public accommodationBookingPrice?: number | null,
    public accommodationBookingReview?: string | null,
    public attractionPrice?: number | null,
    public attractionReview?: string | null,
    public travelCost?: number | null,
    public airlineCompany?: IAirlineCompany | null,
    public accommodation?: IAccommodation | null,
    public flightScore?: IScore | null,
    public bookingScore?: IScore | null,
    public attraction?: IAttraction | null
  ) {
    this.travelWithFlight = this.travelWithFlight ?? false;
  }
}
