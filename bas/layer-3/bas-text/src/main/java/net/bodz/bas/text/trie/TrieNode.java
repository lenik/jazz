package net.bodz.bas.text.trie;

import java.util.Set;

public abstract class TrieNode<char_t, T, self_t extends TrieNode<char_t, T, self_t>>
        implements
            ITrieNode<char_t, T, self_t> {

    protected final ITrie<char_t, T, self_t> trie;
    protected final self_t parent;
    public final int depth;
    public final char_t key;

    private boolean wildcard;

    public TrieNode(ITrie<char_t, T, self_t> trie, self_t parent, char_t key) {
        this.trie = trie;
        this.parent = parent;
        this.depth = parent == null ? 0 : (parent.getDepth() + 1);
        this.key = key;
    }

    @Override
    public self_t getParent() {
        return parent;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public char_t getKey() {
        return key;
    }

    @Override
    public boolean isWildcard() {
        return wildcard;
    }

    public void setWildcard(boolean wildcard) {
        this.wildcard = wildcard;
    }

    @Override
    public self_t detach() {
        return detach(true);
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean printChildren) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(key);
        sb.append("]");
        if (wildcard)
            sb.append("*");

        T data = getData();
        if (data != null) {
            sb.append(" = ");
            sb.append(data);
        }

        Set<char_t> childKeys = getChildKeys();
        if (printChildren && ! childKeys.isEmpty()) {
            sb.append(" {");
            int i = 0;
            for (char_t k : childKeys) {
                self_t child = getChild(k);
                if (i++ != 0)
                    sb.append(", ");
                sb.append(k);
                sb.append(": ");
                sb.append(child);
            }
            sb.append(" }");
        }
        return sb.toString();
    }

    @Override
    public boolean accept(ITrieNodeVisitor<self_t> visitor) {
        @SuppressWarnings("unchecked")
        self_t thisNode = (self_t) this;
        if (! visitor.node(thisNode))
            return false;
        if (isDefined())
            if (! visitor.leafNode(thisNode))
                return false;
        return true;
    }

}
