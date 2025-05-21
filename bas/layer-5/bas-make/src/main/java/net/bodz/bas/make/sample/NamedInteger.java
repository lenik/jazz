package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedInteger
        implements IDataEntry<String, Integer> {

    String key;
    Integer data;
    ZonedDateTime time;

    public NamedInteger() {
    }

    public NamedInteger(String key) {
        this.key = key;
    }

    public NamedInteger(String key, Integer data) {
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
    public Class<Integer> getDataType() {
        return Integer.class;
    }

    @Override
    public Integer getData() {
        return data;
    }

    @Override
    public void setData(Integer data) {
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
