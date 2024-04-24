package net.bodz.bas.text.trie;

import java.io.ByteArrayOutputStream;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.Replacement;
import net.bodz.bas.t.vector.Vector;

public class BinaryReplacer {

    private final DefaultByteTrie<byte[]> trie = new DefaultByteTrie<>();
    private boolean wholeOnly = true;

    public BinaryReplacer(boolean wholeOnly) {
        this.wholeOnly = wholeOnly;
    }

    public BinaryReplacer replace(byte[] pattern, byte[] replacement) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (replacement == null)
            throw new NullPointerException("replacement");
        trie.findOrCreate(pattern).setData(replacement);
        return this;
    }

    public byte[] transform(byte[] input) {
        int inputLength = input.length;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(inputLength);
        Vector<DefaultNode<Byte, byte[]>> matchv = trie.findAll(input);
        int pos = 0;
        for (int i = 0; i < inputLength; i++) {
            DefaultNode<Byte, byte[]> match = matchv.get(i);
            if (match == null)
                continue;

            int matchLen = match.depth;
            int matchStart = i - (matchLen - 1);
            if (wholeOnly) {
                boolean whole = true;
                if (matchLen >= 1 && matchStart - 1 >= 0) {
                    int byteBeforeMach = input[matchStart - 1] & 0xFF;
                    if (! boundaryTest(byteBeforeMach))
                        whole = false;
                }
                if (i + 1 < inputLength) {
                    int byteAheadMatch = input[i + 1] & 0xFF;
                    if (! boundaryTest(byteAheadMatch))
                        whole = false;
                }
                if (! whole)
                    continue;
            }

            int unmatchLen = matchStart - pos;
            buf.write(input, pos, unmatchLen);

            byte[] replacement = match.getData();
            buf.write(replacement, 0, replacement.length);

            pos = i + 1;
        }

        buf.write(input, pos, inputLength - pos);
        return buf.toByteArray();
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
                    throw new IllegalUsageException(String.format("No superclass for %s", oldType));
            }

            byte[] olddata = oldType.getName().getBytes();
            byte[] newdata = newType.getName().getBytes();
            DefaultNode<Byte, byte[]> node = trie.findOrCreate(olddata);
            if (node != null && node.getData() != null)
                throw new DuplicatedKeyException(olddata, node, "node data: " + node.getData());

            node.setData(newdata);
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
