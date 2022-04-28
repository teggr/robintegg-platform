package com.robintegg.platform.posts;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class PostContentRenderer {

    public String render(Post post) {

        Parser parser = Parser.builder().build();
        Node document = parser.parse(post.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document); // "<p>This is <em>Sparta</em></p>\n"

    }

}
