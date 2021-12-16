package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-evaluate-attraction")
public class TaskEvaluateAttractionController {

    private final Logger log = LoggerFactory.getLogger(TaskEvaluateAttractionController.class);

    private final TaskEvaluateAttractionService taskEvaluateAttractionService;

    public TaskEvaluateAttractionController(TaskEvaluateAttractionService taskEvaluateAttractionService) {
        this.taskEvaluateAttractionService = taskEvaluateAttractionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEvaluateAttractionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext = taskEvaluateAttractionService.loadContext(id);
        return ResponseEntity.ok(taskEvaluateAttractionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskEvaluateAttractionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext = taskEvaluateAttractionService.claim(id);
        return ResponseEntity.ok(taskEvaluateAttractionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskEvaluateAttraction {}",
            taskEvaluateAttractionContext.getTaskInstance().getId()
        );
        taskEvaluateAttractionService.complete(taskEvaluateAttractionContext);
        return ResponseEntity.noContent().build();
    }
}
