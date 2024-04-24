package net.bodz.bas.text.trie;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.Replacement;
import net.bodz.bas.t.vector.Vector;

public class TextReplacer {

    private final DefaultCharTrie<String> trie = new DefaultCharTrie<String>();
    private boolean wholeOnly = true;

    public TextReplacer(boolean wholeOnly) {
        this.wholeOnly = wholeOnly;
    }

    public TextReplacer replace(String pattern, String replacement) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (replacement == null)
            throw new NullPointerException("replacement");
        trie.findOrCreate(pattern).setData(replacement);
        return this;
    }

    public String transform(String input) {
        int inputLength = input.length();
        StringBuilder buf = new StringBuilder(inputLength);
        Vector<DefaultNode<Character, String>> matchv = trie.findAll(input);
        int pos = 0;
        for (int i = 0; i < inputLength; i++) {
            DefaultNode<Character, String> match = matchv.get(i);
            if (match == null)
                continue;

            int matchLen = match.depth;
            int matchStart = i - (matchLen - 1);
            if (wholeOnly) {
                boolean whole = true;
                if (matchLen >= 1 && matchStart - 1 >= 0) {
                    char charBeforeMach = input.charAt(matchStart - 1);
                    if (! boundaryTest(charBeforeMach))
                        whole = false;
                }
                if (i + 1 < inputLength) {
                    char charAheadMatch = input.charAt(i + 1);
                    if (! boundaryTest(charAheadMatch))
                        whole = false;
                }
                if (! whole)
                    continue;
            }

            int unmatchLen = matchStart - pos;
            buf.append(input, pos, unmatchLen);

            String replacement = match.getData();
            buf.append(replacement);

            pos = i + 1;
        }

        String remaining = input.substring(pos);
        buf.append(remaining);
        return buf.toString();
    }

    boolean boundaryTest(int codepoint) {
        return ! Character.isAlphabetic(codepoint);
    }

    public void loadReplacements() {
        for (Class<?> newType : IndexedTypes.list(Replacement.class, true)) {
            Replacement aRepl = newType.getAnnotation(Replacement.class);
            Class<?> oldType = aRepl.value();

            if (oldType == Replacement.superclass.class) {
                oldType = newType.getSuperclass();
                if (oldType == null)
                    throw new IllegalUsageException(String.format(//
                            "No superclass for %s", oldType));
            }

            String oldName = oldType.getName();
            String newName = newType.getName();
            DefaultNode<Character, String> node = trie.findOrCreate(oldName);
            if (node != null && node.getData() != null)
                throw new DuplicatedKeyException(oldName, node, "node data: " + node.getData());

            node.setData(newName);
        }
    }

    static final TextReplacer indexed = new TextReplacer(true);
    static {
        indexed.loadReplacements();
    }

    public static TextReplacer getIndexed() {
        return indexed;
    }

}
