package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class HtmlOutTest {

    public static void main(String[] args) {
        ITreeOut treeOut = TreeOutImpl.from(Stdio.cout);
        HtmlOutputFormat outputFormat = new HtmlOutputFormat();
        outputFormat.newLineAfterStartTag = true;
        outputFormat.newLineBeforeEndTag = true;

        HtmlDoc doc = new HtmlDoc(treeOut, outputFormat);

        IHtmlOut out = doc.newHtmlOut();
        out = out.html();
        {
            out.h1().style("color: red").text("Title");
            out.begin("div").id("div1").text("Welcome");

            HtmlForm form = out.form();
            {
                form.verbatim("[CODE: a <simple> form]");
                form.input().id("button1").text("Click me!");
            }
        }
        out.close();
    }

}
