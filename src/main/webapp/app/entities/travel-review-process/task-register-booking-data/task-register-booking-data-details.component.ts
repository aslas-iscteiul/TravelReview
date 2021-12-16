import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRegisterBookingDataService from './task-register-booking-data.service';
import { TaskRegisterBookingDataContext } from './task-register-booking-data.model';

@Component
export default class TaskRegisterBookingDataDetailsComponent extends Vue {
  private taskRegisterBookingDataService: TaskRegisterBookingDataService = new TaskRegisterBookingDataService();
  private taskContext: TaskRegisterBookingDataContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRegisterBookingDataService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
