package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedString
        implements IDataEntry<String, String> {

    String key;
    String data;
    ZonedDateTime time;

    public NamedString() {
    }

    public NamedString(String key) {
        this.key = key;
    }

    public NamedString(String key, String data) {
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
    public Class<String> getDataType() {
        return String.class;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
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
