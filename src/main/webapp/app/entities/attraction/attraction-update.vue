<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelReviewApp.attraction.home.createOrEditLabel"
          data-cy="AttractionCreateUpdateHeading"
          v-text="$t('travelReviewApp.attraction.home.createOrEditLabel')"
        >
          Create or edit a Attraction
        </h2>
        <div>
          <div class="form-group" v-if="attraction.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="attraction.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.attraction.name')" for="attraction-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="attraction-name"
              data-cy="name"
              :class="{ valid: !$v.attraction.name.$invalid, invalid: $v.attraction.name.$invalid }"
              v-model="$v.attraction.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.attraction.description')" for="attraction-description"
              >Description</label
            >
            <input
              type="text"
              class="form-control"
              name="description"
              id="attraction-description"
              data-cy="description"
              :class="{ valid: !$v.attraction.description.$invalid, invalid: $v.attraction.description.$invalid }"
              v-model="$v.attraction.description.$model"
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
            :disabled="$v.attraction.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./attraction-update.component.ts"></script>
