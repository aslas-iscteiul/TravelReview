import axios from 'axios';
import { TaskSelectAttractionContext } from './task-select-attraction.model';

const baseApiUrl = 'api/travel-review-process/task-select-attraction';

export default class TaskSelectAttractionService {
  public loadContext(taskId: number): Promise<TaskSelectAttractionContext> {
    return new Promise<TaskSelectAttractionContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskSelectAttractionContext> {
    return new Promise<TaskSelectAttractionContext>((resolve, reject) => {
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

  public complete(taskSelectAttractionContext: TaskSelectAttractionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskSelectAttractionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
