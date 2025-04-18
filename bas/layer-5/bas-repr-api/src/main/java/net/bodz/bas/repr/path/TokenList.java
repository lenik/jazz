package net.bodz.bas.repr.path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public class TokenList
        extends AbstractBasicTokenQueue
        implements IMutableTokenArray,
                   IRewindableTokenQueue,
                   Serializable,
                   Cloneable {

    private static final long serialVersionUID = 1L;

    List<String> tokens;
    boolean entered;
    int pos;
    boolean stopped;

    public TokenList(String... tokens) {
        this(new ArrayList<>(Arrays.asList(tokens)), false);
    }

    public TokenList(List<String> tokens, boolean entered) {
        if (tokens == null)
            throw new NullPointerException("tokens");
        this.tokens = tokens;
        this.entered = entered;
    }

    public static TokenList ofPath(String path) {
        return new Builder().path(path).build();
    }

    @Override
    public TokenList clone() {
        TokenList o = new TokenList(tokens, entered);
        o.pos = pos;
        o.stopped = stopped;
        return o;
    }

    protected List<String> getBackedList() {
        return tokens;
    }

    protected void setBackedList(@NotNull String... tokens) {
        setBackedList(tokens, 0, tokens.length);
    }

    protected void setBackedList(@NotNull String[] tokens, int off, int len) {
        List<String> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++)
            list.add(tokens[off + i]);
        setBackedList(list);
    }

    protected void setBackedList(@NotNull List<String> tokens) {
        this.tokens = tokens;
        pos = 0;
    }

    @Override
    public int size() {
        return tokens.size();
    }

    @Override
    public int available() {
        return size() - pos;
    }

    @Override
    public void reset() {
        pos = 0;
    }

    @Override
    public void resize(int newSize) {
        int size = size();
        while (newSize < size)
            delete(--size);
        while (newSize > size) {
            add(null);
            size++;
        }
    }

    @Override
    public void compact() {
    }

    @Override
    public void set(int index, @NotNull String token) {
        tokens.set(index, token);
    }

    @Override
    public void add(String token) {
        tokens.add(token);
    }

    @Override
    public void add(int index, String token) {
        tokens.add(index, token);
        if (pos >= index)
            pos++;
    }

    @Override
    public void delete(int index) {
        tokens.remove(index);
        if (pos > index)
            pos--;
    }

    @Override
    public void skip(int n) {
        int index = this.pos + n;
        if (index > size())
            throw new IllegalArgumentException("Skip to underflow: " + n);
        this.pos = index;
    }

    @Override
    public String get(int index) {
        return tokens.get(index);
    }

    @Override
    public int position() {
        return pos;
    }

    @Override
    public String[] shift(int n) {
        if (n > available())
            n = available();
        String[] copy = new String[n];
        for (int i = 0; i < n; i++)
            copy[i] = tokens.get(pos + i);
        pos += n;
        return copy;
    }

    @Override
    public String shift() {
        if (available() == 0)
            return null;
        return tokens.get(pos++);
    }

    @Override
    public String peekAt(int offset) {
        int index = this.pos + offset;
        if (index < 0 || index >= size())
            return null;
        return tokens.get(index);
    }

    public static class Builder {

        List<String> tokens;
        boolean entered;

        public Builder tokens(String[] tokens) {
            this.tokens = new ArrayList<>(Arrays.asList(tokens));
            return this;
        }

        public Builder path(String path) {
            if (path == null)
                throw new NullPointerException("path");

            if (path.isEmpty()) {
                this.tokens = new ArrayList<>();
                this.entered = false;
                return this;
            }

            entered = path.charAt(path.length() - 1) == '/';

            this.tokens = Parsers.parsePath(path);
            return this;
        }

        public Builder enter() {
            this.entered = true;
            return this;
        }

        public TokenList build() {
            TokenList o = new TokenList(tokens, entered);
            return o;
        }

    }

}
