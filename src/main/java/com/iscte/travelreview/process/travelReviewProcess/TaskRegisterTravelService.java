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
public class TaskRegisterTravelService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRegisterTravelMapper taskRegisterTravelMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskRegisterTravelService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRegisterTravelMapper taskRegisterTravelMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRegisterTravelMapper = taskRegisterTravelMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskRegisterTravelContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRegisterTravelMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskRegisterTravelContextDTO taskRegisterTravelContext = new TaskRegisterTravelContextDTO();
        taskRegisterTravelContext.setTaskInstance(taskInstanceDTO);
        taskRegisterTravelContext.setTravelReviewProcess(travelReviewProcess);

        return taskRegisterTravelContext;
    }

    public TaskRegisterTravelContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRegisterTravelContextDTO taskRegisterTravelContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTravelDestination());
        travelReviewDTO.setTravelWithFlight(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTravelWithFlight());
        travelReviewDTO.setTravelStartDate(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTravelStartDate());
        travelReviewDTO.setTravelEndDate(taskRegisterTravelContext.getTravelReviewProcess().getTravelReview().getTravelEndDate());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskRegisterTravelContextDTO taskRegisterTravelContext) {
        save(taskRegisterTravelContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskRegisterTravelContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRegisterTravelContext.getTaskInstance(), travelReviewProcess);
    }
}
