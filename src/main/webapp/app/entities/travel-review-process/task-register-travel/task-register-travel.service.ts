import axios from 'axios';
import { TaskRegisterTravelContext } from './task-register-travel.model';

const baseApiUrl = 'api/travel-review-process/task-register-travel';

export default class TaskRegisterTravelService {
  public loadContext(taskId: number): Promise<TaskRegisterTravelContext> {
    return new Promise<TaskRegisterTravelContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskRegisterTravelContext> {
    return new Promise<TaskRegisterTravelContext>((resolve, reject) => {
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

  public complete(taskRegisterTravelContext: TaskRegisterTravelContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskRegisterTravelContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
