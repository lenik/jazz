package net.bodz.bas.t.record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicRecordType<T>
        implements IRecordType<T> {

    final List<IColumnType<T, ?>> columns;

    @SafeVarargs
    public BasicRecordType(IColumnType<T, ?>... columns) {
        this.columns = new ArrayList<>();
        this.columns.addAll(Arrays.asList(columns));
    }

    @SafeVarargs
    public static <T> BasicRecordType<T> ofList(IColumnType<T, ?>... columns) {
        return new BasicRecordType<>(columns);
    }

    @Override
    public List<? extends IColumnType<T, ?>> getColumns() {
        return Collections.unmodifiableList(columns);
    }

}
