package net.bodz.bas.i18n.dstr;

public abstract class MultiTagStringParser {

    private static final int START = 0;
    private static final int QQ = 1;
    private static final int ESCAPE = 2;

    private int state;
    private StringBuilder tagBuffer = new StringBuilder();
    private boolean tagBoundary;
    private StringBuilder partBuffer = new StringBuilder();
    private boolean partQueued;

    protected void reset() {
        state = START;
        tagBuffer.setLength(0);
        tagBoundary = false;
        partBuffer.setLength(0);
        partQueued = false;
    }

    /**
     * Example: <code>"for default lang" xxx "for xxx" "concat..." ...</code>
     * 
     * @return Not used, it should be overrided in the subclass.
     */
    public synchronized Object parse(String s) {
        reset();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (state) {
            case START:
                if (ch == '"') {
                    state = QQ;
                    tagBoundary = true;
                } else if (Character.isSpaceChar(ch)) {
                    tagBoundary = true;
                } else {
                    if (partQueued)
                        flushQueue();
                    if (tagBoundary) {
                        tagBuffer.setLength(0);
                        tagBoundary = false;
                    }
                    tagBuffer.append(ch);
                }
                break;
            case QQ:
                switch (ch) {
                case '"':
                    partQueued = true;
                    state = START;
                    break;
                case '\\':
                    state = ESCAPE;
                    break;
                default:
                    partBuffer.append(ch);
                }
                break;
            case ESCAPE:
                switch (ch) {
                case 'n':
                    ch = '\n';
                    break;
                case 't':
                    ch = '\t';
                    break;
                case 'f':
                    ch = '\f';
                    break;
                case '0':
                    ch = '\0';
                    break;
                case 'x':
                    throw new UnsupportedOperationException("\\x### isn't supported yet");
                } // switch ch.
                partBuffer.append(ch);
                state = QQ;
                break;
            default:
                assert false;
            } // state
        } // input ch
        if (partQueued)
            flushQueue();
        return null;
    }

    private void flushQueue() {
        String tag;
        if (tagBuffer.length() == 0)
            tag = null;
        else
            tag = tagBuffer.toString();
        commit(tag, partBuffer.toString());
        partBuffer.setLength(0);
        partQueued = false;
    }

    protected abstract void commit(String tag, String string);

}
