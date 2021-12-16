<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="travelReviewProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="travelReviewProcessDetailsHeading">
          <span v-text="$t('travelReviewApp.travelReviewProcess.detail.title')">TravelReviewProcess</span> {{ travelReviewProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="travelReviewProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('travelReviewApp.travelReviewProcess.detail.title')">TravelReviewProcess</span> #{{ travelReviewProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="travelReviewProcess.travelReview">
              <h5 class="card-header">
                <span class="title"> Travel Review #{{ travelReviewProcess.travelReview.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.title')">title</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="title"
                    id="travel-review-title"
                    data-cy="title"
                    v-model="travelReviewProcess.travelReview.title"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.description')">description</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="description"
                    id="travel-review-description"
                    data-cy="description"
                    v-model="travelReviewProcess.travelReview.description"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelOrigin')">travelOrigin</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelOrigin"
                    id="travel-review-travelOrigin"
                    data-cy="travelOrigin"
                    v-model="travelReviewProcess.travelReview.travelOrigin"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelDestination')"
                    >travelDestination</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelDestination"
                    id="travel-review-travelDestination"
                    data-cy="travelDestination"
                    v-model="travelReviewProcess.travelReview.travelDestination"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelWithFlight')"
                    >travelWithFlight</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelWithFlight"
                    id="travel-review-travelWithFlight"
                    data-cy="travelWithFlight"
                    v-model="travelReviewProcess.travelReview.travelWithFlight"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelStartDate')"
                    >travelStartDate</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelStartDate"
                    id="travel-review-travelStartDate"
                    data-cy="travelStartDate"
                    v-model="travelReviewProcess.travelReview.travelStartDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelEndDate')">travelEndDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelEndDate"
                    id="travel-review-travelEndDate"
                    data-cy="travelEndDate"
                    v-model="travelReviewProcess.travelReview.travelEndDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.flightTicketNumber')"
                    >flightTicketNumber</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="flightTicketNumber"
                    id="travel-review-flightTicketNumber"
                    data-cy="flightTicketNumber"
                    v-model="travelReviewProcess.travelReview.flightTicketNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.flightClass')">flightClass</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="flightClass"
                    id="travel-review-flightClass"
                    data-cy="flightClass"
                    v-model="travelReviewProcess.travelReview.flightClass"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.flightDuration')">flightDuration</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="flightDuration"
                    id="travel-review-flightDuration"
                    data-cy="flightDuration"
                    v-model="travelReviewProcess.travelReview.flightDuration"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.flightPrice')">flightPrice</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="flightPrice"
                    id="travel-review-flightPrice"
                    data-cy="flightPrice"
                    v-model="travelReviewProcess.travelReview.flightPrice"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.flightReview')">flightReview</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="flightReview"
                    id="travel-review-flightReview"
                    data-cy="flightReview"
                    v-model="travelReviewProcess.travelReview.flightReview"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.accommodationBookingNumber')"
                    >accommodationBookingNumber</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="accommodationBookingNumber"
                    id="travel-review-accommodationBookingNumber"
                    data-cy="accommodationBookingNumber"
                    v-model="travelReviewProcess.travelReview.accommodationBookingNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.accommodationBookingPrice')"
                    >accommodationBookingPrice</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="accommodationBookingPrice"
                    id="travel-review-accommodationBookingPrice"
                    data-cy="accommodationBookingPrice"
                    v-model="travelReviewProcess.travelReview.accommodationBookingPrice"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.accommodationBookingReview')"
                    >accommodationBookingReview</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="accommodationBookingReview"
                    id="travel-review-accommodationBookingReview"
                    data-cy="accommodationBookingReview"
                    v-model="travelReviewProcess.travelReview.accommodationBookingReview"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.attractionPrice')"
                    >attractionPrice</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="attractionPrice"
                    id="travel-review-attractionPrice"
                    data-cy="attractionPrice"
                    v-model="travelReviewProcess.travelReview.attractionPrice"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.attractionReview')"
                    >attractionReview</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="attractionReview"
                    id="travel-review-attractionReview"
                    data-cy="attractionReview"
                    v-model="travelReviewProcess.travelReview.attractionReview"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelReviewApp.travelReviewProcess.travelCost')">travelCost</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelCost"
                    id="travel-review-travelCost"
                    data-cy="travelCost"
                    v-model="travelReviewProcess.travelReview.travelCost"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewProcess.airlineCompany')"
                    for="travel-review-process-airlineCompany"
                    >Airline Company</label
                  >
                  <input
                    v-if="travelReviewProcess.travelReview.airlineCompany"
                    readonly
                    type="text"
                    class="form-control"
                    name="airlineCompany"
                    id="travel-review-airlineCompany"
                    data-cy="airlineCompany"
                    :value="travelReviewProcess.travelReview.airlineCompany.name"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="airlineCompany"
                    id="travel-review-airlineCompany"
                    data-cy="airlineCompany"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelReviewApp.travelReviewProcess.accommodation')"
                    for="travel-review-process-accommodation"
                    >Accommodation</label
                  >
                  <input
                    v-if="travelReviewProcess.travelReview.accommodation"
                    readonly
                    type="text"
                    class="form-control"
                    name="accommodation"
                    id="travel-review-accommodation"
                    data-cy="accommodation"
                    :value="travelReviewProcess.travelReview.accommodation.name"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="accommodation"
                    id="travel-review-accommodation"
                    data-cy="accommodation"
                    value=""
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./travel-review-process-details.component.ts"></script>
