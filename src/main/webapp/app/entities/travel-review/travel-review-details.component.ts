import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelReview } from '@/shared/model/travel-review.model';
import TravelReviewService from './travel-review.service';

@Component
export default class TravelReviewDetails extends Vue {
  @Inject('travelReviewService') private travelReviewService: () => TravelReviewService;
  public travelReview: ITravelReview = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.travelReviewId) {
        vm.retrieveTravelReview(to.params.travelReviewId);
      }
    });
  }

  public retrieveTravelReview(travelReviewId) {
    this.travelReviewService()
      .find(travelReviewId)
      .then(res => {
        this.travelReview = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
