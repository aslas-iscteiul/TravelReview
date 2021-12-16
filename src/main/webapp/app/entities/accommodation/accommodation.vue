<template>
  <div>
    <h2 id="page-heading" data-cy="AccommodationHeading">
      <span v-text="$t('travelReviewApp.accommodation.home.title')" id="accommodation-heading">Accommodations</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('travelReviewApp.accommodation.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'AccommodationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-accommodation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('travelReviewApp.accommodation.home.createLabel')"> Create a new Accommodation </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && accommodations && accommodations.length === 0">
      <span v-text="$t('travelReviewApp.accommodation.home.notFound')">No accommodations found</span>
    </div>
    <div class="table-responsive" v-if="accommodations && accommodations.length > 0">
      <table class="table table-striped" aria-describedby="accommodations">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.name')">Name</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.type')">Type</span></th>
            <th scope="row">
              <span v-text="$t('travelReviewApp.accommodation.sustainableAccommodation')">Sustainable Accommodation</span>
            </th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.website')">Website</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.email')">Email</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.phone')">Phone</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.address')">Address</span></th>
            <th scope="row"><span v-text="$t('travelReviewApp.accommodation.classification')">Classification</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="accommodation in accommodations" :key="accommodation.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AccommodationView', params: { accommodationId: accommodation.id } }">{{
                accommodation.id
              }}</router-link>
            </td>
            <td>{{ accommodation.name }}</td>
            <td>{{ accommodation.type }}</td>
            <td>{{ accommodation.sustainableAccommodation }}</td>
            <td>{{ accommodation.website }}</td>
            <td>{{ accommodation.email }}</td>
            <td>{{ accommodation.phone }}</td>
            <td>{{ accommodation.address }}</td>
            <td>{{ accommodation.classification }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'AccommodationView', params: { accommodationId: accommodation.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'AccommodationEdit', params: { accommodationId: accommodation.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(accommodation)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="travelReviewApp.accommodation.delete.question"
          data-cy="accommodationDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-accommodation-heading" v-text="$t('travelReviewApp.accommodation.delete.question', { id: removeId })">
          Are you sure you want to delete this Accommodation?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-accommodation"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeAccommodation()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./accommodation.component.ts"></script>
