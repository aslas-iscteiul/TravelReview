/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import AttractionUpdateComponent from '@/entities/attraction/attraction-update.vue';
import AttractionClass from '@/entities/attraction/attraction-update.component';
import AttractionService from '@/entities/attraction/attraction.service';

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
  describe('Attraction Management Update Component', () => {
    let wrapper: Wrapper<AttractionClass>;
    let comp: AttractionClass;
    let attractionServiceStub: SinonStubbedInstance<AttractionService>;

    beforeEach(() => {
      attractionServiceStub = sinon.createStubInstance<AttractionService>(AttractionService);

      wrapper = shallowMount<AttractionClass>(AttractionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          attractionService: () => attractionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.attraction = entity;
        attractionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attractionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.attraction = entity;
        attractionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attractionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAttraction = { id: 123 };
        attractionServiceStub.find.resolves(foundAttraction);
        attractionServiceStub.retrieve.resolves([foundAttraction]);

        // WHEN
        comp.beforeRouteEnter({ params: { attractionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.attraction).toBe(foundAttraction);
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
