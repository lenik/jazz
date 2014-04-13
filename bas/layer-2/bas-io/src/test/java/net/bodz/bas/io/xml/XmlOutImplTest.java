package net.bodz.bas.io.xml;

import org.junit.Assert;

import net.bodz.bas.io.Stdio;

public class XmlOutImplTest
        extends Assert {

    public static void main(String[] args) {
        IXmlOut out = XmlOutImpl.from(Stdio.cout);
        out.tag("ver", "Text");
        out.startTag("html");
        {
            out.tag("h1", "Title").attr("color", "red").attr("font", "bi'g");
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
