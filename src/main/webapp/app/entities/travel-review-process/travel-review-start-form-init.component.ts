import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelReviewProcess, TravelReviewProcess } from '@/shared/model/travel-review-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { TravelReview } from '@/shared/model/travel-review.model';
import TravelReviewProcessService from './travel-review-process.service';

const validations: any = {
  travelReviewProcess: {
    travelReview: {
      title: {},
      description: {},
    },
  },
};

@Component({
  validations,
})
export default class TravelReviewStartFormInitComponent extends Vue {
  @Inject('travelReviewProcessService') private travelReviewProcessService: () => TravelReviewProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'TravelReviewProcess';
  public travelReviewProcess: ITravelReviewProcess = new TravelReviewProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initTravelReviewStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.travelReviewProcessService()
      .create(this.travelReviewProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('travelReviewApp.travelReviewStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initTravelReviewStartForm(): void {
    this.travelReviewProcess.travelReview = new TravelReview();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.travelReviewProcess.processInstance = new ProcessInstance();
      this.travelReviewProcess.processInstance.processDefinition = res;
    });
  }
}
