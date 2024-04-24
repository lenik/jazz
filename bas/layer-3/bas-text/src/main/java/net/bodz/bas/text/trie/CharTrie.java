package net.bodz.bas.text.trie;

import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;

public class CharTrie<T, node_t extends ITrieNode<Character, T, node_t>>
        extends AbstractTrie<Character, T, node_t> {

    protected CharTrie() {
        super();
    }

    public CharTrie(INodeFactory<Character, T, node_t> nodeFactory) {
        super(nodeFactory);
    }

    public node_t find(char[] chars) {
        return find(chars, 0, chars.length);
    }

    public node_t find(char[] chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars[i];
            node = node.getChild(ch);
            if (node == null)
                return null;
        }
        return node;
    }

    public node_t findOrCreate(char[] chars) {
        return findOrCreate(chars, 0, chars.length);
    }

    public node_t findOrCreate(char[] chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars[i];
            node = node.getOrAddChild(ch);
        }
        return node;
    }

    public node_t find(CharSequence chars) {
        return find(chars, 0, chars.length());
    }

    public node_t find(CharSequence chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            node = node.getChild(ch);
            if (node == null)
                return null;
        }
        return node;
    }

    public node_t findOrCreate(CharSequence chars) {
        return findOrCreate(chars, 0, chars.length());
    }

    public node_t findOrCreate(CharSequence chars, int start, int end) {
        node_t node = root;
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            node = node.getOrAddChild(ch);
        }
        return node;
    }

    public node_t findShortestDefinedPrefix(CharSequence string) {
        return findShortestDefinedPrefix(Vectors.ofString(string));
    }

    public node_t findLongestDefinedPrefix(CharSequence string) {
        return findLongestDefinedPrefix(Vectors.ofString(string));
    }

    public boolean isDefined(char[] chars) {
        return isDefined(chars, 0, chars.length);
    }

    public boolean isDefined(char[] chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    public boolean isDefined(CharSequence chars) {
        return isDefined(chars, 0, chars.length());
    }

    public boolean isDefined(CharSequence chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return false;
        else
            return node.isDefined();
    }

    public node_t remove(char[] chars) {
        return remove(chars, 0, chars.length);
    }

    public node_t remove(char[] chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return null;
        node.detach();
        return node;
    }

    public node_t remove(CharSequence chars) {
        return remove(chars, 0, chars.length());
    }

    public node_t remove(CharSequence chars, int start, int end) {
        node_t node = find(chars, start, end);
        if (node == null)
            return null;
        node.detach();
        return node;
    }

    public Vector<node_t> findAll(CharSequence content) {
        return findAll(Vectors.ofString(content));
    }

}
