package net.bodz.bas.io.xml;

import java.util.Map;

import net.bodz.bas.io.impl.NullTreeOut;

public class NullXmlOut
        extends NullTreeOut
        implements IXmlOut {

    @Override
    public void processInstruction(String target, String data) {
    }

    @Override
    public void comment(String str) {
    }

    @Override
    public IXmlTagBuilder tag(String name) {
        return IXmlTagBuilder.NULL;
    }

    @Override
    public IXmlTagBuilder tag(String name, String text) {
        return IXmlTagBuilder.NULL;
    }

    @Override
    public IXmlTagBuilder tag(String name, Map<String, ?> attributes, String text) {
        return IXmlTagBuilder.NULL;
    }

    @Override
    public IXmlTagBuilder start(String name) {
        return IXmlTagBuilder.NULL;
    }

    @Override
    public IXmlTagBuilder start(String name, Map<String, ?> attributes) {
        return IXmlTagBuilder.NULL;
    }

    @Override
    public void end() {
    }

    @Override
    public void endAll() {
    }

    @Override
    public void startTagBegin(String name) {
    }

    @Override
    public void attribute(String name, Object value) {
    }

    @Override
    public void startTagEnd(boolean empty) {
    }

    @Override
    public void text(String str) {
    }

    @Override
    public void textln(String str) {
    }

    @Override
    public void cdata(String cdata) {
    }

}
