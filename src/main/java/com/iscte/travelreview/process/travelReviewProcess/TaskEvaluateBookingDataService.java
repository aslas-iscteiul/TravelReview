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
public class TaskEvaluateBookingDataService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskEvaluateBookingDataMapper taskEvaluateBookingDataMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskEvaluateBookingDataService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskEvaluateBookingDataMapper taskEvaluateBookingDataMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskEvaluateBookingDataMapper = taskEvaluateBookingDataMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskEvaluateBookingDataContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskEvaluateBookingDataMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext = new TaskEvaluateBookingDataContextDTO();
        taskEvaluateBookingDataContext.setTaskInstance(taskInstanceDTO);
        taskEvaluateBookingDataContext.setTravelReviewProcess(travelReviewProcess);

        return taskEvaluateBookingDataContext;
    }

    public TaskEvaluateBookingDataContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(
            taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getTravelDestination()
        );
        travelReviewDTO.setAccommodationBookingNumber(
            taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getAccommodationBookingNumber()
        );
        travelReviewDTO.setAccommodationBookingReview(
            taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getAccommodationBookingReview()
        );
        travelReviewDTO.setBookingScore(taskEvaluateBookingDataContext.getTravelReviewProcess().getTravelReview().getBookingScore());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskEvaluateBookingDataContextDTO taskEvaluateBookingDataContext) {
        save(taskEvaluateBookingDataContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskEvaluateBookingDataContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskEvaluateBookingDataContext.getTaskInstance(), travelReviewProcess);
    }
}
