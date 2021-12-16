package com.iscte.travelreview.process.travelReviewProcess;

import com.iscte.travelreview.repository.TravelReviewProcessRepository;
import com.iscte.travelreview.service.TravelReviewService;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import com.iscte.travelreview.service.mapper.TravelReviewProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskEvaluateAttractionService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskEvaluateAttractionMapper taskEvaluateAttractionMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskEvaluateAttractionService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskEvaluateAttractionMapper taskEvaluateAttractionMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskEvaluateAttractionMapper = taskEvaluateAttractionMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskEvaluateAttractionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskEvaluateAttractionMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext = new TaskEvaluateAttractionContextDTO();
        taskEvaluateAttractionContext.setTaskInstance(taskInstanceDTO);
        taskEvaluateAttractionContext.setTravelReviewProcess(travelReviewProcess);

        return taskEvaluateAttractionContext;
    }

    public TaskEvaluateAttractionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(
            taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getTravelDestination()
        );
        travelReviewDTO.setAttractionReview(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getAttractionReview());
        travelReviewDTO.setAttractionScore(taskEvaluateAttractionContext.getTravelReviewProcess().getTravelReview().getAttractionScore());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskEvaluateAttractionContextDTO taskEvaluateAttractionContext) {
        save(taskEvaluateAttractionContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskEvaluateAttractionContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskEvaluateAttractionContext.getTaskInstance(), travelReviewProcess);
    }
}
