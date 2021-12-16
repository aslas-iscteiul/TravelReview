/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AccommodationComponent from '@/entities/accommodation/accommodation.vue';
import AccommodationClass from '@/entities/accommodation/accommodation.component';
import AccommodationService from '@/entities/accommodation/accommodation.service';

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
  describe('Accommodation Management Component', () => {
    let wrapper: Wrapper<AccommodationClass>;
    let comp: AccommodationClass;
    let accommodationServiceStub: SinonStubbedInstance<AccommodationService>;

    beforeEach(() => {
      accommodationServiceStub = sinon.createStubInstance<AccommodationService>(AccommodationService);
      accommodationServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AccommodationClass>(AccommodationComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          accommodationService: () => accommodationServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      accommodationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAccommodations();
      await comp.$nextTick();

      // THEN
      expect(accommodationServiceStub.retrieve.called).toBeTruthy();
      expect(comp.accommodations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      accommodationServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeAccommodation();
      await comp.$nextTick();

      // THEN
      expect(accommodationServiceStub.delete.called).toBeTruthy();
      expect(accommodationServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
