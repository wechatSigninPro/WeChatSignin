package com.nenu.edu.server.service;

import com.nenu.edu.server.po.Feedback;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.request.FeedbackRequest;

/**
 * 反馈业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午3:31 2018/5/16
 */
public interface FeedbackService {

    /**
     * 新增反馈
     *
     * @param request
     * @param user
     * @return
     */
    Feedback create(FeedbackRequest request, User user);
}
