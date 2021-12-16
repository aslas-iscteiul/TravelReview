import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAttraction } from '@/shared/model/attraction.model';

import AttractionService from './attraction.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Attraction extends Vue {
  @Inject('attractionService') private attractionService: () => AttractionService;
  private removeId: number = null;

  public attractions: IAttraction[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAttractions();
  }

  public clear(): void {
    this.retrieveAllAttractions();
  }

  public retrieveAllAttractions(): void {
    this.isFetching = true;

    this.attractionService()
      .retrieve()
      .then(
        res => {
          this.attractions = res.data;
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

  public prepareRemove(instance: IAttraction): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAttraction(): void {
    this.attractionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelReviewApp.attraction.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAttractions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
