package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-evaluate-booking-data")
public class TaskEvaluateBookingDataController {

    private final Logger log = LoggerFactory.getLogger(TaskEvaluateBookingDataController.class);

    private final TaskEvaluateBookingDataService taskEvaluateBookingDataService;

    public TaskEvaluateBookingDataController(TaskEvaluateBookingDataService taskEvaluateBookingDataService) {
        this.taskEvaluateBookingDataService = taskEvaluateBookingDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEvaluateBookingDataContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext = taskEvaluateBookingDataService.loadContext(id);
        return ResponseEntity.ok(taskEvaluateBookingDataContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskEvaluateBookingDataContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext = taskEvaluateBookingDataService.claim(id);
        return ResponseEntity.ok(taskEvaluateBookingDataContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskEvaluateBookingData {}",
            taskEvaluateBookingDataContext.getTaskInstance().getId()
        );
        taskEvaluateBookingDataService.complete(taskEvaluateBookingDataContext);
        return ResponseEntity.noContent().build();
    }
}
