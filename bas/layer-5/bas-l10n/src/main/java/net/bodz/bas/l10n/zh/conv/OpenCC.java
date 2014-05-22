package net.bodz.bas.l10n.zh.conv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.IllegalConfigException;

public class OpenCC {

    private List<OpenCCDatrie> tries = new ArrayList<OpenCCDatrie>();

    public OpenCC()
            throws IOException {
        this(new String[] { //
                "simp_to_trad_characters.ocd", //
                        "simp_to_trad_phrases.ocd", //
                });
    }

    public OpenCC(String... dicts)
            throws IOException {
        if (DATADIR == null)
            throw new IllegalConfigException("libopencc1 isn't available.");

        for (String dict : dicts) {
            File dictFile = new File(DATADIR, dict);
            if (!dictFile.exists())
                throw new IllegalArgumentException("Dict file isn't available: " + dictFile);

            OpenCCDatrie trie = new OpenCCDatrie();
            trie.parseOcd(dictFile);
            tries.add(trie);
        }
    }

    public String convert(String str) {
        if (tries.isEmpty())
            return str;

        int[] mapbuf = new int[100];
        StringBuilder sb = new StringBuilder(str.length() * 5 / 4);

        while (!str.isEmpty()) {
            int maxlen = 0;
            String maxword = null;

            for (OpenCCDatrie trie : tries) {
                int n = trie.convert(str, mapbuf);
                if (n == 0)
                    continue;
                int len = mapbuf[n - 2];
                if (len > maxlen) {
                    maxlen = len;
                    maxword = trie.word(mapbuf[n - 1]);
                }
            }

            if (maxlen > 0) {
                sb.append(maxword);
                str = str.substring(maxlen);
            } else {
                sb.append(str.charAt(0));
                str = str.substring(1);
            }
        }
        return sb.toString();
    }

    static File DATADIR;
    static {
        String[] libdirs = { //
        "/usr/lib/", //
                "/usr/lib/x86_64-linux-gnu", //
                "/usr/lib/i386-linux-gnu", //
        };
        for (String libdir : libdirs) {
            File openccdir = new File(libdir + "/opencc");
            if (openccdir.exists()) {
                DATADIR = openccdir;
                break;
            }
        }
    }

}
