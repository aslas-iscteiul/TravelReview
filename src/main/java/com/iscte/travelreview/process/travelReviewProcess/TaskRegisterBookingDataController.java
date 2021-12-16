package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-register-booking-data")
public class TaskRegisterBookingDataController {

    private final Logger log = LoggerFactory.getLogger(TaskRegisterBookingDataController.class);

    private final TaskRegisterBookingDataService taskRegisterBookingDataService;

    public TaskRegisterBookingDataController(TaskRegisterBookingDataService taskRegisterBookingDataService) {
        this.taskRegisterBookingDataService = taskRegisterBookingDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRegisterBookingDataContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext = taskRegisterBookingDataService.loadContext(id);
        return ResponseEntity.ok(taskRegisterBookingDataContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRegisterBookingDataContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext = taskRegisterBookingDataService.claim(id);
        return ResponseEntity.ok(taskRegisterBookingDataContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskRegisterBookingData {}",
            taskRegisterBookingDataContext.getTaskInstance().getId()
        );
        taskRegisterBookingDataService.complete(taskRegisterBookingDataContext);
        return ResponseEntity.noContent().build();
    }
}
