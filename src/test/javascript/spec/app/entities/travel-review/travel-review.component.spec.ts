/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TravelReviewComponent from '@/entities/travel-review/travel-review.vue';
import TravelReviewClass from '@/entities/travel-review/travel-review.component';
import TravelReviewService from '@/entities/travel-review/travel-review.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('TravelReview Management Component', () => {
    let wrapper: Wrapper<TravelReviewClass>;
    let comp: TravelReviewClass;
    let travelReviewServiceStub: SinonStubbedInstance<TravelReviewService>;

    beforeEach(() => {
      travelReviewServiceStub = sinon.createStubInstance<TravelReviewService>(TravelReviewService);
      travelReviewServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TravelReviewClass>(TravelReviewComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          travelReviewService: () => travelReviewServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      travelReviewServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTravelReviews();
      await comp.$nextTick();

      // THEN
      expect(travelReviewServiceStub.retrieve.called).toBeTruthy();
      expect(comp.travelReviews[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
