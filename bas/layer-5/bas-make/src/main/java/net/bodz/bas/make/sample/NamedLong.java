package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedLong
        implements IDataEntry<String, Long> {

    String key;
    Long data;
    ZonedDateTime time;

    public NamedLong() {
    }

    public NamedLong(String key) {
        this.key = key;
    }

    public NamedLong(String key, Long data) {
        this.key = key;
        this.data = data;
    }

    @NotNull
    @Override
    public Class<String> getKeyType() {
        return String.class;
    }

    @NotNull
    @Override
    public String getKey() {
        return key;
    }

    @NotNull
    @Override
    public Class<Long> getDataType() {
        return Long.class;
    }

    @Override
    public Long getData() {
        return data;
    }

    @Override
    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return time;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(key);
        if (exists())
            buf.append(" = ").append(data);
        return buf.toString();
    }

}
