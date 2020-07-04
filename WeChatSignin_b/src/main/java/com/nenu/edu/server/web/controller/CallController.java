package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.po.Call;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.CallService;
import com.nenu.edu.server.vo.SignRecordVo;
import com.nenu.edu.server.web.request.CallRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ying Guoqing
 * @Description:点名控制层
 */
@RestController
@RequestMapping("/api/call")
public class CallController extends BaseLogService {

    @Autowired
    private CallService callService;

    @Override
    protected Class getType() {
        return CallController.class;
    }

    @PostMapping("/create")
    public ResponseWrapper<Call> create(@RequestBody @Validated(value = CallRequest.Create.class) CallRequest request, @CurrentUser User user) {
        Call call = callService.create(request, user);

        ResponseWrapper<Call> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(call);

        return responseWrapper;
    }

    @PostMapping("/delete")
    public ResponseWrapper<Call> delete(@RequestBody @Validated(value = CallRequest.Delete.class) CallRequest request, @CurrentUser User user) {
        callService.delete(request, user);

        ResponseWrapper<Call> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(null);

        return responseWrapper;
    }

    @PostMapping("/list/password")
    public ResponseWrapper<List<Call>> listByPasswordAndLocation(@RequestBody @Validated(value = CallRequest.ListByPasswordAndLocation.class) CallRequest request, @CurrentUser User user) {
        List<Call> calls = callService.listByPasswordAndLocation(request, user);

        ResponseWrapper<List<Call>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(calls);

        return responseWrapper;
    }

    @GetMapping("/list")
    public ResponseWrapper<List<?>> list(@CurrentUser User user) {
        List<?> calls = callService.list(user);

        ResponseWrapper<List<?>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(calls);

        return responseWrapper;
    }

    @PostMapping("/view")
    public ResponseWrapper<Object> view(@RequestBody @Validated(value = CallRequest.View.class) CallRequest request, @CurrentUser User user) {
        Object call = callService.view(request, user);

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(call);

        return responseWrapper;
    }

    @PostMapping("/search")
    public ResponseWrapper<List<?>> search(@RequestBody @Validated(value = CallRequest.Search.class) CallRequest request, @CurrentUser User user) {
        List<?> calls = callService.search(request, user);

        ResponseWrapper<List<?>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(calls);

        return responseWrapper;
    }

    @PostMapping("/list/course")
    public ResponseWrapper<List<?>> listByCourse(@RequestBody @Validated(value = CallRequest.ListByCourse.class) CallRequest request, @CurrentUser User user) {
        List<?> calls = callService.listByCourse(request, user);

        ResponseWrapper<List<?>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(calls);

        return responseWrapper;
    }

    @PostMapping("/list/student")
    public ResponseWrapper<List<SignRecordVo>> listRecord(@RequestBody @Validated(value = CallRequest.ListStudent.class) CallRequest request, @CurrentUser User user) {
        List<SignRecordVo> signRecordVos = callService.listStudent(request, user);

        ResponseWrapper<List<SignRecordVo>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(signRecordVos);

        return responseWrapper;
    }

    /**
     * 点名二维码
     *
     * @return
     */
    @PostMapping(value = "/qrcode")
    public ResponseWrapper<byte[]> qRCode(@RequestBody @Validated(value = CallRequest.QrCode.class) CallRequest request, @CurrentUser User user) {
        String content = callService.qrCode(request, user);
        byte[] imageData = QRCode.from(content).withSize(500, 500).to(ImageType.PNG).stream().toByteArray();

        ResponseWrapper<byte[]> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(imageData);

        return responseWrapper;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@CurrentUser User user) throws UnsupportedEncodingException {
        byte[] body = callService.exportToExcel(user);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String name = "签到记录（" + dateFormat.format(new Date()) + "）.xlsx";
        String fileName = new String(name.getBytes("utf-8"), "iso-8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }

}