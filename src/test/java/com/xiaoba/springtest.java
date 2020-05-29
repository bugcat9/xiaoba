package com.xiaoba;

import com.xiaoba.entity.MarkdownEntity;
import com.xiaoba.wrapper.MarkDown2HtmlWrapper;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = springtest.class)


public class springtest {
    @Test
    public void test(){
        System.out.println("========================================");
    }

    @Test
    public void markdown2html() throws IOException {
        String file = "E:\\Users\\Test\\md\\index.md";
        MarkdownEntity html = MarkDown2HtmlWrapper.ofFile(file);
        System.out.println(html.toString());
    }
}
