package net.bodz.bas.make;

import java.time.ZonedDateTime;

public interface IMakeTarget {

    default long getVersion() {
        ZonedDateTime time = getLastModified();
        if (time != null)
            return time.toInstant().toEpochMilli();
        else
            return 0;
    }

    ZonedDateTime getLastModified();

    String getDisplayName();

    boolean exists();

}
