package com.sundy.service;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.sundy.common.util.MathUtils;
import com.sundy.common.util.StringUtils;
import com.sundy.common.util.TimeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 14:01:58
 * 描述：
 */
public class StringUtilTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilTest.class);

    @Test
    public void show(){
//        LOGGER.info(StringUtils.convertToPY("世界你好", PinyinFormat.WITH_TONE_MARK));
//        System.out.println(TimeUtils.showTime(new Date().getTime()-95645412,new Date().getTime()));
        double add = MathUtils.div(25, 1.0001,11);
        double i1 = 25;
        double i2 = 1.0001;
        System.out.println(add);
        System.out.println(i1/i2);
    }
}
