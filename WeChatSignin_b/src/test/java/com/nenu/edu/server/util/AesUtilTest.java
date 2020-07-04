package com.nenu.edu.server.util;

import com.nenu.edu.server.BaseTest;
import org.junit.Test;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:30 2020/7/4
 */
public class AesUtilTest extends BaseTest {

    @Override
    protected Class getTargetClass() {
        return AesUtilTest.class;
    }

    @Test
    public void decrypt() {
        String s = AesUtil.decrypt("oWYQm0XTaeTCysez1kQwKHcHEhqU", "RQrIPNLFDpx+slPTcEBjSg2==");
        log.info("result: " + s);
    }

}
