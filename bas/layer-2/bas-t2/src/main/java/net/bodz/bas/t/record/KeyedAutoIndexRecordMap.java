package net.bodz.bas.t.record;

public class KeyedAutoIndexRecordMap<K, T extends IColumnChangeSource & IKeyed<K>>
        extends AutoIndexRecordMap<K, T>
        implements IKeyedRecordMap<K, T> {

    public KeyedAutoIndexRecordMap(IKeyedRecordType<T, K> recordType) {
        super(recordType);
    }

}
