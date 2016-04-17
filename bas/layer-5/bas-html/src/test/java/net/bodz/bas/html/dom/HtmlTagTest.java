package net.bodz.bas.html.dom;

import org.junit.Assert;

import net.bodz.bas.html.dom.tag.MutableForm;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.xml.dom.XmlFormatter;

public class HtmlTagTest
        extends Assert {

    public static void main(String[] args) {
        MutableHtmlDoc doc = new MutableHtmlDoc();
        IHtmlTag out = doc;
        out.insert("ver").text("Text");
        out = out.html();
        {
            out.h1().style("color: red").text("Title");
            out.insert("div").id("div1").text("Welcome");

            MutableForm form = out.form();
            {
                form.println("a <simple> form");
                form.input().id("button1").text("Click me!");
            }
        }
        new XmlFormatter(Stdio.cout).format(doc);
    }

}
