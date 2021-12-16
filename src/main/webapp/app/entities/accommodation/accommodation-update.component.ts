import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAccommodation, Accommodation } from '@/shared/model/accommodation.model';
import AccommodationService from './accommodation.service';

const validations: any = {
  accommodation: {
    name: {},
    type: {},
    sustainableAccommodation: {},
    website: {},
    email: {},
    phone: {},
    address: {},
    classification: {},
  },
};

@Component({
  validations,
})
export default class AccommodationUpdate extends Vue {
  @Inject('accommodationService') private accommodationService: () => AccommodationService;
  public accommodation: IAccommodation = new Accommodation();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.accommodationId) {
        vm.retrieveAccommodation(to.params.accommodationId);
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
    if (this.accommodation.id) {
      this.accommodationService()
        .update(this.accommodation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.accommodation.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.accommodationService()
        .create(this.accommodation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.accommodation.created', { param: param.id });
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

  public retrieveAccommodation(accommodationId): void {
    this.accommodationService()
      .find(accommodationId)
      .then(res => {
        this.accommodation = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
