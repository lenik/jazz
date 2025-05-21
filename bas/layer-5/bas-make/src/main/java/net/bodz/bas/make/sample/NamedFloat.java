package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedFloat
        implements IDataEntry<String, Float> {

    String key;
    Float data;
    ZonedDateTime time;

    public NamedFloat() {
    }

    public NamedFloat(String key) {
        this.key = key;
    }

    public NamedFloat(String key, Float data) {
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
    public Class<Float> getDataType() {
        return Float.class;
    }

    @Override
    public Float getData() {
        return data;
    }

    @Override
    public void setData(Float data) {
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
