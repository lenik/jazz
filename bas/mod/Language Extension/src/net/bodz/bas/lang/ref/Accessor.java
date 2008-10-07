package net.bodz.bas.lang.ref;

public interface Accessor<T> extends Ref<T> {

    Class<?> getType();

    boolean isReadable();

    boolean isWritable();

}
