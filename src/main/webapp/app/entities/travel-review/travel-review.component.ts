import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITravelReview } from '@/shared/model/travel-review.model';

import TravelReviewService from './travel-review.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TravelReview extends Vue {
  @Inject('travelReviewService') private travelReviewService: () => TravelReviewService;
  private removeId: number = null;

  public travelReviews: ITravelReview[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTravelReviews();
  }

  public clear(): void {
    this.retrieveAllTravelReviews();
  }

  public retrieveAllTravelReviews(): void {
    this.isFetching = true;

    this.travelReviewService()
      .retrieve()
      .then(
        res => {
          this.travelReviews = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
