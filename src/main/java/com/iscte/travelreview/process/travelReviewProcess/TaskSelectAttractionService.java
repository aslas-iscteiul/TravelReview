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
public class TaskSelectAttractionService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSelectAttractionMapper taskSelectAttractionMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskSelectAttractionService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSelectAttractionMapper taskSelectAttractionMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSelectAttractionMapper = taskSelectAttractionMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskSelectAttractionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSelectAttractionMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskSelectAttractionContextDTO taskSelectAttractionContext = new TaskSelectAttractionContextDTO();
        taskSelectAttractionContext.setTaskInstance(taskInstanceDTO);
        taskSelectAttractionContext.setTravelReviewProcess(travelReviewProcess);

        return taskSelectAttractionContext;
    }

    public TaskSelectAttractionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSelectAttractionContextDTO taskSelectAttractionContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getTravelDestination());
        travelReviewDTO.setAttractionPrice(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getAttractionPrice());
        travelReviewDTO.setAttraction(taskSelectAttractionContext.getTravelReviewProcess().getTravelReview().getAttraction());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskSelectAttractionContextDTO taskSelectAttractionContext) {
        save(taskSelectAttractionContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskSelectAttractionContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskSelectAttractionContext.getTaskInstance(), travelReviewProcess);
    }
}
