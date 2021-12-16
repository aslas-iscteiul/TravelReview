package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-select-attraction")
public class TaskSelectAttractionController {

    private final Logger log = LoggerFactory.getLogger(TaskSelectAttractionController.class);

    private final TaskSelectAttractionService taskSelectAttractionService;

    public TaskSelectAttractionController(TaskSelectAttractionService taskSelectAttractionService) {
        this.taskSelectAttractionService = taskSelectAttractionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSelectAttractionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSelectAttractionContextDTO taskSelectAttractionContext = taskSelectAttractionService.loadContext(id);
        return ResponseEntity.ok(taskSelectAttractionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskSelectAttractionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSelectAttractionContextDTO taskSelectAttractionContext = taskSelectAttractionService.claim(id);
        return ResponseEntity.ok(taskSelectAttractionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskSelectAttractionContextDTO taskSelectAttractionContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskSelectAttraction {}",
            taskSelectAttractionContext.getTaskInstance().getId()
        );
        taskSelectAttractionService.complete(taskSelectAttractionContext);
        return ResponseEntity.noContent().build();
    }
}
