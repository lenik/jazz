package net.bodz.bas.i18n.dstr;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/**
 * Note: This class is not thread safe.
 */
public abstract class DomainNode<node_t extends DomainNode<node_t, value_t>, value_t> {

    public static final char DOMAIN_SEPARATOR = '-';

    static final int OPTIM_NONE = 0;
    /** Optimization: bring the last-access token to the top. */
    static final int OPTIM_MRU = 1;
    static int optim = OPTIM_MRU;

    protected final String domain;
    protected value_t value;
    node_t follow1;
    node_t next;

    public DomainNode(String domain) {
        this(domain, null);
    }

    public DomainNode(value_t value) {
        this(null, value);
    }

    public DomainNode(String domain, value_t value) {
        this.domain = domain;
        this.value = value;
    }

    @SafeVarargs
    public DomainNode(String domain, value_t value, node_t... follows) {
        this(domain, value);
        for (node_t follow : follows)
            addFollow(follow);
    }

    public String getDomain() {
        return domain;
    }

    public value_t getValue() {
        return value;
    }

    public void setValue(value_t value) {
        this.value = value;
    }

    public final value_t value() {
        return getValue();
    }

    static final int MODE_NULL = 0;
    static final int MODE_NEAREST = 1;
    static final int MODE_CREATE = 2;

    public final node_t resolve(String path) {
        return _resolve(path, DomainResolveMode.nullForNone, null);
    }

    public final node_t resolve(String path, value_t createValue) {
        return _resolve(path, DomainResolveMode.createPath, createValue);
    }

    protected node_t _resolve(String path, DomainResolveMode mode, value_t createValue) {
        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;

        if (path == null || path.isEmpty()) {
            if (value == null)
                switch (mode) {
                case nullForNone:
                    return null;
                case createPath:
                    value = createValue;
                }
            return _this;
        }

        String token;
        int pos = path.indexOf(DOMAIN_SEPARATOR);
        if (pos == -1) {
            token = path;
            path = null;
        } else {
            token = path.substring(0, pos);
            path = path.substring(pos + 1);
        }

        node_t next = follow(token);
        if (next == null) {
            switch (mode) {
            case nullForNone:
                return null;
            case findNearest:
                return _this;
            case createPath:
                if (path == null)
                    next = create(token, createValue);
                else
                    next = create(token, null);
                addFollow(next);
                break;
            }
        }
        return next._resolve(path, mode, createValue);
    }

    protected abstract node_t create(String domain, value_t value);

    /**
     * @param token
     *            non-<code>null</code> token.
     * @return <code>null</code> if the token is not defined.
     */
    protected synchronized node_t follow(String token) {
        if (token == null)
            throw new NullPointerException("token");
        node_t tmp = follow1;
        node_t tmpPrev = null;
        while (tmp != null) {
            if (token.equals(tmp.domain)) {
                if (optim == OPTIM_MRU)
                    if (tmp != follow1) { // bring to the top
                        if (tmpPrev != null)
                            tmpPrev.next = tmp.next;
                        tmp.next = follow1;
                        follow1 = tmp;
                    }
                return tmp;
            }
            tmpPrev = tmp;
            tmp = tmp.next;
        }
        return null; // no matched token.
    }

    protected synchronized void addFollow(node_t node) {
        if (follow1 != null)
            node.next = follow1;
        follow1 = node;
    }

    protected synchronized void addNext(node_t node) {
        if (next != null)
            node.next = next;
        next = node;
    }

    protected synchronized boolean removeNext(node_t node) {
        node_t tmp = next;
        node_t tmpPrev = null;
        while (tmp != null) {
            if (tmp == node) {
                if (tmpPrev != null)
                    tmpPrev.next = tmp.next;
                if (next == tmp)
                    next = tmpPrev != null ? tmpPrev : tmp.next;
                tmp.next = null; // for safe reason.
                return true;
            }
            tmpPrev = tmp;
            tmp = tmp.next;
        }
        return false;
    }

    protected synchronized boolean removeFollow(node_t node) {
        if (follow1 == null)
            return false;
        return follow1.removeNext(node);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public Iterable<Entry<String, node_t>> dump() {
        return new Iterable<Entry<String, node_t>>() {
            @Override
            public Iterator<Entry<String, node_t>> iterator() {
                return new DepthFirstIterator();
            }
        };
    }

    public String dumpContent() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, node_t> trEntry : dump()) {
            String domainPath = trEntry.getKey();
            node_t node = trEntry.getValue();
            value_t value = node.value;
            if (value == null) {
                // sb.append(domainPath + ": (intermediate)\n");
                continue;
            }
            sb.append(domainPath + ": " + value);
            sb.append("\n");
        }
        return sb.toString();
    }

    static class StackedNode {

        StackedNode parent;
        DomainNode<?, ?> prefetch;

        public StackedNode(StackedNode parent, DomainNode<?, ?> first) {
            this.parent = parent;
            this.prefetch = first;
        }

        public void formatReversedPath(StringBuilder out) {
            String token = prefetch.domain;
            if (token != null) {
                if (out.length() != 0)
                    out.append(DOMAIN_SEPARATOR);
                for (int i = token.length() - 1; i >= 0; i--)
                    out.append(token.charAt(i));
            }
            if (parent != null)
                parent.formatReversedPath(out);
        }

        public String getPath() {
            if (parent == null) {
                // to return null path for the top-level domain, also make it slightly faster.
                return prefetch.domain;
            } else {
                StringBuilder buf = new StringBuilder();
                formatReversedPath(buf);
                buf.reverse();
                return buf.toString();
            }
        }

    }

    class DepthFirstIterator
            implements Iterator<Entry<String, node_t>>, Entry<String, node_t> {

        StackedNode stack = new StackedNode(null, DomainNode.this);
        String currentPath;
        node_t currentNode;

        @Override
        public boolean hasNext() {
            return stack != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Entry<String, node_t> next() {
            if (stack == null)
                throw new NoSuchElementException();

            currentPath = stack.getPath();
            currentNode = (node_t) stack.prefetch;
            node_t tmp = currentNode;

            if (tmp.follow1 != null)
                stack = new StackedNode(stack, tmp.follow1);
            else if (tmp.next != null)
                stack.prefetch = tmp.next;
            else
                do {
                    stack = stack.parent;
                    if (stack == null)
                        break;
                    tmp = (node_t) stack.prefetch;
                    stack.prefetch = tmp = tmp.next;
                } while (tmp == null);

            return this;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getKey() {
            return currentPath;
        }

        @Override
        public node_t getValue() {
            return currentNode;
        }

        @Override
        public node_t setValue(node_t value) {
            throw new UnsupportedOperationException();
        }

    }

}
