import { Component, Vue, Inject } from 'vue-property-decorator';

import AirlineCompanyService from '@/entities/airline-company/airline-company.service';
import { IAirlineCompany } from '@/shared/model/airline-company.model';

import TaskRegisterFlightDataService from './task-register-flight-data.service';
import { TaskRegisterFlightDataContext } from './task-register-flight-data.model';

const validations: any = {
  taskContext: {
    travelReviewProcess: {
      travelReview: {
        title: {},
        description: {},
        travelOrigin: {},
        travelDestination: {},
        flightTicketNumber: {},
        flightClass: {},
        flightDuration: {},
        flightPrice: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskRegisterFlightDataExecuteComponent extends Vue {
  private taskRegisterFlightDataService: TaskRegisterFlightDataService = new TaskRegisterFlightDataService();
  private taskContext: TaskRegisterFlightDataContext = {};

  @Inject('airlineCompanyService') private airlineCompanyService: () => AirlineCompanyService;

  public airlineCompanies: IAirlineCompany[] = [];
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
    this.taskRegisterFlightDataService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRegisterFlightDataService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.airlineCompanyService()
      .retrieve()
      .then(res => {
        this.airlineCompanies = res.data;
      });
  }
}
