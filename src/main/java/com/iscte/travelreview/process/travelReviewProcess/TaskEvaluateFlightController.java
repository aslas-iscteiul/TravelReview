package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-evaluate-flight")
public class TaskEvaluateFlightController {

    private final Logger log = LoggerFactory.getLogger(TaskEvaluateFlightController.class);

    private final TaskEvaluateFlightService taskEvaluateFlightService;

    public TaskEvaluateFlightController(TaskEvaluateFlightService taskEvaluateFlightService) {
        this.taskEvaluateFlightService = taskEvaluateFlightService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEvaluateFlightContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateFlightContextDTO taskEvaluateFlightContext = taskEvaluateFlightService.loadContext(id);
        return ResponseEntity.ok(taskEvaluateFlightContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskEvaluateFlightContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateFlightContextDTO taskEvaluateFlightContext = taskEvaluateFlightService.claim(id);
        return ResponseEntity.ok(taskEvaluateFlightContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskEvaluateFlightContextDTO taskEvaluateFlightContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskEvaluateFlight {}",
            taskEvaluateFlightContext.getTaskInstance().getId()
        );
        taskEvaluateFlightService.complete(taskEvaluateFlightContext);
        return ResponseEntity.noContent().build();
    }
}
