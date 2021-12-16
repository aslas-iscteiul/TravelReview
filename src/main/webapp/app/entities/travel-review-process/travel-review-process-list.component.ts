import { Component, Vue, Inject } from 'vue-property-decorator';
import { ITravelReviewProcess } from '@/shared/model/travel-review-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import TravelReviewProcessService from './travel-review-process.service';

@Component
export default class TravelReviewProcessListComponent extends Vue {
  @Inject('travelReviewProcessService') private travelReviewProcessService: () => TravelReviewProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'TravelReviewProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public travelReviewProcessList: ITravelReviewProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.travelReviewProcessService()
      .retrieve()
      .then(
        res => {
          this.travelReviewProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
