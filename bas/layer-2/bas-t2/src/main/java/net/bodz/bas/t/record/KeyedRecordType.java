package net.bodz.bas.t.record;

public class KeyedRecordType<T extends IKeyed<K>, K>
        extends BasicRecordType<T>
        implements IKeyedRecordType<T, K> {

    final IColumnType<T, ?> keyColumn;

    @SafeVarargs
    public KeyedRecordType(IColumnType<T, ?> keyColumn, IColumnType<T, ?>... columns) {
        super(columns);
        this.keyColumn = keyColumn;
        this.columns.add(0, keyColumn);
    }

    @SafeVarargs
    public static <T extends IKeyed<K>, K> KeyedRecordType<T, K>  //
    ofList(IColumnType<T, K> keyColumn, IColumnType<T, ?>... columns) {
        return new KeyedRecordType<>(keyColumn, columns);
    }

    @Override
    public IColumnType<T, ?> getKeyColumn() {
        return keyColumn;
    }

}
