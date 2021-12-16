<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelReviewApp.airlineCompany.home.createOrEditLabel"
          data-cy="AirlineCompanyCreateUpdateHeading"
          v-text="$t('travelReviewApp.airlineCompany.home.createOrEditLabel')"
        >
          Create or edit a AirlineCompany
        </h2>
        <div>
          <div class="form-group" v-if="airlineCompany.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="airlineCompany.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.airlineCompany.name')" for="airline-company-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="airline-company-name"
              data-cy="name"
              :class="{ valid: !$v.airlineCompany.name.$invalid, invalid: $v.airlineCompany.name.$invalid }"
              v-model="$v.airlineCompany.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.airlineCompany.website')" for="airline-company-website"
              >Website</label
            >
            <input
              type="text"
              class="form-control"
              name="website"
              id="airline-company-website"
              data-cy="website"
              :class="{ valid: !$v.airlineCompany.website.$invalid, invalid: $v.airlineCompany.website.$invalid }"
              v-model="$v.airlineCompany.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.airlineCompany.email')" for="airline-company-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="airline-company-email"
              data-cy="email"
              :class="{ valid: !$v.airlineCompany.email.$invalid, invalid: $v.airlineCompany.email.$invalid }"
              v-model="$v.airlineCompany.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.airlineCompany.headquarters')" for="airline-company-headquarters"
              >Headquarters</label
            >
            <input
              type="text"
              class="form-control"
              name="headquarters"
              id="airline-company-headquarters"
              data-cy="headquarters"
              :class="{ valid: !$v.airlineCompany.headquarters.$invalid, invalid: $v.airlineCompany.headquarters.$invalid }"
              v-model="$v.airlineCompany.headquarters.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelReviewApp.airlineCompany.foundation')" for="airline-company-foundation"
              >Foundation</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="airline-company-foundation"
                  v-model="$v.airlineCompany.foundation.$model"
                  name="foundation"
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
                id="airline-company-foundation"
                data-cy="foundation"
                type="text"
                class="form-control"
                name="foundation"
                :class="{ valid: !$v.airlineCompany.foundation.$invalid, invalid: $v.airlineCompany.foundation.$invalid }"
                v-model="$v.airlineCompany.foundation.$model"
              />
            </b-input-group>
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
            :disabled="$v.airlineCompany.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./airline-company-update.component.ts"></script>
