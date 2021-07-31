package net.bodz.bas.text.qlex.trie;

import java.io.IOException;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.StringCharIn;
import net.bodz.bas.text.trie.CharTrie;
import net.bodz.bas.text.trie.CharTrie.Node;

public class TrieLexer<sym> {

    CharTrie<sym> trie;

    public TrieLexer() {
        trie = new CharTrie<sym>();
    }

    public Node<sym> declare(CharSequence k, sym symbol) {
        Node<sym> overlapped = trie.findOverlap(k);
        if (overlapped != null)
            throw new DuplicatedKeyException("Symbol overlapped: " + k);

        Node<sym> node = trie.resolve(k);
        node.define(symbol);
        return node;
    }

    public TrieTokenizer<sym> tokenize(String s) {
        StringCharIn in = new StringCharIn(s);
        return tokenize(in);
    }

    public TrieTokenizer<sym> tokenize(ICharIn in) {
        return new TrieTokenizer<sym>(trie, in);
    }

    public TrieParser<sym> newParser(String s, ITokenCallback<sym> callback)
            throws IOException, ParseException {
        StringCharIn in = new StringCharIn(s);
        return newParser(in, callback);
    }

    public TrieParser<sym> newParser(ICharIn in, ITokenCallback<sym> callback)
            throws IOException, ParseException {
        TrieParser<sym> parser = new TrieParser<sym>(trie, in, callback);
        return parser;
    }

}