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
                v-text="$t('travelReviewApp.taskEvaluateAttraction.title')"
                for="task-evaluate-attraction-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-evaluate-attraction-title"
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
                v-text="$t('travelReviewApp.taskEvaluateAttraction.description')"
                for="task-evaluate-attraction-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-evaluate-attraction-description"
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
                v-text="$t('travelReviewApp.taskEvaluateAttraction.travelOrigin')"
                for="task-evaluate-attraction-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-evaluate-attraction-travelOrigin"
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
                v-text="$t('travelReviewApp.taskEvaluateAttraction.travelDestination')"
                for="task-evaluate-attraction-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-evaluate-attraction-travelDestination"
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
                v-text="$t('travelReviewApp.taskEvaluateAttraction.attractionReview')"
                for="task-evaluate-attraction-attractionReview"
                >Attraction Review</label
              >
              <input
                type="text"
                class="form-control"
                name="attractionReview"
                id="task-evaluate-attraction-attractionReview"
                data-cy="attractionReview"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.attractionReview.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.attractionReview.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.attractionReview.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskEvaluateAttraction.attractionScore')"
                for="task-evaluate-attraction-attractionScore"
                >Attraction Score</label
              >
              <select
                class="form-control"
                id="task-evaluate-attraction-attractionScore"
                data-cy="attractionScore"
                name="attractionScore"
                v-model="taskContext.travelReviewProcess.travelReview.attractionScore"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.attractionScore &&
                    scoreOption.id === taskContext.travelReviewProcess.travelReview.attractionScore.id
                      ? taskContext.travelReviewProcess.travelReview.attractionScore
                      : scoreOption
                  "
                  v-for="scoreOption in scores"
                  :key="scoreOption.id"
                >
                  {{ scoreOption.description }}
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

<script lang="ts" src="./task-evaluate-attraction-execute.component.ts"></script>
