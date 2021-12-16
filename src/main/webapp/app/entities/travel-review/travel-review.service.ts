import axios from 'axios';

import { ITravelReview } from '@/shared/model/travel-review.model';

const baseApiUrl = 'api/travel-reviews';

export default class TravelReviewService {
  public find(id: number): Promise<ITravelReview> {
    return new Promise<ITravelReview>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
