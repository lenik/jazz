package net.bodz.bas.make.util;

import java.time.ZonedDateTime;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IKeyDataBuilder;
import net.bodz.bas.meta.decl.NotNull;

public abstract class NamedData<T>
        implements IKeyData<String, T>,
                   INamed {

    String key;
    T data;
    ZonedDateTime time;

    public NamedData() {
    }

    public NamedData(String key) {
        this.key = key;
    }

    public NamedData(String key, T data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public String getName() {
        return key;
    }

    @Override
    public void setName(String name) {
        this.key = name;
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

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return time;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getKeyType().getSimpleName());
        buf.append(" ").append(key);
        if (exists())
            buf.append(" = ").append(data);
        return buf.toString();
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, T>
            implements IKeyDataBuilder<self_t, String, T> {

        protected String key;
        protected T data;

        @Override
        public self_t key(@NotNull String value) {
            this.key = value;
            return (self_t) this;
        }

        @Override
        public self_t data(T value) {
            this.data = value;
            return (self_t) this;
        }

    }

}
