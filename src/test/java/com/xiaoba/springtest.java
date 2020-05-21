package com.xiaoba;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = springtest.class)


public class springtest {
    @Test
    public void test(){
        System.out.println("========================================");
    }
}
