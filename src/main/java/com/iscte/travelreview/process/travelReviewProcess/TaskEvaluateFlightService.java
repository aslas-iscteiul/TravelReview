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
public class TaskEvaluateFlightService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskEvaluateFlightMapper taskEvaluateFlightMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskEvaluateFlightService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskEvaluateFlightMapper taskEvaluateFlightMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskEvaluateFlightMapper = taskEvaluateFlightMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskEvaluateFlightContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskEvaluateFlightMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskEvaluateFlightContextDTO taskEvaluateFlightContext = new TaskEvaluateFlightContextDTO();
        taskEvaluateFlightContext.setTaskInstance(taskInstanceDTO);
        taskEvaluateFlightContext.setTravelReviewProcess(travelReviewProcess);

        return taskEvaluateFlightContext;
    }

    public TaskEvaluateFlightContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskEvaluateFlightContextDTO taskEvaluateFlightContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getTravelDestination());
        travelReviewDTO.setFlightTicketNumber(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getFlightTicketNumber());
        travelReviewDTO.setFlightReview(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getFlightReview());
        travelReviewDTO.setFlightScore(taskEvaluateFlightContext.getTravelReviewProcess().getTravelReview().getFlightScore());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskEvaluateFlightContextDTO taskEvaluateFlightContext) {
        save(taskEvaluateFlightContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskEvaluateFlightContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskEvaluateFlightContext.getTaskInstance(), travelReviewProcess);
    }
}
