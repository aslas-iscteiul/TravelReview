import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskRegisterTravelService from './task-register-travel.service';
import { TaskRegisterTravelContext } from './task-register-travel.model';

@Component
export default class TaskRegisterTravelDetailsComponent extends Vue {
  private taskRegisterTravelService: TaskRegisterTravelService = new TaskRegisterTravelService();
  private taskContext: TaskRegisterTravelContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskRegisterTravelService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
