import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAttraction } from '@/shared/model/attraction.model';
import AttractionService from './attraction.service';

@Component
export default class AttractionDetails extends Vue {
  @Inject('attractionService') private attractionService: () => AttractionService;
  public attraction: IAttraction = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.attractionId) {
        vm.retrieveAttraction(to.params.attractionId);
      }
    });
  }

  public retrieveAttraction(attractionId) {
    this.attractionService()
      .find(attractionId)
      .then(res => {
        this.attraction = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
