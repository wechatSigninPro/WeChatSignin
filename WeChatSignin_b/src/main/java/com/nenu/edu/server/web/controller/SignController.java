package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.po.Sign;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.SignService;
import com.nenu.edu.server.web.request.SignRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Ying Guoqing
 * @Description:签到控制层
 */
@RestController
@RequestMapping("/api/sign")
public class SignController extends BaseLogService {

    @Autowired
    private SignService signService;

    @Override
    protected Class getType() {
        return SignController.class;
    }

    @PostMapping("/create")
    public ResponseWrapper<Sign> create(@RequestBody @Validated(value = SignRequest.Create.class) SignRequest request, @CurrentUser User user) {
        Sign sign = signService.create(request, user);

        ResponseWrapper<Sign> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(sign);

        return responseWrapper;
    }

    @PostMapping("/qrcode")
    public ResponseWrapper<Sign> qrcode(@RequestBody @Validated(value = SignRequest.QrCode.class) SignRequest request, @CurrentUser User user) {
        Sign sign = signService.qrcode(request, user);

        ResponseWrapper<Sign> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(sign);

        return responseWrapper;
    }

}