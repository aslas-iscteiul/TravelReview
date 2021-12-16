<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="accommodation">
        <h2 class="jh-entity-heading" data-cy="accommodationDetailsHeading">
          <span v-text="$t('travelReviewApp.accommodation.detail.title')">Accommodation</span> {{ accommodation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.name')">Name</span>
          </dt>
          <dd>
            <span>{{ accommodation.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.type')">Type</span>
          </dt>
          <dd>
            <span>{{ accommodation.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.sustainableAccommodation')">Sustainable Accommodation</span>
          </dt>
          <dd>
            <span>{{ accommodation.sustainableAccommodation }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.website')">Website</span>
          </dt>
          <dd>
            <span>{{ accommodation.website }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.email')">Email</span>
          </dt>
          <dd>
            <span>{{ accommodation.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.phone')">Phone</span>
          </dt>
          <dd>
            <span>{{ accommodation.phone }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.address')">Address</span>
          </dt>
          <dd>
            <span>{{ accommodation.address }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.accommodation.classification')">Classification</span>
          </dt>
          <dd>
            <span>{{ accommodation.classification }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="accommodation.id"
          :to="{ name: 'AccommodationEdit', params: { accommodationId: accommodation.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./accommodation-details.component.ts"></script>
