package net.bodz.mda.parsers;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.util.Strings;

public abstract class _Lexer implements Lexer {

    protected final Parser parser;

    private Stack<Integer> stateStack;
    private int            currentState;

    private DocumentSize   last;
    private DocumentSize   end;

    private Stack<Buffer>  recordStack;
    private Buffer         currentRecord;

    /**
     * @param parser
     *            used by:
     *            <ul>
     *            <li>{@link #getTokenDeclClass()}
     *            <li>{@link #setValue()}
     *            <li>{@link #read()}
     *            </ul>
     */
    public _Lexer(Parser parser) {
        this.parser = parser;
        this.currentState = getInitialState();
    }

    protected abstract int getInitialState();

    // DocumentPosition

    @Override
    public abstract long tell();

    @Override
    public abstract int line();

    @Override
    public abstract int column();

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
    public int state() {
        return currentState;
    }

    protected abstract void setState(int state);

    @Override
    public void enter(int state) {
        if (stateStack == null)
            stateStack = new Stack<Integer>();
        stateStack.add(currentState);
        setState(currentState = state);
    }

    @Override
    public void leave() {
        if (stateStack == null || stateStack.isEmpty())
            throw new StackOverflowError("state stack underflow");
        setState(currentState = stateStack.pop());
    }

    private Map<Integer, String> tokenNames;

    /**
     * Guess token name by searching static fields.
     * <p>
     * This may be not accuracy.
     * 
     * In common case, id &lt; 256 is single-char token, and therefore no
     * corresponding token name.
     * 
     * @return guessed token name
     */
    @Override
    public String getTokenName(int id) {
        if (id >= 0 && id < 256)
            return Strings.escape((char) id);
        if (tokenNames == null)
            tokenNames = new HashMap<Integer, String>();
        String name = tokenNames.get(id);
        if (name == null) {
            name = ReflectUtil.getFirstFieldName(getTokenDeclClass(), null, id);
            if (name == null)
                name = String.valueOf(id);
            tokenNames.put(id, name);
        }
        return name;
    }

    protected Class<?> getTokenDeclClass() {
        return parser.getClass();
    }

    private Map<Integer, String> stateNames;

    /**
     * Guess state name by searching static fields.
     * <p>
     * This may be not accuracy, because state is few used in most lexers, and
     * their values range in 0..10 which are frequently used, often collision
     * with many other irrelevant field values.
     * 
     * @return guessed state name
     */
    @Override
    public String getStateName(int state) {
        if (stateNames == null)
            stateNames = new HashMap<Integer, String>();
        String name = stateNames.get(state);
        if (name == null) {
            name = ReflectUtil.getFirstFieldName(getClass(), null, state);
            if (name == null)
                name = String.valueOf(state);
            stateNames.put(state, name);
        }
        return name;
    }

    public String getStackTrace() {
        StringBuffer buffer = null;
        for (int state : stateStack) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append('\n');
            buffer.append(getStateName(state));
        }
        return buffer.toString();
    }

    /**
     * Enable using of:
     * <ul>
     * <li>{@link net.bodz.mda.parsers.Token#getPreStart()} <li>
     * {@link net.bodz.mda.parsers.Token#getPreStartLine()} <li>
     * {@link net.bodz.mda.parsers.Token#getPreStartCOlumn()} <li>
     * {@link net.bodz.mda.parsers.Token#getEnd()} <li>
     * {@link net.bodz.mda.parsers.Token#getEndLine()} <li>
     * {@link net.bodz.mda.parsers.Token#getEndColumn()}
     * </ul>
     */
    protected boolean lookAheadEndOfMatch() {
        return true;
    }

    public void setValue(Object value) {
        parser.setValue(value);
    }

    public class _Token implements Token {

        private int          id;
        private String       name;
        private Object       value;
        private DocumentSize last;
        private DocumentSize end;
        private int          start;
        private int          line;
        private int          column;

        protected _Token(int id, Object value) {
            this.id = id;
            this.value = value;
            this.last = _Lexer.this.last;
            this.end = _Lexer.this.end;
            this.start = (int) _Lexer.this.tell();
            this.line = _Lexer.this.line();
            this.column = _Lexer.this.column();
        }

        protected _Token(int id) {
            this.id = id;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getName() {
            if (name == null)
                name = resolveName(id);
            return name;
        }

        protected String resolveName(int id) {
            throw new NotImplementedException();
        }

        @Override
        public Object value() {
            return value;
        }

        @Override
        public int getPreStart() {
            return last.length;
        }

        @Override
        public int getPreStartLine() {
            return last.lines;
        }

        @Override
        public int getPreStartColumn() {
            return last.lineOffset;
        }

        @Override
        public int getStart() {
            return start;
        }

        @Override
        public int getStartLine() {
            return line;
        }

        @Override
        public int getStartColumn() {
            return column;
        }

        @Override
        public int getEnd() {
            return end.length;
        }

        @Override
        public int getEndLine() {
            return end.lines;
        }

        @Override
        public int getEndColumn() {
            return end.lineOffset;
        }

        @Override
        public String getText() {
            return _Lexer.this.getText();
        }

        @Override
        public int getLength() {
            return _Lexer.this.getLength();
        }

        @Override
        public char getChar(int index) {
            return _Lexer.this.getChar(index);
        }

        /** token N(Name)="text" at offset(line:column), value=? */
        @Override
        public String toString() {
            Buffer buf = new Buffer();
            buf.print("token ");
            buf.print(id);
            String name = getName();
            if (name != null) {
                buf.print('(');
                buf.print(name);
                buf.print(')');
            }
            buf.print("=\"");
            String text = Strings.ellipse(getText(), 20);
            buf.print(Strings.escape(text));
            buf.print("\" at ");
            buf.print(start);
            buf.print('(');
            buf.print(line);
            buf.print(':');
            buf.print(column);
            buf.print("), value=");
            buf.println(String.valueOf(value));
            return buf.toString();
        }
    }

    private DocumentSize end() {
        DocumentSize start = new DocumentSize((int) tell(), line(), column());
        DocumentSize end = start.add(getText());
        return end;
    }

    @Override
    public Token read() {
        boolean look = lookAheadEndOfMatch();
        if (look) {
            last = end;
            end = end();
        }
        int tokenId = _read();
        Object value = parser.value();
        return new _Token(tokenId, value);
    }

    protected abstract int _read();

    protected void startRecord() {
        startRecord(32);
    }

    protected void startRecord(int capacity) {
        if (currentRecord != null) {
            if (recordStack == null)
                recordStack = new Stack<Buffer>();
            recordStack.push(currentRecord);
        }
        currentRecord = new Buffer(capacity);
    }

    protected Buffer record() {
        if (currentRecord == null)
            throw new IllegalStateException("recording is not started");
        return currentRecord;
    }

    protected void record(String s) {
        record().print(s);
    }

    protected String endRecord() {
        String s = record().toString();
        if (recordStack == null || recordStack.isEmpty())
            currentRecord = null;
        else
            currentRecord = recordStack.pop();
        return s;
    }

}
