import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskCorrectTravelDatesService from './task-correct-travel-dates.service';
import { TaskCorrectTravelDatesContext } from './task-correct-travel-dates.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        travelStartDate: {},
        travelEndDate: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskCorrectTravelDatesExecuteComponent extends Vue {
  private taskCorrectTravelDatesService: TaskCorrectTravelDatesService = new TaskCorrectTravelDatesService();
  private taskContext: TaskCorrectTravelDatesContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskCorrectTravelDatesService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskCorrectTravelDatesService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
