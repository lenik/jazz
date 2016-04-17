package net.bodz.bas.html.io;

import java.io.Writer;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public class HtmlDoc
        extends RecHtmlOut<HtmlDoc> {

    public ITreeOut treeOut;
    public HtmlOutputFormat outputFormat;

    public HtmlDoc(Writer writer, HtmlOutputFormat outputFormat) {
        this(TreeOutImpl.from(new WriterPrintOut(writer)), outputFormat);
    }

    public HtmlDoc(ITreeOut treeOut, HtmlOutputFormat outputFormat) {
        super(outputFormat);
        this.doc = this;
        this.treeOut = treeOut;
        this.outputFormat = outputFormat;
    }

}
