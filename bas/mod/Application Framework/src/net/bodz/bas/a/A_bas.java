package net.bodz.bas.a;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.NotImplementedException;

@RcsKeywords(id = "$Id: Rcs.java 784 2008-01-15 10:53:24Z lenik $")
public class A_bas {

    public static String parse(Doc docN) {
        if (docN == null)
            return null;
        String[] doc = docN.value();
        if (doc.length == 0)
            return null;
        if (doc.length == 1)
            return parseDocString(doc[0]);
        StringBuffer buf = new StringBuffer(doc.length * 80);
        for (int i = 0; i < doc.length; i++) {
            // if (i != 0) buf.append('\n');
            buf.append(parseDocString(doc[i]));
        }
        return buf.toString();
    }

    static String parseDocString(String doc) {
        if (doc.startsWith("#")) {
            // #bundle.property
            throw new NotImplementedException();
        } else if (doc.startsWith("/")) {
            // doc = doc.substring(1);
            try {
                doc = Files.readAll(doc, "utf-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return doc;
    }

    static DateFormat DATETIME;
    static DateFormat DATE;
    static DateFormat TIME;
    static {
        DATE = new SimpleDateFormat("yyyy-MM-dd");
        TIME = new SimpleDateFormat("hh:mm:sss");
        DATETIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
    }

    public static VersionInfo parseId(String id) {
        id = id.substring(0, id.length() - 1);
        String[] parts = id.split("\\s+");
        VersionInfo info = new VersionInfo();
        switch (parts.length) {
        case 7: // state
            info.state = parts[6];
        case 6: // lenik
            info.author = parts[5];
        case 5: // 10:53:242
            long _time = 0;
            try {
                _time = TIME.parse(parts[4]).getTime();
            } catch (ParseException e) {
            }
            info.time += _time;
        case 4: // 2008-01-15
            long _date = 0;
            try {
                _date = DATE.parse(parts[3]).getTime();
            } catch (ParseException e) {
            }
            info.time += _date;
        case 3: // 784
            String[] revs = parts[2].split("\\.");
            info.revision = new int[revs.length];
            for (int i = 0; i < revs.length; i++)
                info.revision[i] = Integer.parseInt(revs[i]);
        case 2: // Rcs.java
            info.name = parts[1];
        case 1: // $Id:
        }
        return info;
    }

    public static VersionInfo parseId(RcsKeywords keywords) {
        return parseId(keywords.id());
    }

    public static VersionInfo parseId(Class<?> clazz) {
        return parseId(clazz.getAnnotation(RcsKeywords.class));
    }

}
