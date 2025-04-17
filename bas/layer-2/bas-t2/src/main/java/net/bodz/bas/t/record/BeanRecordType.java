package net.bodz.bas.t.record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeanRecordType<T>
        implements IRecordType<T> {

    final List<IColumnType<T, ?>> columns;

    @SafeVarargs
    public BeanRecordType(IColumnType<T, ?>... columns) {
        this.columns = new ArrayList<>();
        this.columns.addAll(Arrays.asList(columns));
    }

    @SafeVarargs
    public static <T> BeanRecordType<T> ofList(IColumnType<T, ?>... columns) {
        return new BeanRecordType<>(columns);
    }

    @Override
    public List<? extends IColumnType<T, ?>> getColumns() {
        return Collections.unmodifiableList(columns);
    }

}
