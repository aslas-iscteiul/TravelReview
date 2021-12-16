package com.iscte.travelreview.delegate;

import com.iscte.travelreview.service.MailService;
import com.iscte.travelreview.service.dto.TravelReviewDTO;
import com.iscte.travelreview.service.dto.TravelReviewProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Component
public class EmailTravelReviewSummaryDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelReviewProcessDTO travelReviewProcess = (TravelReviewProcessDTO) delegateExecution.getVariable("processInstance");
        TravelReviewDTO travelReview = travelReviewProcess.getTravelReview();
        String to = travelReview.getUserEmail();
        String subject = "[AgileKip] Summary of your travel review " + travelReview.getTitle();
        Context context = new Context(Locale.getDefault());
        context.setVariable("travelReview", travelReview);
        String content = templateEngine.process("travelReviewProcess/travelReviewSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
