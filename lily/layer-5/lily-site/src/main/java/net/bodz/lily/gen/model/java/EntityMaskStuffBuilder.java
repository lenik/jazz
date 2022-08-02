package net.bodz.lily.gen.model.java;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.range.*;
import net.bodz.lily.model.base.CoObjectMask;

public class EntityMaskStuffBuilder
        extends EntityStuffBuilder {

    public EntityMaskStuffBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        out.printf("public class %s\n", fragmentName);
        out.printf("        extends %s {\n", //
                imports.simple(CoObjectMask.class));
        out.enter();
        {
//            out.println();
//            out.println("private static final long serialVersionUID = 1L;");

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnFields(column);
            }

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnAccessors(column);
            }

            out.leave();
        }
        out.println();
        out.println("}");
    }

    static Map<Class<?>, Class<?>> rangeMapping;
    static {
        rangeMapping = new HashMap<>();
        rangeMapping.put(Short.class, ShortRange.class);
        rangeMapping.put(Integer.class, IntegerRange.class);
        rangeMapping.put(Long.class, LongRange.class);
        rangeMapping.put(Float.class, FloatRange.class);
        rangeMapping.put(Double.class, DoubleRange.class);

        rangeMapping.put(BigInteger.class, BigIntegerRange.class);
        rangeMapping.put(BigDecimal.class, BigDecimalRange.class);

        rangeMapping.put(Date.class, DateTimeRange.class);
        rangeMapping.put(java.sql.Date.class, DateTimeRange.class);
        rangeMapping.put(Timestamp.class, DateTimeRange.class);
    }

    void columnFields(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        out.println(imports.simple(type) + " " + colName + ";");

        if (type == String.class)
            out.println("String " + colName + "Pattern;");
        else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null)
                out.printf("%s %sRange = new %s();\n", //
                        imports.simple(rangeType), //
                        colName, //
                        imports.simple(rangeType));
        }
    }

    @Override
    void columnAccessors(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        String ColName = Strings.ucfirst(colName);

        out.printf("public %s get%s() {\n", //
                imports.simple(type), //
                ColName);
        out.printf("    return %s;\n", colName);
        out.println("}");
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }
        out.printf("public void set%s(%s value) {\n", ColName, //
                imports.simple(type));
        out.printf("    this.%s = value;\n", colName);
        out.println("}");

        if (type == String.class) {
            out.println();
            out.printf("public String get%sPattern() {\n", ColName);
            out.printf("    return %sPattern;\n", colName);
            out.println("}");
            out.println();
            out.printf("public void set%sPattern(%s value) {\n", ColName, //
                    imports.simple(type));
            out.printf("    this.%sPattern = value;\n", colName);
            out.println("}");
        } else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null) {
                String RT = imports.simple(rangeType);
                out.println();
                out.printf("public %s get%sRange() {\n", RT, ColName);
                out.printf("    return %sRange;\n", colName);
                out.println("}");
                out.println();
                out.printf("public void set%sRange(%s range) {\n", ColName, RT);
                out.printf("    this.%sRange = range;\n", colName);
                out.println("}");
            }
        }
    }

}
