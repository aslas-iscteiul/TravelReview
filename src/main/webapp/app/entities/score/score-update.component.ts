import { Component, Vue, Inject } from 'vue-property-decorator';

import { IScore, Score } from '@/shared/model/score.model';
import ScoreService from './score.service';

const validations: any = {
  score: {
    number: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class ScoreUpdate extends Vue {
  @Inject('scoreService') private scoreService: () => ScoreService;
  public score: IScore = new Score();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.scoreId) {
        vm.retrieveScore(to.params.scoreId);
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
    if (this.score.id) {
      this.scoreService()
        .update(this.score)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.score.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.scoreService()
        .create(this.score)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelReviewApp.score.created', { param: param.id });
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

  public retrieveScore(scoreId): void {
    this.scoreService()
      .find(scoreId)
      .then(res => {
        this.score = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
