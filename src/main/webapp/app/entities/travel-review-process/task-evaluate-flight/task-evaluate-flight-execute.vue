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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskEvaluateFlight.title')" for="task-evaluate-flight-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-evaluate-flight-title"
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
                v-text="$t('travelReviewApp.taskEvaluateFlight.description')"
                for="task-evaluate-flight-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-evaluate-flight-description"
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
                v-text="$t('travelReviewApp.taskEvaluateFlight.travelOrigin')"
                for="task-evaluate-flight-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-evaluate-flight-travelOrigin"
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
                v-text="$t('travelReviewApp.taskEvaluateFlight.travelDestination')"
                for="task-evaluate-flight-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-evaluate-flight-travelDestination"
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
                v-text="$t('travelReviewApp.taskEvaluateFlight.flightTicketNumber')"
                for="task-evaluate-flight-flightTicketNumber"
                >Flight Ticket Number</label
              >
              <input
                type="text"
                class="form-control"
                name="flightTicketNumber"
                id="task-evaluate-flight-flightTicketNumber"
                readonly
                data-cy="flightTicketNumber"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskEvaluateFlight.flightReview')"
                for="task-evaluate-flight-flightReview"
                >Flight Review</label
              >
              <input
                type="text"
                class="form-control"
                name="flightReview"
                id="task-evaluate-flight-flightReview"
                data-cy="flightReview"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightReview.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightReview.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.flightReview.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskEvaluateFlight.flightScore')"
                for="task-evaluate-flight-flightScore"
                >Flight Score</label
              >
              <select
                class="form-control"
                id="task-evaluate-flight-flightScore"
                data-cy="flightScore"
                name="flightScore"
                v-model="taskContext.travelReviewProcess.travelReview.flightScore"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.flightScore &&
                    scoreOption.id === taskContext.travelReviewProcess.travelReview.flightScore.id
                      ? taskContext.travelReviewProcess.travelReview.flightScore
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

<script lang="ts" src="./task-evaluate-flight-execute.component.ts"></script>
