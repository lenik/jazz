package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedDouble
        implements IDataEntry<String, Double> {

    String key;
    Double data;
    ZonedDateTime time;

    public NamedDouble() {
    }

    public NamedDouble(String key) {
        this.key = key;
    }

    public NamedDouble(String key, Double data) {
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
    public Class<Double> getDataType() {
        return Double.class;
    }

    @Override
    public Double getData() {
        return data;
    }

    @Override
    public void setData(Double data) {
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
