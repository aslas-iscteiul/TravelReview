import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskCorrectTravelDatesService from './task-correct-travel-dates.service';
import { TaskCorrectTravelDatesContext } from './task-correct-travel-dates.model';

@Component
export default class TaskCorrectTravelDatesDetailsComponent extends Vue {
  private taskCorrectTravelDatesService: TaskCorrectTravelDatesService = new TaskCorrectTravelDatesService();
  private taskContext: TaskCorrectTravelDatesContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskCorrectTravelDatesService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
