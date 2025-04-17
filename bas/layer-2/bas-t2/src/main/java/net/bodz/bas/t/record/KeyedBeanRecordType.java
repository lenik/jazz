package net.bodz.bas.t.record;

public class KeyedBeanRecordType<T extends IKeyed<K>, K>
        extends BeanRecordType<T>
        implements IKeyedRecordType<T, K> {

    final IColumnType<T, ?> keyColumn;

    @SafeVarargs
    public KeyedBeanRecordType(IColumnType<T, ?> keyColumn, IColumnType<T, ?>... columns) {
        super(columns);
        this.keyColumn = keyColumn;
        this.columns.add(0, keyColumn);
    }

    @SafeVarargs
    public static <T extends IKeyed<K>, K> KeyedBeanRecordType<T, K>  //
    ofList(IColumnType<T, K> keyColumn, IColumnType<T, ?>... columns) {
        return new KeyedBeanRecordType<>(keyColumn, columns);
    }

    @Override
    public IColumnType<T, ?> getKeyColumn() {
        return keyColumn;
    }

}
