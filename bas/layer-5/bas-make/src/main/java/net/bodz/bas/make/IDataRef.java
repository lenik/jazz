package net.bodz.bas.make;

import java.time.ZonedDateTime;

import net.bodz.bas.meta.decl.NotNull;

public interface IDataRef<T> {

    @NotNull
    Class<T> getDataType();

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
