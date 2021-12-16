<template>
  <div>
    <h2 id="page-heading" data-cy="TravelReviewHeading">
      <span v-text="$t('travelReviewApp.travelReview.home.title')" id="travel-review-heading">Travel Reviews</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('travelReviewApp.travelReview.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && travelReviews && travelReviews.length === 0">
      <span v-text="$t('travelReviewApp.travelReview.home.notFound')">No travelReviews found</span>
    </div>
    <div class="table-responsive" v-if="travelReviews && travelReviews.length > 0">
      <table class="table table-striped" aria-describedby="travelReviews">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.title')">Title</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.description')">Description</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelOrigin')">Travel Origin</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelDestination')">Travel Destination</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelWithFlight')">Travel With Flight</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelStartDate')">Travel Start Date</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelEndDate')">Travel End Date</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightTicketNumber')">Flight Ticket Number</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightClass')">Flight Class</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightDuration')">Flight Duration</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightPrice')">Flight Price</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightReview')">Flight Review</span></th>
            <th scope="row">
              <span v-text="$t('travelReviewApp.travelReview.accommodationBookingNumber')">Accommodation Booking Number</span>
            </th>
            <th scope="row">
              <span v-text="$t('travelReviewApp.travelReview.accommodationBookingPrice')">Accommodation Booking Price</span>
            </th>
            <th scope="row">
              <span v-text="$t('travelReviewApp.travelReview.accommodationBookingReview')">Accommodation Booking Review</span>
            </th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.attractionPrice')">Attraction Price</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.attractionReview')">Attraction Review</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.travelCost')">Travel Cost</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.airlineCompany')">Airline Company</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.accommodation')">Accommodation</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.travelReview.flightScore')">Flight Score</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="travelReview in travelReviews" :key="travelReview.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TravelReviewView', params: { travelReviewId: travelReview.id } }">{{
                travelReview.id
              }}</router-link>
            </td>
            <td>{{ travelReview.title }}</td>
            <td>{{ travelReview.description }}</td>
            <td>{{ travelReview.travelOrigin }}</td>
            <td>{{ travelReview.travelDestination }}</td>
            <td>{{ travelReview.travelWithFlight }}</td>
            <td>{{ travelReview.travelStartDate }}</td>
            <td>{{ travelReview.travelEndDate }}</td>
            <td>{{ travelReview.flightTicketNumber }}</td>
            <td>{{ travelReview.flightClass }}</td>
            <td>{{ travelReview.flightDuration }}</td>
            <td>{{ travelReview.flightPrice }}</td>
            <td>{{ travelReview.flightReview }}</td>
            <td>{{ travelReview.accommodationBookingNumber }}</td>
            <td>{{ travelReview.accommodationBookingPrice }}</td>
            <td>{{ travelReview.accommodationBookingReview }}</td>
            <td>{{ travelReview.attractionPrice }}</td>
            <td>{{ travelReview.attractionReview }}</td>
            <td>{{ travelReview.travelCost }}</td>
            <td>
              <div v-if="travelReview.airlineCompany">
                <router-link :to="{ name: 'AirlineCompanyView', params: { airlineCompanyId: travelReview.airlineCompany.id } }">{{
                  travelReview.airlineCompany.name
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="travelReview.accommodation">
                <router-link :to="{ name: 'AccommodationView', params: { accommodationId: travelReview.accommodation.id } }">{{
                  travelReview.accommodation.name
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="travelReview.flightScore">
                <router-link :to="{ name: 'ScoreView', params: { scoreId: travelReview.flightScore.id } }">{{
                  travelReview.flightScore.description
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TravelReviewView', params: { travelReviewId: travelReview.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="travelReviewApp.travelReview.delete.question"
          data-cy="travelReviewDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-travelReview-heading" v-text="$t('travelReviewApp.travelReview.delete.question', { id: removeId })">
          Are you sure you want to delete this Travel Review?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-travelReview"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTravelReview()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./travel-review.component.ts"></script>
