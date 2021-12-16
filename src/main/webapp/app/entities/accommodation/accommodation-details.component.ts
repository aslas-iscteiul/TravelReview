import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAccommodation } from '@/shared/model/accommodation.model';
import AccommodationService from './accommodation.service';

@Component
export default class AccommodationDetails extends Vue {
  @Inject('accommodationService') private accommodationService: () => AccommodationService;
  public accommodation: IAccommodation = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.accommodationId) {
        vm.retrieveAccommodation(to.params.accommodationId);
      }
    });
  }

  public retrieveAccommodation(accommodationId) {
    this.accommodationService()
      .find(accommodationId)
      .then(res => {
        this.accommodation = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
