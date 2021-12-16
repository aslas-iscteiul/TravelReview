/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import TravelReviewService from '@/entities/travel-review/travel-review.service';
import { TravelReview } from '@/shared/model/travel-review.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('TravelReview Service', () => {
    let service: TravelReviewService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new TravelReviewService();
      currentDate = new Date();
      elemDefault = new TravelReview(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            travelStartDate: dayjs(currentDate).format(DATE_FORMAT),
            travelEndDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of TravelReview', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            description: 'BBBBBB',
            travelOrigin: 'BBBBBB',
            travelDestination: 'BBBBBB',
            travelWithFlight: true,
            travelStartDate: dayjs(currentDate).format(DATE_FORMAT),
            travelEndDate: dayjs(currentDate).format(DATE_FORMAT),
            flightTicketNumber: 'BBBBBB',
            flightClass: 'BBBBBB',
            flightDuration: 1,
            flightPrice: 1,
            flightReview: 'BBBBBB',
            accommodationBookingNumber: 'BBBBBB',
            accommodationBookingPrice: 1,
            accommodationBookingReview: 'BBBBBB',
            attractionPrice: 1,
            attractionReview: 'BBBBBB',
            travelCost: 1,
            userEmail: 'BBBBBB',
            userName: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            travelStartDate: currentDate,
            travelEndDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of TravelReview', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
