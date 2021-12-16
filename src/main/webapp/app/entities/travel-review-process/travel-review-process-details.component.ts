import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelReviewProcess } from '@/shared/model/travel-review-process.model';
import TravelReviewProcessService from './travel-review-process.service';

@Component
export default class TravelReviewProcessDetailsComponent extends Vue {
  @Inject('travelReviewProcessService') private travelReviewProcessService: () => TravelReviewProcessService;
  public travelReviewProcess: ITravelReviewProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveTravelReviewProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveTravelReviewProcess(travelReviewProcessId) {
    this.isFetching = true;
    this.travelReviewProcessService()
      .find(travelReviewProcessId)
      .then(
        res => {
          this.travelReviewProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
