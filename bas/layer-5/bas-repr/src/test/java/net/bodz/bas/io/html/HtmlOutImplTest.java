package net.bodz.bas.io.html;

import org.junit.Assert;

import net.bodz.bas.io.Stdio;

public class HtmlOutImplTest
        extends Assert {

    public static void main(String[] args) {
        IHtmlOut out = HtmlOutImpl.from(Stdio.cout);
        out.tag("ver", "Text");
        out.startTag("html");
        {
            out.h1().style("color: red").text("Title");
            out.tag("div", "Welcome").id("div1");

            out.startTag("form");
            {
                out.textln("a <simple> form");
                out.startTag("input");
                out.text("Click me!");
                out.endTag();
            }
            out.endTag();
        }
        out.endTag();
    }

}
