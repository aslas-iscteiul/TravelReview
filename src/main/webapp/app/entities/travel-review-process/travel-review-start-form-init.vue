<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelReviewApp.travelReviewStartForm.home.createOrEditLabel"
          data-cy="TravelReviewStartFormCreateUpdateHeading"
          v-text="$t('travelReviewApp.travelReviewStartForm.home.createOrEditLabel')"
        >
          Create or edit a TravelReviewStartForm
        </h2>
        <div v-if="travelReviewProcess.processInstance">
          <akip-show-process-definition :processDefinition="travelReviewProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="travelReviewProcess.travelReview">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewStartForm.title')"
                    for="travel-review-start-form-title"
                    >Title</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="title"
                    id="travel-review-start-form-title"
                    data-cy="title"
                    :class="{
                      valid: !$v.travelReviewProcess.travelReview.title.$invalid,
                      invalid: $v.travelReviewProcess.travelReview.title.$invalid,
                    }"
                    v-model="$v.travelReviewProcess.travelReview.title.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewStartForm.description')"
                    for="travel-review-start-form-description"
                    >Description</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="description"
                    id="travel-review-start-form-description"
                    data-cy="description"
                    :class="{
                      valid: !$v.travelReviewProcess.travelReview.description.$invalid,
                      invalid: $v.travelReviewProcess.travelReview.description.$invalid,
                    }"
                    v-model="$v.travelReviewProcess.travelReview.description.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewStartForm.userEmail')"
                    for="travel-review-start-form-userEmail"
                    >User Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="travel-review-start-form-userEmail"
                    data-cy="userEmail"
                    :class="{
                      valid: !$v.travelReviewProcess.travelReview.userEmail.$invalid,
                      invalid: $v.travelReviewProcess.travelReview.userEmail.$invalid,
                    }"
                    v-model="$v.travelReviewProcess.travelReview.userEmail.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewStartForm.userName')"
                    for="travel-review-start-form-userName"
                    >User Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userName"
                    id="travel-review-start-form-userName"
                    data-cy="userName"
                    :class="{
                      valid: !$v.travelReviewProcess.travelReview.userName.$invalid,
                      invalid: $v.travelReviewProcess.travelReview.userName.$invalid,
                    }"
                    v-model="$v.travelReviewProcess.travelReview.userName.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.travelReviewProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./travel-review-start-form-init.component.ts"></script>
