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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.title')">title</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.description')">description</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.travelOrigin')">travelOrigin</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.travelDestination')"
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.accommodationBookingNumber')"
                >accommodationBookingNumber</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="accommodationBookingNumber"
                id="travel-review-accommodationBookingNumber"
                data-cy="accommodationBookingNumber"
                v-model="taskContext.travelReviewProcess.travelReview.accommodationBookingNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterBookingData.accommodationBookingPrice')"
                >accommodationBookingPrice</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="accommodationBookingPrice"
                id="travel-review-accommodationBookingPrice"
                data-cy="accommodationBookingPrice"
                v-model="taskContext.travelReviewProcess.travelReview.accommodationBookingPrice"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.accommodation')"
                for="task-register-booking-data-accommodation"
                >Accommodation</label
              >
              <input
                v-if="taskContext.travelReviewProcess.travelReview.accommodation"
                readonly
                type="text"
                class="form-control"
                name="accommodation"
                id="travel-review-accommodation"
                data-cy="accommodation"
                :value="taskContext.travelReviewProcess.travelReview.accommodation.name"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="accommodation"
                id="travel-review-accommodation"
                data-cy="accommodation"
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
          :to="`/process-definition/TravelReviewProcess/task/RegisterBookingData/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-register-booking-data-details.component.ts"></script>
