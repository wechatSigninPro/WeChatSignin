package com.nenu.edu.server.service;

import com.nenu.edu.server.po.Call;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.vo.SignRecordVo;
import com.nenu.edu.server.web.request.CallRequest;

import java.util.List;

/**
 * 点名业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午4:38 2018/4/14
 */
public interface CallService {

    /**
     * 老师发起点名
     *
     * @param request
     * @param user
     * @return
     */
    Call create(CallRequest request, User user);

    /**
     * 老师删除点名
     *
     * @param request
     * @param user
     * @return
     */
    void delete(CallRequest request, User user);

    /**
     * 根据口令查找5分钟内、50米内的点名
     *
     * @param request
     * @return
     */
    List<Call> listByPasswordAndLocation(CallRequest request, User user);

    /**
     * 点名、签到列表
     *
     * @param user
     * @return
     */
    List<?> list(User user);

    /**
     * 点名、签到信息
     *
     * @param request
     * @param user
     * @return
     */
    Object view(CallRequest request, User user);

    /**
     * 搜索
     *
     * @param request
     * @param user
     * @return
     */
    List<?> search(CallRequest request, User user);

    /**
     * 根据课程查询
     *
     * @param request
     * @param user
     * @return
     */
    List<?> listByCourse(CallRequest request, User user);


    /**
     * 根据点名编号查询学生名单
     *
     * @param request
     * @param user
     * @return
     */
    List<SignRecordVo> listStudent(CallRequest request, User user);

    /**
     * 点名二维码
     *
     * @param request
     * @param user
     * @return
     */
    String qrCode(CallRequest request, User user);

    /**
     * 导出点名记录为excel文件
     *
     * @param user
     * @return
     */
    byte[] exportToExcel(User user);
}
