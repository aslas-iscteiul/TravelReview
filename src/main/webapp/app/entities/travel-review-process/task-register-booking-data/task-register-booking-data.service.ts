import axios from 'axios';
import { TaskRegisterBookingDataContext } from './task-register-booking-data.model';

const baseApiUrl = 'api/travel-review-process/task-register-booking-data';

export default class TaskRegisterBookingDataService {
  public loadContext(taskId: number): Promise<TaskRegisterBookingDataContext> {
    return new Promise<TaskRegisterBookingDataContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskRegisterBookingDataContext> {
    return new Promise<TaskRegisterBookingDataContext>((resolve, reject) => {
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

  public complete(taskRegisterBookingDataContext: TaskRegisterBookingDataContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskRegisterBookingDataContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
