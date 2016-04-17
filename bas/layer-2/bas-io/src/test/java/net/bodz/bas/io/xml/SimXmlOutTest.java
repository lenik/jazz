package net.bodz.bas.io.xml;

import org.junit.Assert;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class SimXmlOutTest
        extends Assert {

    public static void main(String[] args) {
        ITreeOut treeOut = TreeOutImpl.from(Stdio.cout);

        XmlOutputFormat format = new XmlOutputFormat();
        format.shortEmptyElement = true;
        format.sortAttributeNames = true;

        IXmlOut out = new XmlDoc(treeOut, format);
        out.begin("ver").text("1.0").end();
        IXmlOut html = out.begin("html");
        {
            html.begin("h1").attr("font", "bi'g").attr("color", "red").text("Title").end();
            html.begin("div").id("div1").text("Welcome").end();

            IXmlOut form = html.begin("form");
            {
                form.textln("a <simple> form");
                form.begin("input").end();
                form.text("Click me!");
                form.end();
            }
            html.end();
        }
        out.close();
    }

}
