package net.bodz.bas.files;

public interface MapRecordBuilder<T> extends RecordBuilder<T> {

    void add(Object key, Object value);

}
