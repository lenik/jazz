package net.bodz.bas.html.io;

import java.io.Writer;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.xml.XmlDoc;

public class HtmlDoc
        extends XmlDoc {

    public HtmlDoc(Writer writer, HtmlOutputFormat outputFormat) {
        this(TreeOutImpl.from(new WriterPrintOut(writer)), outputFormat);
    }

    public HtmlDoc(ITreeOut treeOut, HtmlOutputFormat outputFormat) {
        super(treeOut, outputFormat);
    }

    @Override
    public HtmlOutputFormat getOutputFormat() {
        return (HtmlOutputFormat) super.getOutputFormat();
    }

    /**
     * @see #newXmlOut()
     */
    public RecHtmlOut newHtmlOut() {
        RecHtmlOut htmlOut = new RecHtmlOut(this);
        return htmlOut;
    }

}
