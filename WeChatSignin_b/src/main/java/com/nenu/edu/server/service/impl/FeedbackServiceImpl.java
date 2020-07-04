package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.mapper.FeedbackMapper;
import com.nenu.edu.server.po.Feedback;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.FeedbackService;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.web.request.FeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author i@xiaofeig.cn
 * @date 下午3:33 2018/5/16
 */
@Service
public class FeedbackServiceImpl extends BaseLogService implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    protected Class getType() {
        return FeedbackServiceImpl.class;
    }

    @Override
    public Feedback create(FeedbackRequest request, User user) {
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setContent(request.getContent());
        DaoUtil.checkSingleRecordAccess(
                feedbackMapper.insert(feedback)
        );
        return feedback;
    }
}
