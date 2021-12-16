package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-register-flight-data")
public class TaskRegisterFlightDataController {

    private final Logger log = LoggerFactory.getLogger(TaskRegisterFlightDataController.class);

    private final TaskRegisterFlightDataService taskRegisterFlightDataService;

    public TaskRegisterFlightDataController(TaskRegisterFlightDataService taskRegisterFlightDataService) {
        this.taskRegisterFlightDataService = taskRegisterFlightDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRegisterFlightDataContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext = taskRegisterFlightDataService.loadContext(id);
        return ResponseEntity.ok(taskRegisterFlightDataContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRegisterFlightDataContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext = taskRegisterFlightDataService.claim(id);
        return ResponseEntity.ok(taskRegisterFlightDataContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskRegisterFlightData {}",
            taskRegisterFlightDataContext.getTaskInstance().getId()
        );
        taskRegisterFlightDataService.complete(taskRegisterFlightDataContext);
        return ResponseEntity.noContent().build();
    }
}
