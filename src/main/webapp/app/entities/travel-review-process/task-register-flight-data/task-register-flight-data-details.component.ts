import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRegisterFlightDataService from './task-register-flight-data.service';
import { TaskRegisterFlightDataContext } from './task-register-flight-data.model';

@Component
export default class TaskRegisterFlightDataDetailsComponent extends Vue {
  private taskRegisterFlightDataService: TaskRegisterFlightDataService = new TaskRegisterFlightDataService();
  private taskContext: TaskRegisterFlightDataContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRegisterFlightDataService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
