/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AttractionComponent from '@/entities/attraction/attraction.vue';
import AttractionClass from '@/entities/attraction/attraction.component';
import AttractionService from '@/entities/attraction/attraction.service';

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
  describe('Attraction Management Component', () => {
    let wrapper: Wrapper<AttractionClass>;
    let comp: AttractionClass;
    let attractionServiceStub: SinonStubbedInstance<AttractionService>;

    beforeEach(() => {
      attractionServiceStub = sinon.createStubInstance<AttractionService>(AttractionService);
      attractionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AttractionClass>(AttractionComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          attractionService: () => attractionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      attractionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAttractions();
      await comp.$nextTick();

      // THEN
      expect(attractionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.attractions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      attractionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeAttraction();
      await comp.$nextTick();

      // THEN
      expect(attractionServiceStub.delete.called).toBeTruthy();
      expect(attractionServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
