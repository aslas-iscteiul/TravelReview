import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const TravelReview = () => import('@/entities/travel-review/travel-review.vue');
// prettier-ignore
const TravelReviewDetails = () => import('@/entities/travel-review/travel-review-details.vue');
// prettier-ignore
const TravelReviewProcessDetails = () => import('@/entities/travel-review-process/travel-review-process-details.vue');
// prettier-ignore
const TravelReviewProcessList = () => import('@/entities/travel-review-process/travel-review-process-list.vue');
// prettier-ignore
const TravelReviewStartFormInit = () => import('@/entities/travel-review-process/travel-review-start-form-init.vue');
// prettier-ignore
const TravelReviewProcess_TaskRegisterTravelDetails = () => import('@/entities/travel-review-process/task-register-travel/task-register-travel-details.vue');
// prettier-ignore
const TravelReviewProcess_TaskRegisterTravelExecute = () => import('@/entities/travel-review-process/task-register-travel/task-register-travel-execute.vue');
// prettier-ignore
const TravelReviewProcess_TaskRegisterFlightDataDetails = () => import('@/entities/travel-review-process/task-register-flight-data/task-register-flight-data-details.vue');
// prettier-ignore
const TravelReviewProcess_TaskRegisterFlightDataExecute = () => import('@/entities/travel-review-process/task-register-flight-data/task-register-flight-data-execute.vue');
// prettier-ignore
const AirlineCompany = () => import('@/entities/airline-company/airline-company.vue');
// prettier-ignore
const AirlineCompanyUpdate = () => import('@/entities/airline-company/airline-company-update.vue');
// prettier-ignore
const AirlineCompanyDetails = () => import('@/entities/airline-company/airline-company-details.vue');
// prettier-ignore
const Accommodation = () => import('@/entities/accommodation/accommodation.vue');
// prettier-ignore
const AccommodationUpdate = () => import('@/entities/accommodation/accommodation-update.vue');
// prettier-ignore
const AccommodationDetails = () => import('@/entities/accommodation/accommodation-details.vue');
// prettier-ignore
const TravelReviewProcess_TaskCorrectTravelDatesDetails = () => import('@/entities/travel-review-process/task-correct-travel-dates/task-correct-travel-dates-details.vue');
// prettier-ignore
const TravelReviewProcess_TaskCorrectTravelDatesExecute = () => import('@/entities/travel-review-process/task-correct-travel-dates/task-correct-travel-dates-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/travel-review',
    name: 'TravelReview',
    component: TravelReview,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/travel-review/:travelReviewId/view',
    name: 'TravelReviewView',
    component: TravelReviewDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/instance/:processInstanceId/view',
    name: 'TravelReviewProcessView',
    component: TravelReviewProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/instances',
    name: 'TravelReviewProcessList',
    component: TravelReviewProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/init',
    name: 'TravelReviewStartFormInit',
    component: TravelReviewStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/RegisterTravel/:taskInstanceId/view',
    name: 'TravelReviewProcess_TaskRegisterTravelDetails',
    component: TravelReviewProcess_TaskRegisterTravelDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/RegisterTravel/:taskInstanceId/execute',
    name: 'TravelReviewProcess_TaskRegisterTravelExecute',
    component: TravelReviewProcess_TaskRegisterTravelExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/RegisterFlightData/:taskInstanceId/view',
    name: 'TravelReviewProcess_TaskRegisterFlightDataDetails',
    component: TravelReviewProcess_TaskRegisterFlightDataDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/RegisterFlightData/:taskInstanceId/execute',
    name: 'TravelReviewProcess_TaskRegisterFlightDataExecute',
    component: TravelReviewProcess_TaskRegisterFlightDataExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company',
    name: 'AirlineCompany',
    component: AirlineCompany,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/new',
    name: 'AirlineCompanyCreate',
    component: AirlineCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/:airlineCompanyId/edit',
    name: 'AirlineCompanyEdit',
    component: AirlineCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/:airlineCompanyId/view',
    name: 'AirlineCompanyView',
    component: AirlineCompanyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accommodation',
    name: 'Accommodation',
    component: Accommodation,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accommodation/new',
    name: 'AccommodationCreate',
    component: AccommodationUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accommodation/:accommodationId/edit',
    name: 'AccommodationEdit',
    component: AccommodationUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accommodation/:accommodationId/view',
    name: 'AccommodationView',
    component: AccommodationDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/CorrectTravelDates/:taskInstanceId/view',
    name: 'TravelReviewProcess_TaskCorrectTravelDatesDetails',
    component: TravelReviewProcess_TaskCorrectTravelDatesDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelReviewProcess/task/CorrectTravelDates/:taskInstanceId/execute',
    name: 'TravelReviewProcess_TaskCorrectTravelDatesExecute',
    component: TravelReviewProcess_TaskCorrectTravelDatesExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
