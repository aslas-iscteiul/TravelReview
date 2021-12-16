import axios from 'axios';
import { TaskRegisterFlightDataContext } from './task-register-flight-data.model';

const baseApiUrl = 'api/travel-review-process/task-register-flight-data';

export default class TaskRegisterFlightDataService {
  public loadContext(taskId: number): Promise<TaskRegisterFlightDataContext> {
    return new Promise<TaskRegisterFlightDataContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskRegisterFlightDataContext> {
    return new Promise<TaskRegisterFlightDataContext>((resolve, reject) => {
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

  public complete(taskRegisterFlightDataContext: TaskRegisterFlightDataContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskRegisterFlightDataContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
