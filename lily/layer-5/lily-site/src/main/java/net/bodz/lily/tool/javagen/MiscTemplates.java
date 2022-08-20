package net.bodz.lily.tool.javagen;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.bas.t.range.*;

public class MiscTemplates {

    JavaGenProject project;

    public MiscTemplates(JavaGenProject project) {
        this.project = project;
    }

    public QualifiedName getIdType(ITableViewMetadata table) {
        IColumnMetadata[] primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            return null;
        case 1:
            Class<?> kType = primaryKeyCols[0].getType();
            Class<?> kBoxed = Primitives.box(kType);
            return QualifiedName.parse(kBoxed.getName());
        default:
            return project.IFoo_Id;
        }
    }

    static Map<Class<?>, String> initVals = new HashMap<>();
    static {
        initVals.put(String.class, "\"\"");
        initVals.put(BigDecimal.class, "BigDecimal.ZERO");
        initVals.put(BigInteger.class, "BigInteger.ZERO");
        initVals.put(Timestamp.class, "new Timestamp(System.currentTimeMillis()");
    }

    public void initNotNulls(JavaSourceWriter out, ITableViewMetadata table) {
        out.println("public void initNotNulls() {");
        out.enter();
        for (IColumnMetadata column : table.getColumns()) {
            if (column.isPrimaryKey())
                continue;

            if (column.isNullable())
                continue;

            Class<?> type = column.getType();
            String initVal = MiscTemplates.initVals.get(type);
            if (initVal == null)
                continue;

            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            out.println("this." + colName + " = " + initVal + ";");
        }
        out.leave();
        out.println("}");
    }

    public void N_consts(JavaSourceWriter out, ITableViewMetadata table, Boolean wantPrimaryKey) {
        List<String> defs = new ArrayList<>();
        for (IColumnMetadata column : table.getColumns()) {
            if (wantPrimaryKey != null)
                if (column.isPrimaryKey() != wantPrimaryKey.booleanValue())
                    continue;

            Class<?> type = column.getType();
            String col_name = column.getName();
            String COL_NAME = col_name.toUpperCase();

            if (type == String.class) {
                int precision = column.getPrecision();
                if (precision > 0)
                    defs.add("public static final int N_" + COL_NAME + " = " + precision + ";");
            }
        }
        if (!defs.isEmpty()) {
            out.println();
            for (String def : defs)
                out.println(def);
        }
    }

    public void columnField(JavaSourceWriter out, IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        if (column.isPrimaryKey())
            out.println("@" + out.im.name(Id.class));

        boolean notNull = !column.isNullable();
        if (notNull)
            out.println("@" + out.im.name(NotNull.class));

        out.print(out.im.name(type) + " " + colName);
        out.println(";");
    }

    public void columnAccessors(JavaSourceWriter out, IColumnMetadata column, boolean impl) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        if (column.isPrimaryKey())
            out.println("@" + out.im.name(Id.class));
        boolean unique = column.isUnique();

        boolean notNull = !column.isNullable();
        if (notNull && !type.isPrimitive())
            out.println("@" + out.im.name(NotNull.class));

        // int columnDisplaySize = column.getColumnDisplaySize();
        int precision = column.getPrecision();
        int scale = column.getScale();

        out.print("@" + out.im.name(Precision.class) + "(");
        {
            if (type == String.class) {
                String N_COL_NAME = "N_" + col_name.toUpperCase();
                out.print("value = " + N_COL_NAME);
            } else {
                out.print("value = " + precision);
            }
            if (scale != 0)
                out.print(", scale = " + scale);
            out.println(")");
        }

        String N_COL_NAME = "N_" + col_name.toUpperCase();
        if (type == String.class) {
            if (precision > 0) {
                out.println("@" + out.im.name(TextInput.class) + "(maxLength = " + N_COL_NAME + ")");
            }
        }

        out.print("@" + out.im.name(Column.class));
        {
            out.print("(name = \"" + col_name + "\"");
            if (unique)
                out.print(", unique = true");
            if (notNull)
                out.print(", nullable = false");

            boolean insertable = true;
            boolean updatable = true;
            if (!insertable)
                out.print(", insertable = false");
            if (!updatable)
                out.print(", updatable = false");

            if (type == String.class)
                out.print(", length = " + N_COL_NAME);
            else {
                out.print(", precision = " + precision);
                if (scale != 0)
                    out.print(", scale = " + scale);
            }
            out.println(")");
        }

        String isOrGet = Boolean.class == type ? "is" : "get";
        String ColName = Strings.ucfirst(colName);

        out.printf("public %s %s%s()", //
                out.im.name(type), //
                isOrGet, ColName);
        if (impl) {
            out.println(" {");
            out.printf("    return %s;\n", colName);
            out.println("}");
        } else {
            out.println(";");
        }
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        out.printf("public void set%s(%s%s value)", ColName, //
                (notNull && !type.isPrimitive()) ? ("@" + out.im.name(NotNull.class) + " ") : "", out.im.name(type));
        if (impl) {
            out.println(" {");
            out.printf("    this.%s = value;\n", colName);
            out.println("}");
        } else {
            out.println(";");
        }
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

    public void columnMaskFields(JavaSourceWriter out, IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        out.println(out.im.name(type) + " " + colName + ";");

        if (type == String.class)
            out.println("String " + colName + "Pattern;");
        else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null)
                out.printf("%s %sRange = new %s();\n", //
                        out.im.name(rangeType), //
                        colName, //
                        out.im.name(rangeType));
        }
    }

    public void columnMaskAccessors(JavaSourceWriter out, IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        String ColName = Strings.ucfirst(colName);

        out.printf("public %s get%s() {\n", //
                out.im.name(type), //
                ColName);
        out.printf("    return %s;\n", colName);
        out.println("}");
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }
        out.printf("public void set%s(%s value) {\n", ColName, //
                out.im.name(type));
        out.printf("    this.%s = value;\n", colName);
        out.println("}");

        if (type == String.class) {
            out.println();
            out.printf("public String get%sPattern() {\n", ColName);
            out.printf("    return %sPattern;\n", colName);
            out.println("}");
            out.println();
            out.printf("public void set%sPattern(%s value) {\n", ColName, //
                    out.im.name(type));
            out.printf("    this.%sPattern = value;\n", colName);
            out.println("}");
        } else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null) {
                String RT = out.im.name(rangeType);
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
