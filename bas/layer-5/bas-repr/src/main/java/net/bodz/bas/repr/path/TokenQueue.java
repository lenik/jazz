package net.bodz.bas.repr.path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public class TokenQueue
        // extends AbstractHttpRequestProcessor
        implements ITokenQueue, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private final String[] tokens;
    private final boolean entered;
    private int index;
    private boolean stopped;

    public TokenQueue(String[] tokens, boolean entered) {
        if (tokens == null)
            throw new NullPointerException("tokens");
        this.tokens = tokens;
        this.entered = entered;
    }

    public TokenQueue(String path) {
        if (path == null)
            throw new NullPointerException("path");

        if (path.isEmpty()) {
            this.tokens = new String[0];
            this.entered = false;
            return;
        }

        entered = path.charAt(path.length() - 1) == '/';

        List<String> tokens = new ArrayList<String>(20);
        int start = 0;
        int slash;
        while ((slash = path.indexOf('/', start)) != -1) {
            if (slash > start) { // skip empty tokens like '//', and trailing token.
                String token = path.substring(start, slash);
                tokens.add(token);
            }
            start = slash + 1;
        }

        // start = 0 or (lastSlash + 1)
        if (path.length() > start) {
            String token = path.substring(start);
            tokens.add(token);
        }

        this.tokens = tokens.toArray(new String[0]);
    }

    @Override
    public TokenQueue clone() {
        TokenQueue o = new TokenQueue(tokens, entered);
        o.index = index;
        o.stopped = stopped;
        return o;
    }

    @Override
    public int available() {
        return tokens.length - index;
    }

    @Override
    public String getRemainingPath() {
        int remaining = tokens.length - index;
        if (remaining == 0)
            return null;

        StringBuilder buf = new StringBuilder(remaining * 20);
        for (int i = index; i < tokens.length; i++) {
            if (i != index)
                buf.append('/');
            buf.append(tokens[i]);
        }
        return buf.toString();
    }

    @Override
    public boolean isEntered() {
        return entered;
    }

    @Override
    public boolean isEmpty() {
        return index >= tokens.length;
    }

    @Override
    public void skip(int n) {
        int index = this.index + n;
        if (index > tokens.length)
            throw new IllegalArgumentException("Skip to underflow: " + n);
        this.index = index;
    }

    @Override
    public String[] shift(int n) {
        if (index + n > tokens.length)
            return null;
        String[] copy = Arrays.copyOfRange(tokens, index, index + n);
        index += n;
        return copy;
    }

    @Override
    public String[] shiftAll() {
        return shift(available());
    }

    @Override
    public String shift() {
        if (index >= tokens.length)
            return null;
        return tokens[index++];
    }

    @Override
    public Integer shiftInt() {
        Integer n = peekInt();
        if (n != null)
            index++;
        return n;
    }

    @Override
    public Long shiftLong() {
        Long n = peekLong();
        if (n != null)
            index++;
        return n;
    }

    @Override
    public final String peek() {
        return peek(0);
    }

    @Override
    public String peek(int offset) {
        int index = this.index + offset;
        if (index >= tokens.length)
            return null;
        return tokens[index];
    }

    @Override
    public final Integer peekInt() {
        return peekInt(0);
    }

    @Override
    public Integer peekInt(int offset) {
        int index = this.index + offset;
        if (index >= tokens.length)
            return null;
        if (!isNumber(tokens[index]))
            return null;
        return Integer.valueOf(tokens[index]);
    }

    @Override
    public final Long peekLong() {
        return peekLong(0);
    }

    @Override
    public Long peekLong(int offset) {
        int index = this.index + offset;
        if (index >= tokens.length)
            return null;
        if (!isNumber(tokens[index]))
            return null;
        long n = Long.parseLong(tokens[index]);
        return n;
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public void stop() {
        this.stopped = true;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < index; i++) {
            if (i != 0)
                buf.append('/');
            buf.append(tokens[i]);
        }

        // "a/b <---> /c"
        buf.append(" → ");

        for (int i = index; i < tokens.length; i++) {
            buf.append('/');
            buf.append(tokens[i]);
        }
        return buf.toString();
    }

    static boolean isNumber(String str) {
        int i = str.length();
        while (--i >= 0) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

}
