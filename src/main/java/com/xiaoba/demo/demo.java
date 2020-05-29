package com.xiaoba.demo;

import com.ibm.icu.impl.locale.XCldrStub;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class demo {


    public static void main(String[] args) throws UnsupportedEncodingException {
        demo d=new demo();
        d.mdToHtm();
    }
    public void mdToHtm() throws UnsupportedEncodingException {
        // 从文件中读取markdown内容
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("D:\\Users\\IDEA_\\quick-media\\plugins\\markdown-plugin\\src\\test\\resources\\md\\test.md");

        if(stream==null){
            System.out.print("+++++++++++++++++++");
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));

        List<String> list = reader.lines().collect(Collectors.toList());
        String content = XCldrStub.Joiner.on("\n").join(list);



// markdown to image
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(content);
        String html = renderer.render(document);

        System.out.print(html);
    }
}
