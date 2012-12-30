package net.bodz.bas.i18n.dom;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import net.bodz.bas.c.object.Nullables;

/**
 * This is a cross-node design.
 * 
 * TERM:
 * <ul>
 * <li>Next-List: The list by chaining all <code>next</code> fields.
 * <li>Follow-List: The list by chaining all <code>next</code> fields start from
 * <code>follow1</code>.
 * <li>Path-List: List of nodes render a specific path.
 * </ul>
 * 
 * Note: This class is not thread safe.
 */
public abstract class XDomainNode<node_t extends XDomainNode<node_t, value_t>, value_t>
        implements IDomainNode<node_t, value_t>, Cloneable {

    public static final char DOMAIN_SEPARATOR = '-';

    protected final String domain;
    protected value_t value;
    // node_t parent;
    node_t follow1;
    node_t next;

    public XDomainNode(String domain) {
        this(domain, null);
    }

    public XDomainNode(value_t value) {
        this(null, value);
    }

    public XDomainNode(String domain, value_t value) {
        this.domain = domain;
        this.value = value;
    }

    @SafeVarargs
    public XDomainNode(String domain, value_t value, node_t... follows) {
        this(domain, value);
        for (node_t follow : follows)
            insertFollow(follow);
    }

    protected XDomainNode(node_t other) {
        domain = other.domain;
        value = other.value;
        if (other.follow1 != null)
            follow1 = other.follow1.clone();
        if (other.next != null)
            next = other.next.clone();
    }

    @Override
    protected abstract node_t clone();

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public value_t getValue() {
        return value;
    }

    @Override
    public void setValue(value_t value) {
        this.value = value;
    }

    @Override
    public final value_t get(String path) {
        node_t node = getNode(path);
        return node == null ? null : node.value;
    }

    @Override
    public final node_t getNode(String path) {
        node_t node = _resolve(path, DomainResolveMode.nullForNone, null);
        return node;
    }

    @Override
    public final value_t getNearest(String path) {
        node_t node = getNearestNode(path);
        return node == null ? null : node.value;
    }

    @Override
    public final node_t getNearestNode(String path) {
        return _resolve(path, DomainResolveMode.findNearest, null);
    }

    /**
     * @param path
     *            <code>null</code> or empty string means current node.
     */
    @Override
    public final value_t put(String path, value_t value) {
        node_t node = create(path, null);
        value_t oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    @Override
    public final value_t pull(String path) {
        node_t node = pullNode(path);
        return node == null ? null : node.value;
    }

    @Override
    public final node_t pullNode(String path) {
        return _resolve(path, DomainResolveMode.findNearestAndPullToFront, null);
    }

    /**
     * @param path
     *            <code>null</code> or empty string means current node.
     */
    @Override
    public final node_t create(String path, value_t initialValue) {
        node_t node = _resolve(path, DomainResolveMode.createPath, initialValue);
        return node;
    }

    @Override
    public value_t remove(String path) {
        node_t node = removeNode(path);
        return node == null ? null : node.value;
    }

    public node_t removeNode(String path) {
        if (path == null || path.isEmpty()) {
            @SuppressWarnings("unchecked") node_t _this = (node_t) this;
            this.value = null;
            return _this;
        }

        int pos = path.indexOf(DOMAIN_SEPARATOR);
        if (pos == -1) {
            node_t follow = getFollow(path, false);
            if (follow != null)
                removeFollow(follow);
            return follow;
        }

        String token = path.substring(0, pos);
        path = path.substring(pos + 1);
        node_t follow = getFollow(token, false);
        if (follow != null)
            return follow.removeNode(path);
        else
            return null;
    }

    /**
     * @param path
     *            <code>null</code> or empty string means current node.
     */
    protected node_t _resolve(String path, DomainResolveMode mode, value_t initialValue) {
        @SuppressWarnings("unchecked") node_t _this = (node_t) this;

        if (path == null || path.isEmpty()) {
            if (value != null)
                return _this;
            else
                switch (mode) {
                // case raw: return this;
                case nullForNone: // treat as it is already compacted
                default:
                    return null;
                case findNearest:
                case findNearestAndPullToFront:
                    return null;
                case createPath:
                    value = initialValue;
                    return _this;
                }
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

        node_t child = getFollow(token, mode.isPullToFront());

        switch (mode) {
        case nullForNone:
        default:
            if (child == null)
                return null;
            else
                break;

        case findNearest:
        case findNearestAndPullToFront:
            if (child != null) {
                child = child._resolve(path, mode, initialValue);
                if (child != null)
                    return child;
            }
            // child == null.
            if (value == null)
                return null;
            else
                return _this;

        case createPath:
            if (child == null) {
                if (path == null)
                    child = createNode(token, initialValue);
                else
                    child = createNode(token, null);
                insertFollow(child);
            }
            break;
        }
        return child._resolve(path, mode, initialValue);
    }

    /**
     * A factory method to create a concrete node.
     */
    protected abstract node_t createNode(String domain, value_t value);

    /**
     * Find the token in the follow-list.
     * 
     * @param token
     *            non-<code>null</code> token.
     * @return <code>null</code> if the token is not defined.
     */
    protected synchronized node_t getFollow(String token, boolean pullToFront) {
        if (token == null)
            throw new NullPointerException("token");
        node_t tmp = follow1;
        node_t tmpPrev = null;
        while (tmp != null) {
            if (token.equals(tmp.domain)) {
                if (pullToFront)
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

    protected synchronized void insertFollow(node_t node) {
        if (follow1 != null)
            node.next = follow1;
        follow1 = node;
    }

    protected synchronized void insertNext(node_t node) {
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
        if (follow1 == node) {
            follow1 = node.next;
            node.next = null;
            return true;
        }
        return follow1.removeNext(node);
    }

    @Override
    public int size() {
        int size = value == null ? 0 : 1;
        if (follow1 != null)
            size += follow1.size();
        if (next != null)
            size += next.size();
        return size;
    }

    @Override
    public Iterator<Entry<String, node_t>> iterator() {
        return new NodeIterator();
    }

    class NodeIterator
            implements Iterator<Entry<String, node_t>>, Entry<String, node_t> {

        StackedNode stack = new StackedNode(null, XDomainNode.this);
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

    @Override
    public Set<Entry<String, value_t>> entrySet() {
        return new EntrySet();
    }

    class EntrySet
            extends AbstractSet<Entry<String, value_t>> {

        @Override
        public Iterator<Entry<String, value_t>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return XDomainNode.this.size();
        }

    }

    class EntryIterator
            implements Iterator<Entry<String, value_t>>, Entry<String, value_t> {

        final NodeIterator nodeIterator;

        public EntryIterator() {
            this.nodeIterator = new NodeIterator();
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public Entry<String, value_t> next() {
            nodeIterator.next();
            return this;
        }

        @Override
        public void remove() {
            nodeIterator.remove();
        }

        @Override
        public String getKey() {
            return nodeIterator.getKey();
        }

        @Override
        public value_t getValue() {
            node_t node = nodeIterator.getValue();
            return (node == null) ? null : node.value;
        }

        @Override
        public value_t setValue(value_t value) {
            node_t node = nodeIterator.getValue();
            if (node == null)
                throw new IllegalStateException("Node isn't set");
            value_t oldValue = node.value;
            node.value = value;
            return oldValue;
        }

    }

    @Override
    public Set<String> keySet() {
        return new KeySet();
    }

    class KeySet
            extends AbstractSet<String> {

        @Override
        public Iterator<String> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return XDomainNode.this.size();
        }

    }

    class KeyIterator
            implements Iterator<String> {

        final NodeIterator nodeIterator;

        public KeyIterator() {
            this.nodeIterator = new NodeIterator();
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public String next() {
            Entry<String, node_t> entry = nodeIterator.next();
            return entry.getKey();
        }

        @Override
        public void remove() {
            nodeIterator.remove();
        }

    }

    public String dumpContent() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, node_t> trEntry : this) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        if (value != null)
            hash += value.hashCode();
        if (follow1 != null)
            hash += follow1.hashCode() * 17;
        if (next != null)
            hash += next.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;

        @SuppressWarnings("unchecked") node_t o = (node_t) obj;

        // if (!Nullables.equals(domain, o.domain))
        // return false;
        if (!Nullables.equals(value, o.value))
            return false;

        if (follow1 != o.follow1) {
            if (follow1 == null || o.follow1 == null)
                return false;
            node_t a = follow1, b;
            while (a != null) {
                b = o.getFollow(a.getDomain(), false);
                if (b == null || !a.equals(b))
                    return false;
                a = a.next;
            }
            b = o.follow1;
            while (b != null) {
                a = getFollow(b.getDomain(), false);
                if (a == null)
                    return false;
                b = b.next;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
