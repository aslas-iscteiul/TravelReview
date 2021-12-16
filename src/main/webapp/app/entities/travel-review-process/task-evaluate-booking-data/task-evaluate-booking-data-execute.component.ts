import { Component, Vue, Inject } from 'vue-property-decorator';

import ScoreService from '@/entities/score/score.service';
import { IScore } from '@/shared/model/score.model';

import TaskEvaluateBookingDataService from './task-evaluate-booking-data.service';
import { TaskEvaluateBookingDataContext } from './task-evaluate-booking-data.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        accommodationBookingNumber: {},
        accommodationBookingReview: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskEvaluateBookingDataExecuteComponent extends Vue {
  private taskEvaluateBookingDataService: TaskEvaluateBookingDataService = new TaskEvaluateBookingDataService();
  private taskContext: TaskEvaluateBookingDataContext = {};

  @Inject('scoreService') private scoreService: () => ScoreService;

  public scores: IScore[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskEvaluateBookingDataService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskEvaluateBookingDataService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.scoreService()
      .retrieve()
      .then(res => {
        this.scores = res.data;
      });
  }
}
