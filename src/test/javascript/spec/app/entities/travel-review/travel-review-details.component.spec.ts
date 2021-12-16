/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TravelReviewDetailComponent from '@/entities/travel-review/travel-review-details.vue';
import TravelReviewClass from '@/entities/travel-review/travel-review-details.component';
import TravelReviewService from '@/entities/travel-review/travel-review.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('TravelReview Management Detail Component', () => {
    let wrapper: Wrapper<TravelReviewClass>;
    let comp: TravelReviewClass;
    let travelReviewServiceStub: SinonStubbedInstance<TravelReviewService>;

    beforeEach(() => {
      travelReviewServiceStub = sinon.createStubInstance<TravelReviewService>(TravelReviewService);

      wrapper = shallowMount<TravelReviewClass>(TravelReviewDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { travelReviewService: () => travelReviewServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTravelReview = { id: 123 };
        travelReviewServiceStub.find.resolves(foundTravelReview);

        // WHEN
        comp.retrieveTravelReview(123);
        await comp.$nextTick();

        // THEN
        expect(comp.travelReview).toBe(foundTravelReview);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTravelReview = { id: 123 };
        travelReviewServiceStub.find.resolves(foundTravelReview);

        // WHEN
        comp.beforeRouteEnter({ params: { travelReviewId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.travelReview).toBe(foundTravelReview);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
