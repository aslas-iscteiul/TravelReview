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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskRegisterTravel.title')" for="task-register-travel-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-register-travel-title"
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
                v-text="$t('travelReviewApp.taskRegisterTravel.description')"
                for="task-register-travel-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-register-travel-description"
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
                v-text="$t('travelReviewApp.taskRegisterTravel.travelOrigin')"
                for="task-register-travel-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-register-travel-travelOrigin"
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
                v-text="$t('travelReviewApp.taskRegisterTravel.travelDestination')"
                for="task-register-travel-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-register-travel-travelDestination"
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
                v-text="$t('travelReviewApp.taskRegisterTravel.travelWithFlight')"
                for="task-register-travel-travelWithFlight"
                >Travel With Flight</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="travelWithFlight"
                id="task-register-travel-travelWithFlight"
                data-cy="travelWithFlight"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.travelWithFlight.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.travelWithFlight.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.travelWithFlight.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterTravel.travelStartDate')"
                for="task-register-travel-travelStartDate"
                >Travel Start Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-register-travel-travelStartDate"
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
                  id="task-register-travel-travelStartDate"
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
                v-text="$t('travelReviewApp.taskRegisterTravel.travelEndDate')"
                for="task-register-travel-travelEndDate"
                >Travel End Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-register-travel-travelEndDate"
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
                  id="task-register-travel-travelEndDate"
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

<script lang="ts" src="./task-register-travel-execute.component.ts"></script>
