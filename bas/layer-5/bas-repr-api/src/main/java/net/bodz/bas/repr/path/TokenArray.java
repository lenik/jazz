package net.bodz.bas.repr.path;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public class TokenArray
        extends AbstractBasicTokenQueue
        implements IMutableTokenArray,
                   IRewindableTokenQueue,
                   Serializable,
                   Cloneable {

    private static final long serialVersionUID = 1L;

    String[] tokens;
    int start;
    int end;
    boolean entered;
    int pos;
    boolean stopped;

    public TokenArray(@NotNull String[] tokens) {
        this(tokens, 0, tokens.length, false);
    }

    public TokenArray(@NotNull String[] tokens, boolean entered) {
        this(tokens, 0, tokens.length, entered);
    }

    public TokenArray(@NotNull String[] tokens, int off, int len, boolean entered) {
        this.tokens = tokens;
        this.start = off;
        this.end = off + len;
        this.entered = entered;
        this.pos = off;
    }

    public static TokenArray ofPath(String path) {
        return new Builder().path(path).build();
    }

    @Override
    public TokenArray clone() {
        TokenArray o = new TokenArray(tokens, start, end, entered);
        o.pos = pos;
        o.stopped = stopped;
        return o;
    }

    protected String[] getBackedArray() {
        return tokens;
    }

    protected int getBackedArrayStart() {
        return start;
    }

    protected int getBackedArrayEnd() {
        return end;
    }

    protected void setBackedArray(@NotNull String[] array) {
        setBackedArray(array, 0, array.length);
    }

    protected void setBackedArray(@NotNull String[] array, int off, int len) {
        this.tokens = array;
        this.start = off;
        this.end = off + len;
        this.pos = start;
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public int available() {
        return end - pos;
    }

    @Override
    public void reset() {
        pos = start;
    }

    @Override
    public void resize(int newSize) {
        resize(newSize, false);
    }

    void resize(int newSize, boolean always) {
        int capacity = end - start;
        if (always || newSize > capacity) {
            String[] realloc = new String[newSize];
            System.arraycopy(tokens, start, realloc, 0, capacity);
            tokens = realloc;
            pos -= start;
            if (pos > newSize)
                pos = newSize;
            start = 0;
            end = newSize;
        } else {
            end = start + newSize;
        }
    }

    @Override
    public void compact() {
        resize(end - start, true);
    }

    @Override
    public void skip(int n) {
        int newPos = this.pos + n;
        if (newPos > end)
            newPos = end;
        this.pos = newPos;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException("" + index);
        return tokens[start + index];
    }

    @Override
    public void set(int index, @NotNull String token) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException("" + index);
        tokens[start + index] = token;
    }

    @Override
    public void add(String token) {
        int size = size();
        resize(size + 1);
        set(size, token);
    }

    @Override
    public void add(int index, String token) {
        int size = size();
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index " + index);
        int insertPos = start + index;
        resize(size + 1);

        // move part to the right
        System.arraycopy(tokens, insertPos, tokens, insertPos + 1, size - index);
        set(index, token);
        if (pos >= insertPos)
            pos++;
    }

    @Override
    public void delete(int index) {
        int size = size();
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index);
        int deletePos = start + index;
        System.arraycopy(tokens, deletePos + 1, tokens, deletePos, size - index - 1);
        end--;
        if (pos > deletePos)
            pos--;
    }

    @Override
    public int position() {
        return pos - start;
    }

    @Override
    public String[] shift(int n) {
        if (n > available())
            n = available();
        String[] copy = Arrays.copyOfRange(tokens, pos, pos + n);
        pos += n;
        return copy;
    }

    @Override
    public String shift() {
        if (pos >= end)
            return null;
        return tokens[pos++];
    }

    @Override
    public String peekAt(int offset) {
        int index = this.pos + offset;
        if (index < start || index >= end)
            return null;
        return tokens[index];
    }

    public static class Builder {

        String[] tokens;
        int off;
        int len;
        boolean entered;

        public Builder tokens(String[] tokens) {
            return tokens(tokens, 0, tokens.length);
        }

        public Builder tokens(String[] tokens, int off, int len) {
            this.tokens = tokens;
            this.off = off;
            this.len = len;
            return this;
        }

        public Builder path(String path) {
            if (path == null)
                throw new NullPointerException("path");

            if (path.isEmpty()) {
                this.tokens = new String[0];
                this.off = this.len = 0;
                this.entered = false;
                return this;
            }

            entered = path.charAt(path.length() - 1) == '/';

            List<String> tokens = Parsers.parsePath(path);
            this.tokens = tokens.toArray(new String[0]);
            return this;
        }

        public Builder enter() {
            this.entered = true;
            return this;
        }

        public TokenArray build() {
            TokenArray o = new TokenArray(tokens, off, len, entered);
            return o;
        }

    }

}
