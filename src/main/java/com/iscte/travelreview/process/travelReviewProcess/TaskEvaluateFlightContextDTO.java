package com.iscte.travelreview.process.travelReviewProcess;

import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class TaskEvaluateFlightContextDTO {

    private TravelReviewProcessDTO travelReviewProcess;
    private TaskInstanceDTO taskInstance;

    public TravelReviewProcessDTO getTravelReviewProcess() {
        return travelReviewProcess;
    }

    public void setTravelReviewProcess(TravelReviewProcessDTO travelReviewProcess) {
        this.travelReviewProcess = travelReviewProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
