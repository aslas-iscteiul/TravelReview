package com.iscte.travelreview.process.travelReviewProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-review-process/task-register-travel")
public class TaskRegisterTravelController {

    private final Logger log = LoggerFactory.getLogger(TaskRegisterTravelController.class);

    private final TaskRegisterTravelService taskRegisterTravelService;

    public TaskRegisterTravelController(TaskRegisterTravelService taskRegisterTravelService) {
        this.taskRegisterTravelService = taskRegisterTravelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRegisterTravelContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterTravelContextDTO taskRegisterTravelContext = taskRegisterTravelService.loadContext(id);
        return ResponseEntity.ok(taskRegisterTravelContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRegisterTravelContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRegisterTravelContextDTO taskRegisterTravelContext = taskRegisterTravelService.claim(id);
        return ResponseEntity.ok(taskRegisterTravelContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRegisterTravelContextDTO taskRegisterTravelContext) {
        log.debug(
            "REST request to complete TravelReviewProcess.TaskRegisterTravel {}",
            taskRegisterTravelContext.getTaskInstance().getId()
        );
        taskRegisterTravelService.complete(taskRegisterTravelContext);
        return ResponseEntity.noContent().build();
    }
}
