import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskEvaluateAttractionService from './task-evaluate-attraction.service';
import { TaskEvaluateAttractionContext } from './task-evaluate-attraction.model';

@Component
export default class TaskEvaluateAttractionDetailsComponent extends Vue {
  private taskEvaluateAttractionService: TaskEvaluateAttractionService = new TaskEvaluateAttractionService();
  private taskContext: TaskEvaluateAttractionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskEvaluateAttractionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
