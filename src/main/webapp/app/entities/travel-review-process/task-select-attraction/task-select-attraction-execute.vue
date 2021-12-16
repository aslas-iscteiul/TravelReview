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
              <label class="form-control-label" v-text="$t('travelReviewApp.taskSelectAttraction.title')" for="task-select-attraction-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-select-attraction-title"
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
                v-text="$t('travelReviewApp.taskSelectAttraction.description')"
                for="task-select-attraction-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-select-attraction-description"
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
                v-text="$t('travelReviewApp.taskSelectAttraction.travelOrigin')"
                for="task-select-attraction-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-select-attraction-travelOrigin"
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
                v-text="$t('travelReviewApp.taskSelectAttraction.travelDestination')"
                for="task-select-attraction-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-select-attraction-travelDestination"
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
                v-text="$t('travelReviewApp.taskSelectAttraction.attractionPrice')"
                for="task-select-attraction-attractionPrice"
                >Attraction Price</label
              >
              <input
                type="number"
                class="form-control"
                name="attractionPrice"
                id="task-select-attraction-attractionPrice"
                data-cy="attractionPrice"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.attractionPrice.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.attractionPrice.$invalid,
                }"
                v-model.number="$v.taskContext.travelReviewProcess.travelReview.attractionPrice.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskSelectAttraction.attraction')"
                for="task-select-attraction-attraction"
                >Attraction</label
              >
              <select
                class="form-control"
                id="task-select-attraction-attraction"
                data-cy="attraction"
                name="attraction"
                v-model="taskContext.travelReviewProcess.travelReview.attraction"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.attraction &&
                    attractionOption.id === taskContext.travelReviewProcess.travelReview.attraction.id
                      ? taskContext.travelReviewProcess.travelReview.attraction
                      : attractionOption
                  "
                  v-for="attractionOption in attractions"
                  :key="attractionOption.id"
                >
                  {{ attractionOption.name }}
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

<script lang="ts" src="./task-select-attraction-execute.component.ts"></script>
