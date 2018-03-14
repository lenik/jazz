package net.bodz.bas.text.trie;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.Replacement;

public class TextReplacer {

    private final CharTrie<String> trie = new CharTrie<String>();
    private boolean wholeOnly = true;

    public TextReplacer(boolean wholeOnly) {
        this.wholeOnly = wholeOnly;
    }

    public TextReplacer replace(String pattern, String replacement) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (replacement == null)
            throw new NullPointerException("replacement");
        trie.resolve(pattern).define(replacement);
        return this;
    }

    public String transform(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        int[] heads = trie.scanTries(input);
        int pos = 0;
        for (int i = 0; i < heads.length; i++) {
            int head = heads[i];
            if (head != -1) {
                if (wholeOnly) {
                    boolean whole = true;
                    if (head >= 1) {
                        char back = input.charAt(head - 1);
                        if (!boundaryTest(back))
                            whole = false;
                    }
                    if (i + 1 < heads.length) {
                        char ahead = input.charAt(i + 1);
                        if (!boundaryTest(ahead))
                            whole = false;
                    }
                    if (!whole)
                        continue;
                }
                sb.append(input.substring(pos, head));

                String word = input.substring(head, i + 1);
                String replacement = trie.resolve(word).getData();
                sb.append(replacement);

                pos = i + 1;
            }
        }

        String remaining = input.substring(pos);
        sb.append(remaining);

        return sb.toString();
    }

    boolean boundaryTest(int codepoint) {
        return !Character.isAlphabetic(codepoint);
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
            CharTrie.Node<String> node = trie.resolve(oldName);
            if (node != null && node.getData() != null)
                throw new DuplicatedKeyException(String.format(//
                        "%s is already replaced by %s.", oldName, node.getData()));

            node.define(newName);
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
