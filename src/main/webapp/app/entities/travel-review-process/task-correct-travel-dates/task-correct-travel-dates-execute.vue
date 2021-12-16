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
                v-text="$t('travelReviewApp.taskCorrectTravelDates.title')"
                for="task-correct-travel-dates-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-correct-travel-dates-title"
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
                v-text="$t('travelReviewApp.taskCorrectTravelDates.description')"
                for="task-correct-travel-dates-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-correct-travel-dates-description"
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
                v-text="$t('travelReviewApp.taskCorrectTravelDates.travelOrigin')"
                for="task-correct-travel-dates-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-correct-travel-dates-travelOrigin"
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
                v-text="$t('travelReviewApp.taskCorrectTravelDates.travelDestination')"
                for="task-correct-travel-dates-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-correct-travel-dates-travelDestination"
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
                v-text="$t('travelReviewApp.taskCorrectTravelDates.travelStartDate')"
                for="task-correct-travel-dates-travelStartDate"
                >Travel Start Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-correct-travel-dates-travelStartDate"
                    v-model="$v.taskContext.travelReviewProcess.travelReview.travelStartDate.$model"
                    name="travelStartDate"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="task-correct-travel-dates-travelStartDate"
                  data-cy="travelStartDate"
                  type="text"
                  class="form-control"
                  name="travelStartDate"
                  :class="{
                    valid: !$v.taskContext.travelReviewProcess.travelReview.travelStartDate.$invalid,
                    invalid: $v.taskContext.travelReviewProcess.travelReview.travelStartDate.$invalid,
                  }"
                  v-model="$v.taskContext.travelReviewProcess.travelReview.travelStartDate.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskCorrectTravelDates.travelEndDate')"
                for="task-correct-travel-dates-travelEndDate"
                >Travel End Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-correct-travel-dates-travelEndDate"
                    v-model="$v.taskContext.travelReviewProcess.travelReview.travelEndDate.$model"
                    name="travelEndDate"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="task-correct-travel-dates-travelEndDate"
                  data-cy="travelEndDate"
                  type="text"
                  class="form-control"
                  name="travelEndDate"
                  :class="{
                    valid: !$v.taskContext.travelReviewProcess.travelReview.travelEndDate.$invalid,
                    invalid: $v.taskContext.travelReviewProcess.travelReview.travelEndDate.$invalid,
                  }"
                  v-model="$v.taskContext.travelReviewProcess.travelReview.travelEndDate.$model"
                />
              </b-input-group>
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

<script lang="ts" src="./task-correct-travel-dates-execute.component.ts"></script>
