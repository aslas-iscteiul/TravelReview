<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('travelReviewApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.title')">title</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="title"
                id="travel-review-title"
                data-cy="title"
                v-model="taskContext.travelReviewProcess.travelReview.title"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.description')">description</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="description"
                id="travel-review-description"
                data-cy="description"
                v-model="taskContext.travelReviewProcess.travelReview.description"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.travelOrigin')">travelOrigin</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="travelOrigin"
                id="travel-review-travelOrigin"
                data-cy="travelOrigin"
                v-model="taskContext.travelReviewProcess.travelReview.travelOrigin"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.travelDestination')"
                >travelDestination</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="travelDestination"
                id="travel-review-travelDestination"
                data-cy="travelDestination"
                v-model="taskContext.travelReviewProcess.travelReview.travelDestination"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.flightTicketNumber')"
                >flightTicketNumber</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="flightTicketNumber"
                id="travel-review-flightTicketNumber"
                data-cy="flightTicketNumber"
                v-model="taskContext.travelReviewProcess.travelReview.flightTicketNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.flightClass')">flightClass</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="flightClass"
                id="travel-review-flightClass"
                data-cy="flightClass"
                v-model="taskContext.travelReviewProcess.travelReview.flightClass"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.flightDuration')">flightDuration</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="flightDuration"
                id="travel-review-flightDuration"
                data-cy="flightDuration"
                v-model="taskContext.travelReviewProcess.travelReview.flightDuration"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterFlightData.flightPrice')">flightPrice</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="flightPrice"
                id="travel-review-flightPrice"
                data-cy="flightPrice"
                v-model="taskContext.travelReviewProcess.travelReview.flightPrice"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.airlineCompany')"
                for="task-register-flight-data-airlineCompany"
                >Airline Company</label
              >
              <input
                v-if="taskContext.travelReviewProcess.travelReview.airlineCompany"
                readonly
                type="text"
                class="form-control"
                name="airlineCompany"
                id="travel-review-airlineCompany"
                data-cy="airlineCompany"
                :value="taskContext.travelReviewProcess.travelReview.airlineCompany.name"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="airlineCompany"
                id="travel-review-airlineCompany"
                data-cy="airlineCompany"
                value=""
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/TravelReviewProcess/task/RegisterFlightData/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-register-flight-data-details.component.ts"></script>
