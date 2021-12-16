import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSelectAttractionService from './task-select-attraction.service';
import { TaskSelectAttractionContext } from './task-select-attraction.model';

@Component
export default class TaskSelectAttractionDetailsComponent extends Vue {
  private taskSelectAttractionService: TaskSelectAttractionService = new TaskSelectAttractionService();
  private taskContext: TaskSelectAttractionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskSelectAttractionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
