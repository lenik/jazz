package net.bodz.bas.text.trie;

import java.io.ByteArrayOutputStream;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.Replacement;

public class BinaryReplacer {

    private final ByteTrie<byte[]> trie = new ByteTrie<>();
    private boolean wholeOnly = true;

    public BinaryReplacer(boolean wholeOnly) {
        this.wholeOnly = wholeOnly;
    }

    public BinaryReplacer replace(byte[] pattern, byte[] replacement) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (replacement == null)
            throw new NullPointerException("replacement");
        trie.resolve(pattern).define(replacement);
        return this;
    }

    public byte[] transform(byte[] input) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(input.length);
        int[] heads = trie.scanTries(input);
        int pos = 0;
        for (int i = 0; i < heads.length; i++) {
            int head = heads[i];
            if (head != -1) {
                if (wholeOnly) {
                    boolean whole = true;
                    if (head >= 1) {
                        int back = input[head - 1] & 0xFF;
                        if (!boundaryTest(back))
                            whole = false;
                    }
                    if (i + 1 < heads.length) {
                        int ahead = input[i + 1] & 0xFF;
                        if (!boundaryTest(ahead))
                            whole = false;
                    }
                    if (!whole)
                        continue;
                }
                buf.write(input, pos, head - pos);

                byte[] replacement = trie.resolve(input, head, i + 1 - head).getData();
                buf.write(replacement, 0, replacement.length);

                pos = i + 1;
            }
        }

        buf.write(input, pos, input.length - pos);

        return buf.toByteArray();
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
                    throw new IllegalUsageException(String.format("No superclass for %s", oldType));
            }

            byte[] olddata = oldType.getName().getBytes();
            byte[] newdata = newType.getName().getBytes();
            ByteTrie.Node<byte[]> node = trie.resolve(olddata);
            if (node != null && node.getData() != null)
                throw new DuplicatedKeyException(
                        String.format("%s is already replaced by %s.", olddata, node.getData()));

            node.define(newdata);
        }
    }

    static final BinaryReplacer indexed = new BinaryReplacer(true);
    static {
        indexed.loadReplacements();
    }

    public static BinaryReplacer getIndexed() {
        return indexed;
    }

}
