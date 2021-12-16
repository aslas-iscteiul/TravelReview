import { Component, Vue, Inject } from 'vue-property-decorator';

import ScoreService from '@/entities/score/score.service';
import { IScore } from '@/shared/model/score.model';

import TaskEvaluateAttractionService from './task-evaluate-attraction.service';
import { TaskEvaluateAttractionContext } from './task-evaluate-attraction.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        attractionReview: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskEvaluateAttractionExecuteComponent extends Vue {
  private taskEvaluateAttractionService: TaskEvaluateAttractionService = new TaskEvaluateAttractionService();
  private taskContext: TaskEvaluateAttractionContext = {};

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
    this.taskEvaluateAttractionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskEvaluateAttractionService.complete(this.taskContext).then(res => {
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
