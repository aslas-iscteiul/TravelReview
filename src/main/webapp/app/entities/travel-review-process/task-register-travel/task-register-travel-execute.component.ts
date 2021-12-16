import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRegisterTravelService from './task-register-travel.service';
import { TaskRegisterTravelContext } from './task-register-travel.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        travelWithFlight: {},
        travelStartDate: {},
        travelEndDate: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskRegisterTravelExecuteComponent extends Vue {
  private taskRegisterTravelService: TaskRegisterTravelService = new TaskRegisterTravelService();
  private taskContext: TaskRegisterTravelContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskRegisterTravelService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRegisterTravelService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
