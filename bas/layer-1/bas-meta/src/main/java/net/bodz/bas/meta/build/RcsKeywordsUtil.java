package net.bodz.bas.meta.build;

import java.text.ParseException;
import java.util.Date;

public class RcsKeywordsUtil {

    public static ReleaseDescription parseId(String rcs_id) {
        rcs_id = rcs_id.substring(0, rcs_id.length() - 1);
        String[] parts = rcs_id.split("\\s+");

        ReleaseDescription release = new ReleaseDescription();
        long releaseTime = 0;

        switch (parts.length) {
        case 7: // state
            // skip

        case 6: // lenik
            release.author = parts[5];

        case 5: // 10:53:242
            long _time = 0;
            try {
                _time = RcsDates.parseTime(parts[4]).getTime();
                releaseTime += _time;
            } catch (ParseException e) {
            }

        case 4: // 2008-01-15
            long _date = 0;
            try {
                _date = RcsDates.parseDate(parts[3]).getTime();
                releaseTime += _date;
            } catch (ParseException e) {
            }

        case 3: // 784
            String[] revisions = parts[2].split("\\.");
            VersionTuple versionTuple = new VersionTuple(revisions);
            release.setVersion(versionTuple);

        case 2: // Rcs.java
            release.name = parts[1];

        case 1: // $Id:
        }

        release.setReleaseDate(new Date(releaseTime));
        return release;
    }

    public static ReleaseDescription getVersionInfo(RcsKeywords keywords) {
        return parseId(keywords.id());
    }

    public static ReleaseDescription getVersionInfo(Class<?> clazz) {
        return getVersionInfo(clazz.getAnnotation(RcsKeywords.class));
    }

}
