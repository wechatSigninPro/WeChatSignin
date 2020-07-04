package com.nenu.edu.server.service;

import com.nenu.edu.server.po.Sign;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.request.SignRequest;

/**
 * 签到业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午5:07 2018/4/14
 */
public interface SignService {

    /**
     * 学生签到
     *
     * @param signRequest
     * @param user
     * @return
     */
    Sign create(SignRequest signRequest, User user);

    /**
     * 学生二维码签到
     *
     * @param signRequest
     * @param user
     * @return
     */
    Sign qrcode(SignRequest signRequest, User user);

}
