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
public class TaskRegisterFlightDataService {

    private final TaskInstanceService taskInstanceService;

    private final TravelReviewService travelReviewService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRegisterFlightDataMapper taskRegisterFlightDataMapper;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TaskRegisterFlightDataService(
        TaskInstanceService taskInstanceService,
        TravelReviewService travelReviewService,
        TaskInstanceRepository taskInstanceRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRegisterFlightDataMapper taskRegisterFlightDataMapper,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelReviewService = travelReviewService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRegisterFlightDataMapper = taskRegisterFlightDataMapper;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    public TaskRegisterFlightDataContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRegisterFlightDataMapper::toTravelReviewProcessDTO)
            .orElseThrow();

        TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext = new TaskRegisterFlightDataContextDTO();
        taskRegisterFlightDataContext.setTaskInstance(taskInstanceDTO);
        taskRegisterFlightDataContext.setTravelReviewProcess(travelReviewProcess);

        return taskRegisterFlightDataContext;
    }

    public TaskRegisterFlightDataContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext) {
        TravelReviewDTO travelReviewDTO = travelReviewService
            .findOne(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getId())
            .orElseThrow();
        travelReviewDTO.setTitle(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getTitle());
        travelReviewDTO.setDescription(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getDescription());
        travelReviewDTO.setTravelOrigin(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getTravelOrigin());
        travelReviewDTO.setTravelDestination(
            taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getTravelDestination()
        );
        travelReviewDTO.setFlightTicketNumber(
            taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getFlightTicketNumber()
        );
        travelReviewDTO.setFlightClass(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getFlightClass());
        travelReviewDTO.setFlightDuration(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getFlightDuration());
        travelReviewDTO.setFlightPrice(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getFlightPrice());
        travelReviewDTO.setAirlineCompany(taskRegisterFlightDataContext.getTravelReviewProcess().getTravelReview().getAirlineCompany());
        travelReviewService.save(travelReviewDTO);
    }

    public void complete(TaskRegisterFlightDataContextDTO taskRegisterFlightDataContext) {
        save(taskRegisterFlightDataContext);
        TravelReviewProcessDTO travelReviewProcess = travelReviewProcessRepository
            .findByProcessInstanceId(taskRegisterFlightDataContext.getTravelReviewProcess().getProcessInstance().getId())
            .map(travelReviewProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRegisterFlightDataContext.getTaskInstance(), travelReviewProcess);
    }
}
