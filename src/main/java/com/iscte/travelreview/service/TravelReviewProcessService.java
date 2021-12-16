package com.iscte.travelreview.service;

import com.iscte.travelreview.domain.TravelReviewProcess;
import com.iscte.travelreview.repository.TravelReviewProcessRepository;
import com.iscte.travelreview.repository.TravelReviewRepository;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import com.iscte.travelreview.service.mapper.TravelReviewProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TravelReviewProcess}.
 */
@Service
@Transactional
public class TravelReviewProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "TravelReviewProcess";

    private final Logger log = LoggerFactory.getLogger(TravelReviewProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final TravelReviewRepository travelReviewRepository;

    private final TravelReviewProcessRepository travelReviewProcessRepository;

    private final TravelReviewProcessMapper travelReviewProcessMapper;

    public TravelReviewProcessService(
        ProcessInstanceService processInstanceService,
        TravelReviewRepository travelReviewRepository,
        TravelReviewProcessRepository travelReviewProcessRepository,
        TravelReviewProcessMapper travelReviewProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.travelReviewRepository = travelReviewRepository;
        this.travelReviewProcessRepository = travelReviewProcessRepository;
        this.travelReviewProcessMapper = travelReviewProcessMapper;
    }

    /**
     * Save a travelReviewProcess.
     *
     * @param travelReviewProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public TravelReviewProcessDTO create(TravelReviewProcessDTO travelReviewProcessDTO) {
        log.debug("Request to save TravelReviewProcess : {}", travelReviewProcessDTO);

        TravelReviewProcess travelReviewProcess = travelReviewProcessMapper.toEntity(travelReviewProcessDTO);

        //Saving the domainEntity
        travelReviewRepository.save(travelReviewProcess.getTravelReview());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "TravelReview#" + travelReviewProcess.getTravelReview().getId(),
            travelReviewProcess
        );
        travelReviewProcess.setProcessInstance(processInstance);

        //Saving the process entity
        travelReviewProcess = travelReviewProcessRepository.save(travelReviewProcess);
        return travelReviewProcessMapper.toDto(travelReviewProcess);
    }

    /**
     * Get all the travelReviewProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TravelReviewProcessDTO> findAll() {
        log.debug("Request to get all TravelReviewProcesss");
        return travelReviewProcessRepository
            .findAll()
            .stream()
            .map(travelReviewProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one travelReviewProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelReviewProcessDTO> findOne(Long id) {
        log.debug("Request to get TravelReviewProcess : {}", id);
        return travelReviewProcessRepository.findById(id).map(travelReviewProcessMapper::toDto);
    }

    /**
     * Get one travelReviewProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelReviewProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get TravelReviewProcess by  processInstanceId: {}", processInstanceId);
        return travelReviewProcessRepository.findByProcessInstanceId(processInstanceId).map(travelReviewProcessMapper::toDto);
    }
}
