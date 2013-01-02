package net.bodz.bas.l10n.zh.acl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import net.bodz.bas.c.java.net.URLData;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.text.IMessageModifier;

public class AntiCensorshipMessageModifier
        implements IMessageModifier {

    private Random random = new Random();

    @Override
    public String transform(String input)
            throws RuntimeException {
        int len = input.length();

        IPrintOut buf = new BCharOut(input.length());
        int last = 0;
        List<String> lastDst = null;

        for (int i = 1; i <= len; i++) {
            String substr = input.substring(last, i);

            String ceil = map.ceilingKey(substr);
            List<String> dst = map.get(substr);

            if (dst == null || i == len) {
                if (lastDst != null) {
                    if (!lastDst.isEmpty()) {
                        String pick;
                        if (lastDst.size() == 1)
                            pick = lastDst.get(0);
                        else {
                            int rand = random.nextInt(lastDst.size());
                            pick = lastDst.get(rand);
                        }
                        buf.print(pick);
                    }
                    lastDst = null;
                } else {
                    buf.print(substr);
                }
                last = i;
            } else {
                lastDst = dst;
            }
        }

        return buf.toString();
    }

    static TreeMap<String, List<String>> map;
    static {
        map = new TreeMap<>();

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

                map.put(src, dsts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
