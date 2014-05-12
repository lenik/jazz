package net.bodz.bas.i18n.dom;

import net.bodz.bas.c.string.SimpleJavaStringDFA;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public abstract class MultiTagStringParser
        extends SimpleJavaStringDFA {

    private StringBuilder tagBuffer = new StringBuilder();
    private boolean tagBoundary;

    @Override
    protected void reset() {
        super.reset();
        tagBuffer.setLength(0);
        tagBoundary = false;
    }

    /**
     * Example: <code>"for default lang" xxx "for xxx" "concat..." ...</code>
     *
     * @return Not used, it should be overrided in the subclass.
     */
    @Override
    public synchronized Object parse(String s)
            throws ParseException {
        return super.parse(s);
    }

    @Override
    protected void receive(char ch)
            throws ParseException {

        switch (state) {
        case START:
            if (ch == '"') {
                tagBoundary = true;
            } else if (Character.isSpaceChar(ch)) {
                tagBoundary = true;
            } else {
                if (tagBoundary) {
                    tagBuffer.setLength(0);
                    tagBoundary = false;
                }
                tagBuffer.append(ch);
            }
            break;
        }

        super.receive(ch);
    }

    @Override
    protected void accept(String stringToken) {
        String tag;
        if (tagBuffer.length() == 0)
            tag = null;
        else
            tag = tagBuffer.toString();
        acceptTaggedString(tag, stringToken);
    }

    protected abstract void acceptTaggedString(String tag, String string);

}
