package com.xiaoba.util;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profile.pegdown.Extensions;
import com.vladsch.flexmark.profile.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import lombok.var;

/**
 * Markdown工具
 *
 * @author acgist
 */
public class MarkdownUtils {


    public static void main(String[] args) {
        String html=MarkdownUtils.toHTML("E:\\Users\\Test\\md\\index.md");
    }
    /**
     * 本站域名
     */
    private static final String SITE_HOST = "acgist.com";

    static class LinkRefExtension implements HtmlRenderer.HtmlRendererExtension {

        @Override
        public void rendererOptions(MutableDataHolder holder) {
        }

        @Override
        public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
            rendererBuilder.attributeProviderFactory(LinkRefAttributeProvider.Factory());
        }

        static LinkRefExtension create() {
            return new LinkRefExtension();
        }

    }

    static class LinkRefAttributeProvider implements AttributeProvider {

        @Override
        public void setAttributes(Node node, AttributablePart part, Attributes attributes) {
            if ((node instanceof Link || node instanceof AutoLink) && part == AttributablePart.LINK) {
                // 如果非本站地址添加：ref="nofollow"
                final var href = (var) attributes.get("href");
                if(href != null && ((Attribute) href).getValue() != null && !((Attribute) href).getValue().contains(SITE_HOST)) {
                    attributes.replaceValue("rel", "nofollow");
                }
            }
        }

        static AttributeProviderFactory Factory() {
            return new IndependentAttributeProviderFactory() {
                @Override
                public AttributeProvider apply(LinkResolverContext context) {
                    return new LinkRefAttributeProvider();
                }
            };
        }

    }

    /**
     * <p>Markdown转HTML</p>
     * <p>去掉链接自动转换</p>
     */
    public static final String toHTML(String markdown) {
        final DataHolder holder = PegdownOptionsAdapter.flexmarkOptions(true, Extensions.ALL ^ Extensions.AUTOLINKS, LinkRefExtension.create());
        final Parser parser = Parser.builder(holder).build();
        final Node document = parser.parse(markdown);
        final HtmlRenderer renderer = HtmlRenderer.builder(holder).build();
        return renderer.render(document);
    }

}