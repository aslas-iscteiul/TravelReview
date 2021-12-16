<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelReviewApp.accommodation.home.createOrEditLabel"
          data-cy="AccommodationCreateUpdateHeading"
          v-text="$t('travelReviewApp.accommodation.home.createOrEditLabel')"
        >
          Create or edit a Accommodation
        </h2>
        <div>
          <div class="form-group" v-if="accommodation.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="accommodation.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.name')" for="accommodation-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="accommodation-name"
              data-cy="name"
              :class="{ valid: !$v.accommodation.name.$invalid, invalid: $v.accommodation.name.$invalid }"
              v-model="$v.accommodation.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.type')" for="accommodation-type">Type</label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="accommodation-type"
              data-cy="type"
              :class="{ valid: !$v.accommodation.type.$invalid, invalid: $v.accommodation.type.$invalid }"
              v-model="$v.accommodation.type.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('travelReviewApp.accommodation.sustainableAccommodation')"
              for="accommodation-sustainableAccommodation"
              >Sustainable Accommodation</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="sustainableAccommodation"
              id="accommodation-sustainableAccommodation"
              data-cy="sustainableAccommodation"
              :class="{
                valid: !$v.accommodation.sustainableAccommodation.$invalid,
                invalid: $v.accommodation.sustainableAccommodation.$invalid,
              }"
              v-model="$v.accommodation.sustainableAccommodation.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.website')" for="accommodation-website"
              >Website</label
            >
            <input
              type="text"
              class="form-control"
              name="website"
              id="accommodation-website"
              data-cy="website"
              :class="{ valid: !$v.accommodation.website.$invalid, invalid: $v.accommodation.website.$invalid }"
              v-model="$v.accommodation.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.email')" for="accommodation-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="accommodation-email"
              data-cy="email"
              :class="{ valid: !$v.accommodation.email.$invalid, invalid: $v.accommodation.email.$invalid }"
              v-model="$v.accommodation.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.phone')" for="accommodation-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="accommodation-phone"
              data-cy="phone"
              :class="{ valid: !$v.accommodation.phone.$invalid, invalid: $v.accommodation.phone.$invalid }"
              v-model="$v.accommodation.phone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.address')" for="accommodation-address"
              >Address</label
            >
            <input
              type="text"
              class="form-control"
              name="address"
              id="accommodation-address"
              data-cy="address"
              :class="{ valid: !$v.accommodation.address.$invalid, invalid: $v.accommodation.address.$invalid }"
              v-model="$v.accommodation.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.accommodation.classification')" for="accommodation-classification"
              >Classification</label
            >
            <input
              type="number"
              class="form-control"
              name="classification"
              id="accommodation-classification"
              data-cy="classification"
              :class="{ valid: !$v.accommodation.classification.$invalid, invalid: $v.accommodation.classification.$invalid }"
              v-model.number="$v.accommodation.classification.$model"
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
            :disabled="$v.accommodation.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./accommodation-update.component.ts"></script>
