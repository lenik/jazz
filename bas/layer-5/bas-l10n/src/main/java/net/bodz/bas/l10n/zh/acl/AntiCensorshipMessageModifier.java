package net.bodz.bas.l10n.zh.acl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.net.URLData;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.text.IMessageModifier;
import net.bodz.bas.text.trie.DefaultNode;

public class AntiCensorshipMessageModifier
        extends SubstTrie
        implements
            IMessageModifier {

    private AntiCensorshipMessageModifier() {
    }

    public static final AntiCensorshipMessageModifier INSTANCE = new AntiCensorshipMessageModifier();

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

                DefaultNode<Character, List<String>> node = INSTANCE.findOrCreate(src);
                node.setData(dsts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
