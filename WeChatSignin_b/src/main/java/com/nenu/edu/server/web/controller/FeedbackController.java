package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.po.Feedback;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.FeedbackService;
import com.nenu.edu.server.web.request.FeedbackRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ying Guoqing
 * @Description:反馈控制层
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController extends BaseLogService {

    @Autowired
    private FeedbackService feedbackService;

    @Override
    protected Class getType() {
        return FeedbackController.class;
    }

    @PostMapping("/create")
    public ResponseWrapper<Feedback> create(@RequestBody @Validated(value = FeedbackRequest.Create.class) FeedbackRequest request, @CurrentUser User user) {
        Feedback feedback = feedbackService.create(request, user);

        ResponseWrapper<Feedback> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(feedback);

        return responseWrapper;
    }

}
