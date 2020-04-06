package com.rox.vxsale.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author roxBear
 * @creat 2020/4/5
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KeyUtilTest {

    @Test
    public void getUniqueKey() {
        System.out.println(KeyUtil.getUniqueKey());
    }
}