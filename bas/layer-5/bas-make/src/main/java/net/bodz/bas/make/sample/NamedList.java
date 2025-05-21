package net.bodz.bas.make.sample;

import java.time.ZonedDateTime;
import java.util.List;

import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class NamedList<E>
        implements IDataEntry<String, List<E>> {

    String key;
    List<E> data;
    ZonedDateTime time;

    public NamedList() {
    }

    public NamedList(String key) {
        this.key = key;
    }

    public NamedList(String key, List<E> data) {
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

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<List<E>> getDataType() {
        return (Class<List<E>>) (Class<?>) List.class;
    }

    @Override
    public List<E> getData() {
        return data;
    }

    @Override
    public void setData(List<E> data) {
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
