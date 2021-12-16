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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.title')">title</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.description')">description</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.travelOrigin')">travelOrigin</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.travelDestination')"
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.travelStartDate')"
                >travelStartDate</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="travelStartDate"
                id="travel-review-travelStartDate"
                data-cy="travelStartDate"
                v-model="taskContext.travelReviewProcess.travelReview.travelStartDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelReviewApp.taskCorrectTravelDates.travelEndDate')">travelEndDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="travelEndDate"
                id="travel-review-travelEndDate"
                data-cy="travelEndDate"
                v-model="taskContext.travelReviewProcess.travelReview.travelEndDate"
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
          :to="`/process-definition/TravelReviewProcess/task/CorrectTravelDates/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-correct-travel-dates-details.component.ts"></script>
