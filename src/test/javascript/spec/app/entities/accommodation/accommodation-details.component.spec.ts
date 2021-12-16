/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AccommodationDetailComponent from '@/entities/accommodation/accommodation-details.vue';
import AccommodationClass from '@/entities/accommodation/accommodation-details.component';
import AccommodationService from '@/entities/accommodation/accommodation.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Accommodation Management Detail Component', () => {
    let wrapper: Wrapper<AccommodationClass>;
    let comp: AccommodationClass;
    let accommodationServiceStub: SinonStubbedInstance<AccommodationService>;

    beforeEach(() => {
      accommodationServiceStub = sinon.createStubInstance<AccommodationService>(AccommodationService);

      wrapper = shallowMount<AccommodationClass>(AccommodationDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { accommodationService: () => accommodationServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAccommodation = { id: 123 };
        accommodationServiceStub.find.resolves(foundAccommodation);

        // WHEN
        comp.retrieveAccommodation(123);
        await comp.$nextTick();

        // THEN
        expect(comp.accommodation).toBe(foundAccommodation);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAccommodation = { id: 123 };
        accommodationServiceStub.find.resolves(foundAccommodation);

        // WHEN
        comp.beforeRouteEnter({ params: { accommodationId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.accommodation).toBe(foundAccommodation);
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
