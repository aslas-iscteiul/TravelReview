/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import AccommodationUpdateComponent from '@/entities/accommodation/accommodation-update.vue';
import AccommodationClass from '@/entities/accommodation/accommodation-update.component';
import AccommodationService from '@/entities/accommodation/accommodation.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Accommodation Management Update Component', () => {
    let wrapper: Wrapper<AccommodationClass>;
    let comp: AccommodationClass;
    let accommodationServiceStub: SinonStubbedInstance<AccommodationService>;

    beforeEach(() => {
      accommodationServiceStub = sinon.createStubInstance<AccommodationService>(AccommodationService);

      wrapper = shallowMount<AccommodationClass>(AccommodationUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          accommodationService: () => accommodationServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.accommodation = entity;
        accommodationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(accommodationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.accommodation = entity;
        accommodationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(accommodationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAccommodation = { id: 123 };
        accommodationServiceStub.find.resolves(foundAccommodation);
        accommodationServiceStub.retrieve.resolves([foundAccommodation]);

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
