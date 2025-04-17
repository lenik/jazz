package net.bodz.bas.t.record;

public class KeyedRecordMap<T extends IKeyed<K>, K>
        extends RecordMap<K, T>
        implements IKeyedRecordMap<K, T> {

    public KeyedRecordMap(IRecordType<T> recordType) {
        super(recordType);
    }

}
