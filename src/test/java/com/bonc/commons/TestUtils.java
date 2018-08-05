package com.bonc.commons;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestUtils {

    @Test
    public void test(){
        /**
         * String 工具类
         */
        boolean blank = StringUtils.isBlank(" ad ");
        System.out.println(blank);

        /**
         * md5摘要
         */
        String abc = DigestUtils.md5Hex("abc");
        System.out.println(abc);
    }
}
