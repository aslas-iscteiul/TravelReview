import { Component, Vue, Inject } from 'vue-property-decorator';

import AccommodationService from '@/entities/accommodation/accommodation.service';
import { IAccommodation } from '@/shared/model/accommodation.model';

import TaskRegisterBookingDataService from './task-register-booking-data.service';
import { TaskRegisterBookingDataContext } from './task-register-booking-data.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        accommodationBookingNumber: {},
        accommodationBookingPrice: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskRegisterBookingDataExecuteComponent extends Vue {
  private taskRegisterBookingDataService: TaskRegisterBookingDataService = new TaskRegisterBookingDataService();
  private taskContext: TaskRegisterBookingDataContext = {};

  @Inject('accommodationService') private accommodationService: () => AccommodationService;

  public accommodations: IAccommodation[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskRegisterBookingDataService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRegisterBookingDataService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.accommodationService()
      .retrieve()
      .then(res => {
        this.accommodations = res.data;
      });
  }
}
