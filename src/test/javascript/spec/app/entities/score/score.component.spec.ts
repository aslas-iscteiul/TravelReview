/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ScoreComponent from '@/entities/score/score.vue';
import ScoreClass from '@/entities/score/score.component';
import ScoreService from '@/entities/score/score.service';

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
  describe('Score Management Component', () => {
    let wrapper: Wrapper<ScoreClass>;
    let comp: ScoreClass;
    let scoreServiceStub: SinonStubbedInstance<ScoreService>;

    beforeEach(() => {
      scoreServiceStub = sinon.createStubInstance<ScoreService>(ScoreService);
      scoreServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ScoreClass>(ScoreComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          scoreService: () => scoreServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      scoreServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllScores();
      await comp.$nextTick();

      // THEN
      expect(scoreServiceStub.retrieve.called).toBeTruthy();
      expect(comp.scores[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      scoreServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeScore();
      await comp.$nextTick();

      // THEN
      expect(scoreServiceStub.delete.called).toBeTruthy();
      expect(scoreServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
