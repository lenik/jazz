package net.bodz.bas.t.record;

public class KeyedRecordMap<K, T extends IKeyed<K>>
        extends BasicRecordMap<K, T>
        implements IKeyedRecordMap<K, T> {

    public KeyedRecordMap(IKeyedRecordType<T, K> recordType) {
        super(recordType);
    }

}
