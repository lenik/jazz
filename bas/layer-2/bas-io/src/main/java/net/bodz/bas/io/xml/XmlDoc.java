package net.bodz.bas.io.xml;

import net.bodz.bas.io.ITreeOut;

public class XmlDoc {

    private final ITreeOut treeOut;
    private XmlOutputFormat outputFormat;

    public XmlDoc(ITreeOut treeOut, XmlOutputFormat outputFormat) {
        this.treeOut = treeOut;
        this.outputFormat = outputFormat;
    }

    public ITreeOut getTreeOut() {
        return treeOut;
    }

    public XmlOutputFormat getOutputFormat() {
        return outputFormat;
    }

    public IXmlOut newXmlOut() {
        return new RecXmlOut(this);
    }

}
