import { Component, Vue, Inject } from 'vue-property-decorator';

import { IScore } from '@/shared/model/score.model';
import ScoreService from './score.service';

@Component
export default class ScoreDetails extends Vue {
  @Inject('scoreService') private scoreService: () => ScoreService;
  public score: IScore = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.scoreId) {
        vm.retrieveScore(to.params.scoreId);
      }
    });
  }

  public retrieveScore(scoreId) {
    this.scoreService()
      .find(scoreId)
      .then(res => {
        this.score = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
