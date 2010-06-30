package net.bodz.bas.c1.annotations.util;

import java.text.ParseException;

import net.bodz.bas.c1.annotations.RcsKeywords;
import net.bodz.bas.c1.util.AnnotationParseUtil;
import net.bodz.bas.c1.util.Dates;

/**
 * @test RcsKeywordsUtilTest
 */
public class RcsKeywordsUtil
        extends AnnotationParseUtil {

    public static VersionInfo parseId(String rcs_id) {
        rcs_id = rcs_id.substring(0, rcs_id.length() - 1);
        String[] parts = rcs_id.split("\\s+");
        VersionInfo info = new VersionInfo();
        switch (parts.length) {
        case 7: // state
            info.state = parts[6];
        case 6: // lenik
            info.author = parts[5];
        case 5: // 10:53:242
            long _time = 0;
            try {
                _time = Dates.timeFormat.parse(parts[4]).getTime();
            } catch (ParseException e) {
            }
            info.time += _time;
        case 4: // 2008-01-15
            long _date = 0;
            try {
                _date = Dates.dateFormat.parse(parts[3]).getTime();
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

    public static VersionInfo getVersionInfo(RcsKeywords keywords) {
        return parseId(keywords.id());
    }

    public static VersionInfo getVersionInfo(Class<?> clazz) {
        return getVersionInfo(clazz.getAnnotation(RcsKeywords.class));
    }

}
