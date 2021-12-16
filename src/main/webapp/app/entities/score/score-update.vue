<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelReviewApp.score.home.createOrEditLabel"
          data-cy="ScoreCreateUpdateHeading"
          v-text="$t('travelReviewApp.score.home.createOrEditLabel')"
        >
          Create or edit a Score
        </h2>
        <div>
          <div class="form-group" v-if="score.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="score.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.score.number')" for="score-number">Number</label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="score-number"
              data-cy="number"
              :class="{ valid: !$v.score.number.$invalid, invalid: $v.score.number.$invalid }"
              v-model.number="$v.score.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.score.description')" for="score-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="score-description"
              data-cy="description"
              :class="{ valid: !$v.score.description.$invalid, invalid: $v.score.description.$invalid }"
              v-model="$v.score.description.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.score.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./score-update.component.ts"></script>
