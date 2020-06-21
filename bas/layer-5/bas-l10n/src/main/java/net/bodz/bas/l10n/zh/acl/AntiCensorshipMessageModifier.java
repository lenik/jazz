package net.bodz.bas.l10n.zh.acl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.java.net.URLData;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.fn.AbstractTransformer;
import net.bodz.bas.text.IMessageModifier;
import net.bodz.bas.text.trie.CharTrie;

public class AntiCensorshipMessageModifier
        extends AbstractTransformer<String, String>
        implements IMessageModifier {

    private Random random = new Random();

    @Override
    public String transform(String input)
            throws RuntimeException {
        StringBuilder sb = new StringBuilder(input.length() * 2);
        int[] heads = trie.scanTries(input);
        int pos = 0;
        for (int i = 0; i < heads.length; i++) {
            int head = heads[i];
            if (head != -1) {
                sb.append(input.substring(pos, head));

                String word = input.substring(head, i + 1);
                List<String> replacements = trie.resolve(word).getData();
                String replacement = replacements.get(random.nextInt(replacements.size()));
                sb.append(replacement);

                pos = i + 1;
            }
        }

        String remaining = input.substring(pos);
        sb.append(remaining);

        return sb.toString();
    }

    static CharTrie<List<String>> trie = new CharTrie<List<String>>();

    static {
        URL csvURL = ClassResource.getDataURL(AntiCensorshipMessageModifier.class, "csv");
        if (csvURL == null)
            throw new NullPointerException("csvURL");

        try {
            for (String line : URLData.readLines(csvURL, "utf-8")) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#"))
                    continue;

                String fields[] = line.split(",");

                String src = fields[0];
                List<String> dsts = new ArrayList<String>();
                for (int i = 1; i < fields.length; i++) {
                    dsts.add(fields[i].trim());
                }

                trie.resolve(src).define(dsts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
