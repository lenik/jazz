package net.bodz.bas.i18n.geo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.ParseException;

public class GeoRegions {

    private static final GeoRegion china = new GeoRegion("", "中国", null);
    private static final Map<String, GeoRegion> chinaIndex = new HashMap<String, GeoRegion>();

    public static GeoRegion getChina() {
        return china;
    }

    public static GeoRegion getChinaRegion(String id) {
        return chinaIndex.get(id);
    }

    static {
        try {
            loadChinaRegions();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void loadChinaRegions()
            throws IOException, ParseException {
        URL csv = GeoRegions.class.getResource("china-2013.csv");
        InputStream in = csv.openStream();

        try {
            Reader reader = new InputStreamReader(in, Charsets.UTF8);
            BufferedReader br = new BufferedReader(reader);
            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
                if (line.startsWith("#"))
                    continue;

                int colon = line.indexOf(':');
                if (colon == -1)
                    throw new ParseException("Expected colon(:) at line " + lineNo);
                lineNo++;

                String id = line.substring(0, colon);
                String zhName = line.substring(colon + 1);
                GeoRegion parent;
                String code;

                // AABBCCDDD
                switch (id.length()) {
                case 2:
                    parent = china;
                    code = id;
                    break;

                case 4:
                    parent = chinaIndex.get(id.substring(0, 2));
                    code = id.substring(2);
                    break;

                case 6:
                    parent = chinaIndex.get(id.substring(0, 4));
                    code = id.substring(4);
                    break;

                case 9:
                    parent = chinaIndex.get(id.substring(0, 6));
                    code = id.substring(6);
                    break;

                default:
                    throw new ParseException("Bad ID: " + id);
                }

                GeoRegion region = new GeoRegion(code, zhName, parent);
                region.setZhName(zhName);
                chinaIndex.put(id, region);
            }
        } finally {
            in.close();
        }
    }

}
