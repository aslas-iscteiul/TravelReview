import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAttraction, Attraction } from '@/shared/model/attraction.model';
import AttractionService from './attraction.service';

const validations: any = {
  attraction: {
    name: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class AttractionUpdate extends Vue {
  @Inject('attractionService') private attractionService: () => AttractionService;
  public attraction: IAttraction = new Attraction();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.attractionId) {
        vm.retrieveAttraction(to.params.attractionId);
      }
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
    if (this.attraction.id) {
      this.attractionService()
        .update(this.attraction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.attraction.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.attractionService()
        .create(this.attraction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.attraction.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveAttraction(attractionId): void {
    this.attractionService()
      .find(attractionId)
      .then(res => {
        this.attraction = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
