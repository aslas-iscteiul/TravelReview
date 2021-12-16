import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IScore } from '@/shared/model/score.model';

import ScoreService from './score.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Score extends Vue {
  @Inject('scoreService') private scoreService: () => ScoreService;
  private removeId: number = null;

  public scores: IScore[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllScores();
  }

  public clear(): void {
    this.retrieveAllScores();
  }

  public retrieveAllScores(): void {
    this.isFetching = true;

    this.scoreService()
      .retrieve()
      .then(
        res => {
          this.scores = res.data;
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

  public prepareRemove(instance: IScore): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeScore(): void {
    this.scoreService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelReviewApp.score.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllScores();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
