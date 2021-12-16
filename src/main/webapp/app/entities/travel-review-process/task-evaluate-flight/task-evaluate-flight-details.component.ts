import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskEvaluateFlightService from './task-evaluate-flight.service';
import { TaskEvaluateFlightContext } from './task-evaluate-flight.model';

@Component
export default class TaskEvaluateFlightDetailsComponent extends Vue {
  private taskEvaluateFlightService: TaskEvaluateFlightService = new TaskEvaluateFlightService();
  private taskContext: TaskEvaluateFlightContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskEvaluateFlightService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
