package com.monkeyhorse.blog.utils;

import com.monkeyhorse.blog.support.web.MarkdownService;
import com.monkeyhorse.blog.support.web.PegDownMarkdownService;

/**
 * A Markdown processing util class
 *
 *  @author uoor<i@monkeyhorse.cn thx raysmood>.
 */
public class Markdown {

    private static final MarkdownService MARKDOWN_SERVICE = new PegDownMarkdownService();

    public static String markdownToHtml(String content){
        return MARKDOWN_SERVICE.renderToHtml(content);
    }
}
