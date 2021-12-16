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
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.title')"
                for="task-register-flight-data-title"
                >Title</label
              >
              <input
                type="text"
                class="form-control"
                name="title"
                id="task-register-flight-data-title"
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
                v-text="$t('travelReviewApp.taskRegisterFlightData.description')"
                for="task-register-flight-data-description"
                >Description</label
              >
              <input
                type="text"
                class="form-control"
                name="description"
                id="task-register-flight-data-description"
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
                v-text="$t('travelReviewApp.taskRegisterFlightData.travelOrigin')"
                for="task-register-flight-data-travelOrigin"
                >Travel Origin</label
              >
              <input
                type="text"
                class="form-control"
                name="travelOrigin"
                id="task-register-flight-data-travelOrigin"
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
                v-text="$t('travelReviewApp.taskRegisterFlightData.travelDestination')"
                for="task-register-flight-data-travelDestination"
                >Travel Destination</label
              >
              <input
                type="text"
                class="form-control"
                name="travelDestination"
                id="task-register-flight-data-travelDestination"
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
                v-text="$t('travelReviewApp.taskRegisterFlightData.flightTicketNumber')"
                for="task-register-flight-data-flightTicketNumber"
                >Flight Ticket Number</label
              >
              <input
                type="text"
                class="form-control"
                name="flightTicketNumber"
                id="task-register-flight-data-flightTicketNumber"
                data-cy="flightTicketNumber"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.flightTicketNumber.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.flightClass')"
                for="task-register-flight-data-flightClass"
                >Flight Class</label
              >
              <input
                type="text"
                class="form-control"
                name="flightClass"
                id="task-register-flight-data-flightClass"
                data-cy="flightClass"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightClass.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightClass.$invalid,
                }"
                v-model="$v.taskContext.travelReviewProcess.travelReview.flightClass.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.flightDuration')"
                for="task-register-flight-data-flightDuration"
                >Flight Duration</label
              >
              <input
                type="number"
                class="form-control"
                name="flightDuration"
                id="task-register-flight-data-flightDuration"
                data-cy="flightDuration"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightDuration.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightDuration.$invalid,
                }"
                v-model.number="$v.taskContext.travelReviewProcess.travelReview.flightDuration.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.flightPrice')"
                for="task-register-flight-data-flightPrice"
                >Flight Price</label
              >
              <input
                type="number"
                class="form-control"
                name="flightPrice"
                id="task-register-flight-data-flightPrice"
                data-cy="flightPrice"
                :class="{
                  valid: !$v.taskContext.travelReviewProcess.travelReview.flightPrice.$invalid,
                  invalid: $v.taskContext.travelReviewProcess.travelReview.flightPrice.$invalid,
                }"
                v-model.number="$v.taskContext.travelReviewProcess.travelReview.flightPrice.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelReviewApp.taskRegisterFlightData.airlineCompany')"
                for="task-register-flight-data-airlineCompany"
                >Airline Company</label
              >
              <select
                class="form-control"
                id="task-register-flight-data-airlineCompany"
                data-cy="airlineCompany"
                name="airlineCompany"
                v-model="taskContext.travelReviewProcess.travelReview.airlineCompany"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelReviewProcess.travelReview.airlineCompany &&
                    airlineCompanyOption.id === taskContext.travelReviewProcess.travelReview.airlineCompany.id
                      ? taskContext.travelReviewProcess.travelReview.airlineCompany
                      : airlineCompanyOption
                  "
                  v-for="airlineCompanyOption in airlineCompanies"
                  :key="airlineCompanyOption.id"
                >
                  {{ airlineCompanyOption.name }}
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

<script lang="ts" src="./task-register-flight-data-execute.component.ts"></script>
