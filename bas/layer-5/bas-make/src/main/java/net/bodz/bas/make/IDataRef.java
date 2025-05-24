package net.bodz.bas.make;

import java.time.ZonedDateTime;

public interface IDataRef<T>
        extends IDataType<T> {

    T getData();

    void setData(T data);

    default boolean exists() {
        return getData() != null;
    }

    ZonedDateTime getLastModified();

    default long getVersion() {
        ZonedDateTime time = getLastModified();
        return time == null ? 0 : time.toInstant().toEpochMilli();
    }

}
