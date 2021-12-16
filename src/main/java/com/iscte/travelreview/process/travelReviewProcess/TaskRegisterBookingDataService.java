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
public class TaskRegisterBookingDataService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRegisterBookingDataMapper taskRegisterBookingDataMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskRegisterBookingDataService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRegisterBookingDataMapper taskRegisterBookingDataMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRegisterBookingDataMapper = taskRegisterBookingDataMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskRegisterBookingDataContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRegisterBookingDataMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext = new TaskRegisterBookingDataContextDTO();
        taskRegisterBookingDataContext.setTaskInstance(taskInstanceDTO);
        taskRegisterBookingDataContext.setTravelReviewProcess(travelReviewProcess);

        return taskRegisterBookingDataContext;
    }

    public TaskRegisterBookingDataContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(
            taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getTravelDestination()
        );
        travelReviewDTO.setAccommodationBookingNumber(
            taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getAccommodationBookingNumber()
        );
        travelReviewDTO.setAccommodationBookingPrice(
            taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getAccommodationBookingPrice()
        );
        travelReviewDTO.setAccommodation(taskRegisterBookingDataContext.getTravelReviewProcess().getTravelReview().getAccommodation());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskRegisterBookingDataContextDTO taskRegisterBookingDataContext) {
        save(taskRegisterBookingDataContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskRegisterBookingDataContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRegisterBookingDataContext.getTaskInstance(), travelReviewProcess);
    }
}
