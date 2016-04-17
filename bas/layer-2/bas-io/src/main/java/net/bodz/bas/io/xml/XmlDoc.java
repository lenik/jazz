package net.bodz.bas.io.xml;

import net.bodz.bas.io.ITreeOut;

public class XmlDoc
        extends RecXmlOut<XmlDoc> {

    public final ITreeOut treeOut;
    public final XmlOutputFormat outputFormat;

    public XmlDoc(ITreeOut treeOut, XmlOutputFormat outputFormat) {
        super(outputFormat);
        this.doc = this;
        this.treeOut = treeOut;
        this.outputFormat = outputFormat;
    }

}
