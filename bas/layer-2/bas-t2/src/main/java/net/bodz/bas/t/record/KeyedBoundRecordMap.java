package net.bodz.bas.t.record;

public class KeyedBoundRecordMap<K, T extends IColumnChangeSource & IKeyed<K>>
        extends BoundRecordMap<K, T>
        implements IKeyedRecordMap<K, T> {

    public KeyedBoundRecordMap(IKeyedRecordType<T, K> recordType) {
        super(recordType);
    }

}
