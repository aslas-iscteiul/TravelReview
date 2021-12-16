import { Component, Vue, Inject } from 'vue-property-decorator';

import AttractionService from '@/entities/attraction/attraction.service';
import { IAttraction } from '@/shared/model/attraction.model';

import TaskSelectAttractionService from './task-select-attraction.service';
import { TaskSelectAttractionContext } from './task-select-attraction.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        attractionPrice: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskSelectAttractionExecuteComponent extends Vue {
  private taskSelectAttractionService: TaskSelectAttractionService = new TaskSelectAttractionService();
  private taskContext: TaskSelectAttractionContext = {};

  @Inject('attractionService') private attractionService: () => AttractionService;

  public attractions: IAttraction[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskSelectAttractionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskSelectAttractionService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.attractionService()
      .retrieve()
      .then(res => {
        this.attractions = res.data;
      });
  }
}
