package com.xiaoba.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = EsTest.class)
public class EsTest extends AbstractTestNGSpringContextTests {

    @Test
    public void test(){
        System.out.print("----");
    }
}
