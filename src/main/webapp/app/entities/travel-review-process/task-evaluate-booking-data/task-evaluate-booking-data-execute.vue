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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.title')"
                for="task-evaluate-booking-data-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-evaluate-booking-data-title"
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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.description')"
                for="task-evaluate-booking-data-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-evaluate-booking-data-description"
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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.travelOrigin')"
                for="task-evaluate-booking-data-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-evaluate-booking-data-travelOrigin"
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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.travelDestination')"
                for="task-evaluate-booking-data-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-evaluate-booking-data-travelDestination"
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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.accommodationBookingNumber')"
                for="task-evaluate-booking-data-accommodationBookingNumber"
                >Accommodation Booking Number</label
              >
              <input
                type="text"
                class="form-control"
                name="accommodationBookingNumber"
                id="task-evaluate-booking-data-accommodationBookingNumber"
                readonly
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
                v-text="$t('travelReviewApp.taskEvaluateBookingData.accommodationBookingReview')"
                for="task-evaluate-booking-data-accommodationBookingReview"
                >Accommodation Booking Review</label
              >
              <input
                type="text"
                class="form-control"
                name="accommodationBookingReview"
                id="task-evaluate-booking-data-accommodationBookingReview"
                data-cy="accommodationBookingReview"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.accommodationBookingReview.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.accommodationBookingReview.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.accommodationBookingReview.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskEvaluateBookingData.bookingScore')"
                for="task-evaluate-booking-data-bookingScore"
                >Booking Score</label
              >
              <select
                class="form-control"
                id="task-evaluate-booking-data-bookingScore"
                data-cy="bookingScore"
                name="bookingScore"
                v-model="taskContext.travelReviewProcess.travelReview.bookingScore"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.bookingScore &&
                    scoreOption.id === taskContext.travelReviewProcess.travelReview.bookingScore.id
                      ? taskContext.travelReviewProcess.travelReview.bookingScore
                      : scoreOption
                  "
                  v-for="scoreOption in scores"
                  :key="scoreOption.id"
                >
                  {{ scoreOption.number }}
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

<script lang="ts" src="./task-evaluate-booking-data-execute.component.ts"></script>
