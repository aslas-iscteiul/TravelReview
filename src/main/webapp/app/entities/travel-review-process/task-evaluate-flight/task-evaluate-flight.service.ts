import axios from 'axios';
import { TaskEvaluateFlightContext } from './task-evaluate-flight.model';

const baseApiUrl = 'api/travel-review-process/task-evaluate-flight';

export default class TaskEvaluateFlightService {
  public loadContext(taskId: number): Promise<TaskEvaluateFlightContext> {
    return new Promise<TaskEvaluateFlightContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskEvaluateFlightContext> {
    return new Promise<TaskEvaluateFlightContext>((resolve, reject) => {
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

  public complete(taskEvaluateFlightContext: TaskEvaluateFlightContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskEvaluateFlightContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
