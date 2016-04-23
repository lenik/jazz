package net.bodz.bas.io.xml;

public class RecXmlOut
        extends AbstractRecXmlOut<RecXmlOut, RecXmlOut> {

    public RecXmlOut(XmlDoc doc) {
        super(doc);
    }

    @Override
    public RecXmlOut begin(String name) {
        return begin(name, new RecXmlOut(doc));
    }

}
