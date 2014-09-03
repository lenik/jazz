package net.bodz.bas.l10n.zh.acl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.java.net.URLData;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.text.IMessageModifier;
import net.bodz.bas.text.trie.CharTrie;

public class AntiCensorshipMessageModifier
        implements IMessageModifier {

    private Random random = new Random();

    @Override
    public String transform(String input)
            throws RuntimeException {
        StringBuilder sb = new StringBuilder(input.length() * 2);
        int[] root = breakText(input, trie);
        int start = 0;
        for (int end = 1; end < root.length; end++) {
            int head = root[end];
            if (head != -1) {
                sb.append(input.substring(start, head));

                String word = input.substring(head, end);
                List<String> replacements = trie.resolve(word).getData();
                String replacement = replacements.get(random.nextInt(replacements.size()));
                sb.append(replacement);

                start = end;
            }
        }

        String remaining = input.substring(start);
        sb.append(remaining);

        return sb.toString();
    }

    static int[] breakText(String s, CharTrie<?> trieRoot) {
        int len = s.length();
        int root[] = new int[len + 1];
        Arrays.fill(root, -1);
        for (int start = 0; start < len; start++)
            // if (start == 0 || root[start] >= 0)
            breakText(s, start, len, root, trieRoot);
        return root;
    }

    static void breakText(String s, int start, int end, int root[], CharTrie<?> trie) {
        CharTrie.Node<?> node = trie.getRoot();
        for (int i = start; i < end; i++) {
            char ch = s.charAt(i);
            node = node.getChild(ch);
            if (node != null) {
                if (node.isDefined())
                    if (root[i + 1] == -1)
                        root[i + 1] = start;
            } else
                break;
        }
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
