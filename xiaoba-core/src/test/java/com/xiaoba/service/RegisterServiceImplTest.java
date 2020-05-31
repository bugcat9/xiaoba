package com.xiaoba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


@SpringBootTest
public class RegisterServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    RegisterServiceImpl registerService;

    @Test
    public void test(){
        System.out.print("---");
    }


}