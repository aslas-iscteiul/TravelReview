import axios from 'axios';
import { TaskEvaluateBookingDataContext } from './task-evaluate-booking-data.model';

const baseApiUrl = 'api/travel-review-process/task-evaluate-booking-data';

export default class TaskEvaluateBookingDataService {
  public loadContext(taskId: number): Promise<TaskEvaluateBookingDataContext> {
    return new Promise<TaskEvaluateBookingDataContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskEvaluateBookingDataContext> {
    return new Promise<TaskEvaluateBookingDataContext>((resolve, reject) => {
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

  public complete(taskEvaluateBookingDataContext: TaskEvaluateBookingDataContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskEvaluateBookingDataContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
