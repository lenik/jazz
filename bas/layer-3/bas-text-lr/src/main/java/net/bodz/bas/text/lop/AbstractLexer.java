package net.bodz.bas.text.lop;

import java.io.Reader;
import java.util.Stack;

import net.bodz.bas.c.reflect.ReflectReverseSearch;
import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.util.IXYTellable;
import net.bodz.bas.sio.util.XYPosition;
import net.bodz.bas.t.map.IndexMap;

public abstract class AbstractLexer
        implements Lexer {

    protected class Token
            extends AbstractToken {

        private final String text = AbstractLexer.this.getText();

        public Token(int id) {
            this(id, null);
        }

        public Token(int id, Object value) {
            super(AbstractLexer.this, id, value, getTokenStart());
        }

        @Override
        public String getText() {
            return text;
        }

    }

    /**
     * @see #_read()
     */
    public static final int EOF = -1;

    private Object value;

    private Stack<Integer> stateStack;
    private Stack<BCharOut> recordStack;
    private BCharOut currentRecord;

    private long prevOffset;
    private int prevY;
    private int prevX;

    public AbstractLexer() {
    }

    protected abstract int _read();

    protected abstract IXYTellable getTokenStart();

    @Override
    public Token read() {
        IXYTellable prev = getTokenStart();
        prevOffset = prev.tell();
        prevY = prev.tellY();
        prevX = prev.tellX();
        int tokenId = _read();
        if (tokenId == EOF)
            return null;
        Object value = getValue();
        setValue(null);
        return new Token(tokenId, value);
    }

    public IXYTellable getTokenStart(int history) {
        switch (history) {
        case 0:
            return getTokenStart();
        case 1:
            return new XYPosition(prevOffset, prevY, prevX);
        }
        return null;
    }

    protected abstract String getText();

    protected int getLength() {
        return getText().length();
    }

    protected char getChar(int index) {
        return getText().charAt(index);
    }

    // Lexer

    @Override
    public abstract void reset(Reader in);

    @Override
    public abstract void close();

    @Override
    public abstract int state();

    protected abstract void setState(int state);

    @Override
    public void enter(int newState) {
        if (stateStack == null)
            stateStack = new Stack<Integer>();
        stateStack.add(state());
        setState(newState);
    }

    @Override
    public void leave() {
        if (stateStack == null || stateStack.isEmpty())
            throw new StackOverflowError("state stack underflow");
        setState(stateStack.pop());
    }

    /**
     * If lexer is used with parser, then the parser shall wrap&override this.
     */
    protected Object getValue() {
        return value;
    }

    /**
     * If lexer is used with parser, then the parser shall wrap&override this.
     */
    protected void setValue(Object value) {
        this.value = value;
    }

    private IndexMap<String> tokenNames;

    /**
     * Guess token name by searching static fields.
     * <p>
     * This may be not accuracy.
     * 
     * In common case, id &lt; 256 is single-char token, and therefore no corresponding token name.
     * 
     * @return guessed token name
     */
    @Override
    public String getTokenName(int id) {
        if (id >= 0 && id < 256)
            return StringEscape.escapeJava((char) id);
        if (tokenNames == null)
            tokenNames = new IndexMap<String>();
        String name = tokenNames.get(id);
        if (name == null) {
            name = ReflectReverseSearch.getFirstFieldName(getTokenDeclClass(), null, id);
            if (name == null)
                name = String.valueOf(id);
            tokenNames.set(id, name);
        }
        return name;
    }

    /**
     * if the token consts are defined in parser class, then return that parser class.
     */
    protected Class<?> getTokenDeclClass() {
        // throw new IllegalUsageError("tokenDeclClass isn't set.");
        return getClass();
    }

    private IndexMap<String> stateNames;

    /**
     * Guess state name by searching static fields.
     * <p>
     * This may be not accuracy, because state is few used in most lexers, and their values range in
     * 0..10 which are frequently used, often collision with many other irrelevant field values.
     * 
     * @return guessed state name
     */
    @Override
    public String getStateName(int state) {
        if (stateNames == null)
            stateNames = new IndexMap<String>();
        String name = stateNames.get(state);
        if (name == null) {
            name = ReflectReverseSearch.getFirstFieldName(getClass(), null, state);
            if (name == null)
                name = String.valueOf(state);
            stateNames.set(state, name);
        }
        return name;
    }

    public String getStackTrace() {
        StringBuilder buffer = null;
        for (int state : stateStack) {
            if (buffer == null)
                buffer = new StringBuilder();
            else
                buffer.append('\n');
            buffer.append(getStateName(state));
        }
        return buffer.toString();
    }

    protected void startRecord() {
        startRecord(32);
    }

    protected void startRecord(int capacity) {
        if (currentRecord != null) {
            if (recordStack == null)
                recordStack = new Stack<BCharOut>();
            recordStack.push(currentRecord);
        }
        currentRecord = new BCharOut(capacity);
    }

    protected BCharOut getRecordBuffer() {
        if (currentRecord == null)
            throw new IllegalStateException("recording is not started");
        return currentRecord;
    }

    protected void record(String s) {
        getRecordBuffer().print(s);
    }

    protected String endRecord() {
        String s = getRecordBuffer().toString();
        if (recordStack == null || recordStack.isEmpty())
            currentRecord = null;
        else
            currentRecord = recordStack.pop();
        return s;
    }

}
