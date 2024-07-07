package com.lsj.weblog.admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class RadndUtilTest {

    @Test
    public void testRadnd() {
        System.out.println(RandomStringUtils.randomAlphabetic(20));
    }


}
