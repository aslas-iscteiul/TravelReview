/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AttractionDetailComponent from '@/entities/attraction/attraction-details.vue';
import AttractionClass from '@/entities/attraction/attraction-details.component';
import AttractionService from '@/entities/attraction/attraction.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Attraction Management Detail Component', () => {
    let wrapper: Wrapper<AttractionClass>;
    let comp: AttractionClass;
    let attractionServiceStub: SinonStubbedInstance<AttractionService>;

    beforeEach(() => {
      attractionServiceStub = sinon.createStubInstance<AttractionService>(AttractionService);

      wrapper = shallowMount<AttractionClass>(AttractionDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { attractionService: () => attractionServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAttraction = { id: 123 };
        attractionServiceStub.find.resolves(foundAttraction);

        // WHEN
        comp.retrieveAttraction(123);
        await comp.$nextTick();

        // THEN
        expect(comp.attraction).toBe(foundAttraction);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAttraction = { id: 123 };
        attractionServiceStub.find.resolves(foundAttraction);

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
