import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskEvaluateBookingDataService from './task-evaluate-booking-data.service';
import { TaskEvaluateBookingDataContext } from './task-evaluate-booking-data.model';

@Component
export default class TaskEvaluateBookingDataDetailsComponent extends Vue {
  private taskEvaluateBookingDataService: TaskEvaluateBookingDataService = new TaskEvaluateBookingDataService();
  private taskContext: TaskEvaluateBookingDataContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskEvaluateBookingDataService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
