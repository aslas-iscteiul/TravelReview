<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="travelReview">
        <h2 class="jh-entity-heading" data-cy="travelReviewDetailsHeading">
          <span v-text="$t('travelReviewApp.travelReview.detail.title')">TravelReview</span> {{ travelReview.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.title')">Title</span>
          </dt>
          <dd>
            <span>{{ travelReview.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.description')">Description</span>
          </dt>
          <dd>
            <span>{{ travelReview.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelOrigin')">Travel Origin</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelOrigin }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelDestination')">Travel Destination</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelDestination }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelWithFlight')">Travel With Flight</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelWithFlight }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelStartDate')">Travel Start Date</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelStartDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelEndDate')">Travel End Date</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelEndDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightTicketNumber')">Flight Ticket Number</span>
          </dt>
          <dd>
            <span>{{ travelReview.flightTicketNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightClass')">Flight Class</span>
          </dt>
          <dd>
            <span>{{ travelReview.flightClass }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightDuration')">Flight Duration</span>
          </dt>
          <dd>
            <span>{{ travelReview.flightDuration }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightPrice')">Flight Price</span>
          </dt>
          <dd>
            <span>{{ travelReview.flightPrice }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightReview')">Flight Review</span>
          </dt>
          <dd>
            <span>{{ travelReview.flightReview }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.accommodationBookingNumber')">Accommodation Booking Number</span>
          </dt>
          <dd>
            <span>{{ travelReview.accommodationBookingNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.accommodationBookingPrice')">Accommodation Booking Price</span>
          </dt>
          <dd>
            <span>{{ travelReview.accommodationBookingPrice }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.accommodationBookingReview')">Accommodation Booking Review</span>
          </dt>
          <dd>
            <span>{{ travelReview.accommodationBookingReview }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.attractionPrice')">Attraction Price</span>
          </dt>
          <dd>
            <span>{{ travelReview.attractionPrice }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.attractionReview')">Attraction Review</span>
          </dt>
          <dd>
            <span>{{ travelReview.attractionReview }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.travelCost')">Travel Cost</span>
          </dt>
          <dd>
            <span>{{ travelReview.travelCost }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.airlineCompany')">Airline Company</span>
          </dt>
          <dd>
            <div v-if="travelReview.airlineCompany">
              <router-link :to="{ name: 'AirlineCompanyView', params: { airlineCompanyId: travelReview.airlineCompany.id } }">{{
                travelReview.airlineCompany.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.accommodation')">Accommodation</span>
          </dt>
          <dd>
            <div v-if="travelReview.accommodation">
              <router-link :to="{ name: 'AccommodationView', params: { accommodationId: travelReview.accommodation.id } }">{{
                travelReview.accommodation.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.flightScore')">Flight Score</span>
          </dt>
          <dd>
            <div v-if="travelReview.flightScore">
              <router-link :to="{ name: 'ScoreView', params: { scoreId: travelReview.flightScore.id } }">{{
                travelReview.flightScore.description
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.bookingScore')">Booking Score</span>
          </dt>
          <dd>
            <div v-if="travelReview.bookingScore">
              <router-link :to="{ name: 'ScoreView', params: { scoreId: travelReview.bookingScore.id } }">{{
                travelReview.bookingScore.description
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelReviewApp.travelReview.attraction')">Attraction</span>
          </dt>
          <dd>
            <div v-if="travelReview.attraction">
              <router-link :to="{ name: 'AttractionView', params: { attractionId: travelReview.attraction.id } }">{{
                travelReview.attraction.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./travel-review-details.component.ts"></script>
