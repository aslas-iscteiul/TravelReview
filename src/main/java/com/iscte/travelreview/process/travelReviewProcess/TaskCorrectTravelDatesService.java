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
public class TaskCorrectTravelDatesService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskCorrectTravelDatesMapper taskCorrectTravelDatesMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskCorrectTravelDatesService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskCorrectTravelDatesMapper taskCorrectTravelDatesMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskCorrectTravelDatesMapper = taskCorrectTravelDatesMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskCorrectTravelDatesContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskCorrectTravelDatesMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext = new TaskCorrectTravelDatesContextDTO();
        taskCorrectTravelDatesContext.setTaskInstance(taskInstanceDTO);
        taskCorrectTravelDatesContext.setTravelReviewProcess(travelReviewProcess);

        return taskCorrectTravelDatesContext;
    }

    public TaskCorrectTravelDatesContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(
            taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getTravelDestination()
        );
        travelReviewDTO.setTravelStartDate(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getTravelStartDate());
        travelReviewDTO.setTravelEndDate(taskCorrectTravelDatesContext.getTravelReviewProcess().getTravelReview().getTravelEndDate());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskCorrectTravelDatesContextDTO taskCorrectTravelDatesContext) {
        save(taskCorrectTravelDatesContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskCorrectTravelDatesContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskCorrectTravelDatesContext.getTaskInstance(), travelReviewProcess);
    }
}
