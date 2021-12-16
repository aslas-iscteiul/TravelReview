package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-correct-travel-dates")
public class TaskCorrectTravelDatesController {

    private final Logger log = LoggerFactory.getLogger(TaskCorrectTravelDatesController.class);

    private final TaskCorrectTravelDatesService taskCorrectTravelDatesService;

    public TaskCorrectTravelDatesController(TaskCorrectTravelDatesService taskCorrectTravelDatesService) {
        this.taskCorrectTravelDatesService = taskCorrectTravelDatesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCorrectTravelDatesContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext = taskCorrectTravelDatesService.loadContext(id);
        return ResponseEntity.ok(taskCorrectTravelDatesContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskCorrectTravelDatesContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext = taskCorrectTravelDatesService.claim(id);
        return ResponseEntity.ok(taskCorrectTravelDatesContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskCorrectTravelDates {}",
            taskCorrectTravelDatesContext.getTaskInstance().getId()
        );
        taskCorrectTravelDatesService.complete(taskCorrectTravelDatesContext);
        return ResponseEntity.noContent().build();
    }
}
