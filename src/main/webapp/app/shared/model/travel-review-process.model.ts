import { ITravelReview } from '@/shared/model/travel-review.model';

export interface ITravelReviewProcess {
  id?: number;
  processInstance?: any | null;
  travelReview?: ITravelReview | null;
}

export class TravelReviewProcess implements ITravelReviewProcess {
  constructor(public id?: number, public processInstance?: any | null, public travelReview?: ITravelReview | null) {}
}
