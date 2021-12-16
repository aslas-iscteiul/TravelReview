import axios from 'axios';
import { TaskEvaluateAttractionContext } from './task-evaluate-attraction.model';

const baseApiUrl = 'api/travel-review-process/task-evaluate-attraction';

export default class TaskEvaluateAttractionService {
  public loadContext(taskId: number): Promise<TaskEvaluateAttractionContext> {
    return new Promise<TaskEvaluateAttractionContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskEvaluateAttractionContext> {
    return new Promise<TaskEvaluateAttractionContext>((resolve, reject) => {
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

  public complete(taskEvaluateAttractionContext: TaskEvaluateAttractionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskEvaluateAttractionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
