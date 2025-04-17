package net.bodz.bas.t.record;

public interface IKeyedRecordType<T, K>
        extends IRecordType<T> {

    IColumnType<T, ?> getKeyColumn();

}
