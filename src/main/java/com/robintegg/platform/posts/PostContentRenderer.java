package com.robintegg.platform.posts;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

public class PostContentRenderer {

    public String render(Post post) {

        Parser parser = Parser.builder().build();
        Node document = parser.parse(post.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document); // "<p>This is <em>Sparta</em></p>\n"

    }

}
