import axios from 'axios';

import { ITravelReviewProcess } from '@/shared/model/travel-review-process.model';

const baseApiUrl = 'api/travel-review-processes';

export default class TravelReviewProcessService {
  public find(id: number): Promise<ITravelReviewProcess> {
    return new Promise<ITravelReviewProcess>((resolve, reject) => {
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

  public create(entity: ITravelReviewProcess): Promise<ITravelReviewProcess> {
    return new Promise<ITravelReviewProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
