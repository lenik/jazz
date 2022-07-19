package net.bodz.bas.db.meta;

import javax.persistence.Table;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;

public class TableUtils {

    public static String aTableName(Class<?> type) {
        Table aTable = type.getAnnotation(Table.class);
        if (aTable == null)
            return null;
        String name = aTable.name();
        if (name.isEmpty())
            return null;
        return name;
    }

    public static String tablename(Class<?> type) {
        String name = aTableName(type);
        return name != null ? name : type.getSimpleName().toLowerCase();
    }

    public static String TABLENAME(Class<?> type) {
        String name = aTableName(type);
        return name != null ? name : type.getSimpleName().toUpperCase();
    }

    public static String tableName(Class<?> type) {
        String name = aTableName(type);
        return name != null ? name : Strings.lcfirst(type.getSimpleName());
    }

    public static String table_name(Class<?> type) {
        String name = aTableName(type);
        return name != null ? name : StringId.UL.breakCamel(type.getSimpleName()).toLowerCase();
    }

    public static String TABLE_NAME(Class<?> type) {
        String name = aTableName(type);
        return name != null ? name : StringId.UL.breakCamel(type.getSimpleName()).toUpperCase();
    }

}
