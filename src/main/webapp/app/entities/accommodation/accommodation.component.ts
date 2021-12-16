import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAccommodation } from '@/shared/model/accommodation.model';

import AccommodationService from './accommodation.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Accommodation extends Vue {
  @Inject('accommodationService') private accommodationService: () => AccommodationService;
  private removeId: number = null;

  public accommodations: IAccommodation[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAccommodations();
  }

  public clear(): void {
    this.retrieveAllAccommodations();
  }

  public retrieveAllAccommodations(): void {
    this.isFetching = true;

    this.accommodationService()
      .retrieve()
      .then(
        res => {
          this.accommodations = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IAccommodation): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAccommodation(): void {
    this.accommodationService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelReviewApp.accommodation.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAccommodations();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
