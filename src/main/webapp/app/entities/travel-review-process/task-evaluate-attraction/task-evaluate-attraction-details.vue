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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateAttraction.title')">title</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateAttraction.description')">description</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateAttraction.travelOrigin')">travelOrigin</label>
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateAttraction.travelDestination')"
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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateAttraction.attractionReview')"
                >attractionReview</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="attractionReview"
                id="travel-review-attractionReview"
                data-cy="attractionReview"
                v-model="taskContext.travelReviewProcess.travelReview.attractionReview"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskEvaluateAttraction.attractionScore')"
                for="task-evaluate-attraction-attractionScore"
                >Attraction Score</label
              >
              <input
                v-if="taskContext.travelReviewProcess.travelReview.attractionScore"
                readonly
                type="text"
                class="form-control"
                name="attractionScore"
                id="travel-review-attractionScore"
                data-cy="attractionScore"
                :value="taskContext.travelReviewProcess.travelReview.attractionScore.description"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="attractionScore"
                id="travel-review-attractionScore"
                data-cy="attractionScore"
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
          :to="`/process-definition/TravelReviewProcess/task/EvaluateAttraction/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-evaluate-attraction-details.component.ts"></script>
