package net.bodz.bas.l10n.zh.acl;

import java.util.List;
import java.util.Random;

import net.bodz.bas.t.vector.Vector;
import net.bodz.bas.t.vector.Vectors;
import net.bodz.bas.text.IMessageModifier;
import net.bodz.bas.text.trie.DefaultCharTrie;
import net.bodz.bas.text.trie.DefaultNode;

public class SubstTrie
        extends DefaultCharTrie<List<String>>
        implements
            IMessageModifier {

    private Random random = new Random();

    @Override
    public String apply(String input) {
        int length = input.length();
        StringBuilder sb = new StringBuilder(input.length() * 2);
        Vector<DefaultNode<Character, List<String>>> matchv = findAll(Vectors.ofString(input));
        int pos = 0;
        for (int i = 0; i < length; i++) {
            DefaultNode<Character, List<String>> match = matchv.get(i);
            if (match == null)
                continue;

            int matchLen = match.depth;
            int matchStart = i - (matchLen - 1);

            if (pos < matchStart)
                sb.append(input.substring(pos, matchStart));
            else if (pos > matchStart)
                assert false;

            List<String> replacements = match.getData();
            String replacement = replacements.get(random.nextInt(replacements.size()));
            sb.append(replacement);

            pos = i + 1;
        }

        String remaining = input.substring(pos);
        sb.append(remaining);

        return sb.toString();
    }

}
