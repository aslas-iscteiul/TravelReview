<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('travelReviewApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.title')"
                for="task-register-booking-data-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-register-booking-data-title"
                readonly
                data-cy="title"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.title.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.title.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.title.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.description')"
                for="task-register-booking-data-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-register-booking-data-description"
                readonly
                data-cy="description"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.description.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.description.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.description.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.travelOrigin')"
                for="task-register-booking-data-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-register-booking-data-travelOrigin"
                readonly
                data-cy="travelOrigin"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.travelOrigin.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.travelOrigin.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.travelOrigin.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.travelDestination')"
                for="task-register-booking-data-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-register-booking-data-travelDestination"
                readonly
                data-cy="travelDestination"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.travelDestination.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.travelDestination.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.travelDestination.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.accommodationBookingNumber')"
                for="task-register-booking-data-accommodationBookingNumber"
                >Accommodation Booking Number</label
              >
              <input
                type="text"
                class="form-control"
                name="accommodationBookingNumber"
                id="task-register-booking-data-accommodationBookingNumber"
                data-cy="accommodationBookingNumber"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.accommodationBookingNumber.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.accommodationBookingNumber.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.accommodationBookingNumber.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.accommodationBookingPrice')"
                for="task-register-booking-data-accommodationBookingPrice"
                >Accommodation Booking Price</label
              >
              <input
                type="number"
                class="form-control"
                name="accommodationBookingPrice"
                id="task-register-booking-data-accommodationBookingPrice"
                data-cy="accommodationBookingPrice"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.accommodationBookingPrice.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.accommodationBookingPrice.$invalid,
                }"
                v-model.number="$v.taskContext.travelReviewProcess.travelReview.accommodationBookingPrice.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterBookingData.accommodation')"
                for="task-register-booking-data-accommodation"
                >Accommodation</label
              >
              <select
                class="form-control"
                id="task-register-booking-data-accommodation"
                data-cy="accommodation"
                name="accommodation"
                v-model="taskContext.travelReviewProcess.travelReview.accommodation"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.accommodation &&
                    accommodationOption.id === taskContext.travelReviewProcess.travelReview.accommodation.id
                      ? taskContext.travelReviewProcess.travelReview.accommodation
                      : accommodationOption
                  "
                  v-for="accommodationOption in accommodations"
                  :key="accommodationOption.id"
                >
                  {{ accommodationOption.name }}
                </option>
              </select>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-register-booking-data-execute.component.ts"></script>
