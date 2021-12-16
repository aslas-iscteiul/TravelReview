import axios from 'axios';
import { TaskCorrectTravelDatesContext } from './task-correct-travel-dates.model';

const baseApiUrl = 'api/travel-review-process/task-correct-travel-dates';

export default class TaskCorrectTravelDatesService {
  public loadContext(taskId: number): Promise<TaskCorrectTravelDatesContext> {
    return new Promise<TaskCorrectTravelDatesContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskCorrectTravelDatesContext> {
    return new Promise<TaskCorrectTravelDatesContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskCorrectTravelDatesContext: TaskCorrectTravelDatesContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskCorrectTravelDatesContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
