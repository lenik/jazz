package net.bodz.lily.tool.javagen;

import net.bodz.bas.t.catalog.IColumnMetadata;

public class ColumnUtils {

    public static boolean isPreferredToGenerate(IColumnMetadata column) {
        if (column.isAutoIncrement())
            return true;
        if (column.isPrimaryKey()) {
            String defaultValue = column.getDefaultValue();
            if (defaultValue != null) {
                if (defaultValue.contains("nextval"))
                    return true;
            }
        }
        return false;
    }

    public static boolean isIgnoredInCreation(IColumnMetadata column) {
        if (isPreferredToGenerate(column))
            return true;
        return false;
    }

}
