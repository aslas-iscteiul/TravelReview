import { Component, Vue, Inject } from 'vue-property-decorator';

import ScoreService from '@/entities/score/score.service';
import { IScore } from '@/shared/model/score.model';

import TaskEvaluateFlightService from './task-evaluate-flight.service';
import { TaskEvaluateFlightContext } from './task-evaluate-flight.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        flightTicketNumber: {},
        flightReview: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskEvaluateFlightExecuteComponent extends Vue {
  private taskEvaluateFlightService: TaskEvaluateFlightService = new TaskEvaluateFlightService();
  private taskContext: TaskEvaluateFlightContext = {};

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
    this.taskEvaluateFlightService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskEvaluateFlightService.complete(this.taskContext).then(res => {
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
