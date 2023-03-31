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

public class GeoZones {

    private static final GeoZone china = new GeoZone("", "中国", null);
    private static final Map<String, GeoZone> codeMap = new HashMap<String, GeoZone>();

    public static GeoZone getChina() {
        return china;
    }

    public static GeoZone getChinaRegion(String code) {
        return codeMap.get(code);
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
        URL csv = GeoZones.class.getResource("china-2013.csv");
        InputStream in = csv.openStream();

        try {
            Reader reader = new InputStreamReader(in, Charsets.UTF_8);
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

                String fullCode = line.substring(0, colon);
                String zhName = line.substring(colon + 1);
                GeoZone parent;
                String localCode;

                // AABBCCDDD
                switch (fullCode.length()) {
                case 2:
                    parent = china;
                    localCode = fullCode;
                    break;

                case 4:
                    parent = codeMap.get(fullCode.substring(0, 2));
                    localCode = fullCode.substring(2);
                    break;

                case 6:
                    parent = codeMap.get(fullCode.substring(0, 4));
                    localCode = fullCode.substring(4);
                    break;

                case 9:
                    parent = codeMap.get(fullCode.substring(0, 6));
                    localCode = fullCode.substring(6);
                    break;

                default:
                    throw new ParseException("Bad ID: " + fullCode);
                }

                GeoZone zone = new GeoZone(localCode, zhName, parent);
                zone.setZhName(zhName);
                codeMap.put(fullCode, zone);
            }
        } finally {
            in.close();
        }
    }

}
