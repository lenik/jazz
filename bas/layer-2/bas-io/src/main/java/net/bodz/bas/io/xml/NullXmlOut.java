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
    public void tag(String name, String text) {
    }

    @Override
    public void tag(String name, Map<String, ?> attributes, String text) {
    }

    @Override
    public void startTag(String name) {
    }

    @Override
    public void startTag(String name, Map<String, ?> attributes) {
    }

    @Override
    public void endTag() {
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
