package net.bodz.bas.db.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.c.string.Strings;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {

    String value();

    class fn {

        public static String tablename(Class<?> type) {
            TableName aTableName = type.getAnnotation(TableName.class);
            if (aTableName != null)
                return aTableName.value();
            else
                return type.getSimpleName().toLowerCase();
        }

        public static String TABLENAME(Class<?> type) {
            TableName aTableName = type.getAnnotation(TableName.class);
            if (aTableName != null)
                return aTableName.value();
            else
                return type.getSimpleName().toUpperCase();
        }

        public static String tableName(Class<?> type) {
            TableName aTableName = type.getAnnotation(TableName.class);
            if (aTableName != null)
                return aTableName.value();
            else
                return Strings.lcfirst(type.getSimpleName());
        }

        public static String table_name(Class<?> type) {
            TableName aTableName = type.getAnnotation(TableName.class);
            if (aTableName != null)
                return aTableName.value();
            else
                return Strings.hyphenatize(type.getSimpleName()).toLowerCase();
        }

        public static String TABLE_NAME(Class<?> type) {
            TableName aTableName = type.getAnnotation(TableName.class);
            if (aTableName != null)
                return aTableName.value();
            else
                return Strings.hyphenatize(type.getSimpleName()).toUpperCase();
        }

    }

}
