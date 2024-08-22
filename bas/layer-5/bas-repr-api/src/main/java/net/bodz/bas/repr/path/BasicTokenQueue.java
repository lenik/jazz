package net.bodz.bas.repr.path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public class BasicTokenQueue
        implements
            IStaticTokenArray,
            IForwardOnlyTokenQueue,
            Serializable,
            Cloneable {

    private static final long serialVersionUID = 1L;

    final String[] tokens;
    final int begin;
    final int end;
    final boolean entered;
    int index;
    boolean stopped;

    public BasicTokenQueue(String[] tokens, boolean entered) {
        if (tokens == null)
            throw new NullPointerException("tokens");
        this.tokens = tokens;
        this.begin = 0;
        this.end = tokens.length;
        this.entered = entered;
    }

    public BasicTokenQueue(String method, String scheme, String host, int port, String userInfo, String query,
            String fragment, String[] tokens, boolean entered) {
        if (tokens == null)
            throw new NullPointerException("tokens");
        this.tokens = tokens;
        this.begin = 0;
        this.end = tokens.length;
        this.entered = entered;
    }

    public static BasicTokenQueue ofPath(String path) {
        return new Builder().path(path).build();
    }

    @Override
    public BasicTokenQueue clone() {
        BasicTokenQueue o = new BasicTokenQueue(tokens, entered);
        o.index = index;
        o.stopped = stopped;
        return o;
    }

    void initState(BasicTokenQueue o) {
        index = o.index;
        stopped = o.stopped;
    }

    @Override
    public int size() {
        return end - begin;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= end - begin)
            throw new IndexOutOfBoundsException("" + index);
        return tokens[begin + index];
    }

    @Override
    public int getInt(int index)
            throws ParseException {
        String token = get(index);
        return parseInt(token);
    }

    @Override
    public int getInt(int index, int fallback) {
        String token = get(index);
        if (! isNumber(token))
            return fallback;
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    @Override
    public long getLong(int index)
            throws ParseException {
        String token = get(index);
        return parseLong(token);
    }

    @Override
    public long getLong(int index, long fallback) {
        String token = get(index);
        if (! isNumber(token))
            return fallback;
        try {
            return Long.parseLong(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException {
        E val = get(enumType, index, null);
        if (val == null) {
            String token = tokens[begin + index];
            throw new ParseException(String.format(//
                    "invalid enum name %s, in type %s", token, enumType.getName()));
        }
        return val;
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback) {
        if (index < 0 || index >= end - begin)
            return fallback;
        String token = tokens[begin + index];
        try {
            return Enum.valueOf(enumType, token);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    @Override
    public int available() {
        return end - index;
    }

    @Override
    public String getRemainingPath() {
        int remaining = end - index;
        if (remaining == 0)
            return null;

        StringBuilder buf = new StringBuilder(remaining * 20);
        for (int i = index; i < end; i++) {
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
    public boolean isDone() {
        return index >= end;
    }

    @Override
    public void skip(int n) {
        int index = this.index + n;
        if (index > end)
            throw new IllegalArgumentException("Skip to underflow: " + n);
        this.index = index;
    }

    @Override
    public String[] shift(int n) {
        if (index + n > tokens.length)
            throw new TokenUnderflowException();
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
    public Integer shiftInt()
            throws ParseException {
        Integer n = peekInt();
        if (n != null)
            index++;
        return n;
    }

    @Override
    public int shiftInt(int fallback) {
        if (index >= end)
            return fallback;
        int n = peekInt(fallback);
        index++;
        return n;
    }

    @Override
    public Long shiftLong()
            throws ParseException {
        Long n = peekLong();
        if (n != null)
            index++;
        return n;
    }

    @Override
    public long shiftLong(int fallback) {
        if (index >= end)
            return fallback;
        long n = peekLong(fallback);
        index++;
        return n;
    }

    @Override
    public final String peek() {
        return peekAhead(0);
    }

    @Override
    public String peekAhead(int offset) {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return null;
        return tokens[index];
    }

    @Override
    public final Integer peekInt()
            throws ParseException {
        return peekIntAhead(0);
    }

    @Override
    public Integer peekIntAhead(int offset)
            throws ParseException {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return null;
        String token = tokens[index];
        return parseInt(token);
    }

    @Override
    public int peekInt(int fallback) {
        return peekIntAhead(0, fallback);
    }

    @Override
    public int peekIntAhead(int offset, int fallback) {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return fallback;
        if (! isNumber(tokens[index]))
            return fallback;
        try {
            return Integer.valueOf(tokens[index]);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    @Override
    public final Long peekLong()
            throws ParseException {
        return peekLongAhead(0);
    }

    @Override
    public Long peekLongAhead(int offset)
            throws ParseException {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return null;
        String token = tokens[index];
        return parseLong(token);
    }

    @Override
    public long peekLong(long fallback) {
        return peekLongAhead(0, fallback);
    }

    @Override
    public long peekLongAhead(int offset, long fallback) {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return fallback;
        String token = tokens[index];
        if (! isNumber(token))
            return fallback;
        try {
            return Long.valueOf(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    @Override
    public <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset)
            throws ParseException {
        E val = peekAhead(enumType, offset, null);
        if (val == null) {
            int index = this.index + offset;
            String token = tokens[index];
            throw new ParseException(String.format(//
                    "invalid enum name %s, in type %s", token, enumType.getName()));
        }
        return val;
    }

    @Override
    public <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset, E fallback) {
        int index = this.index + offset;
        if (index < 0 || index >= tokens.length)
            return null;
        String token = tokens[index];
        try {
            return Enum.valueOf(enumType, token);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
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

    static void ensureNumber(String str)
            throws ParseException {
        if (! isNumber(str))
            throw new ParseException("Not a number: " + str);
    }

    static int parseInt(String str)
            throws ParseException {
        ensureNumber(str);
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a number: " + str, e);
        }
    }

    static long parseLong(String str)
            throws ParseException {
        ensureNumber(str);
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a number: " + str, e);
        }
    }

    public static class Builder {

        String[] tokens;
        boolean entered;

        public Builder tokens(String[] tokens) {
            this.tokens = tokens;
            return this;
        }

        public Builder path(String path) {
            if (path == null)
                throw new NullPointerException("path");

            if (path.isEmpty()) {
                this.tokens = new String[0];
                this.entered = false;
                return this;
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
            return this;
        }

        public Builder enter() {
            this.entered = true;
            return this;
        }

        public BasicTokenQueue build() {
            BasicTokenQueue o = new BasicTokenQueue(tokens, entered);
            return o;
        }

    }

}
