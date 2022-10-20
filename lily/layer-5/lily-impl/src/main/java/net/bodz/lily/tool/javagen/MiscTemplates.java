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
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.meta.decl.Ordinal;
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

    public void javaDoc(ITreeOut out, String doc) {
        javaDoc(out, doc, 80);
    }

    public void javaDoc(ITreeOut out, String doc, int width) {
        if (doc == null)
            return;
        doc = doc.trim();
        if (doc.isEmpty())
            return;

        out.println("/**");
        out.println(" * " + doc);
        out.println(" */");
    }

    public QualifiedName getIdType(ITableViewMetadata table) {
        IColumnMetadata[] primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            return null;
        case 1:
            Class<?> kType = primaryKeyCols[0].getType();
            Class<?> kBoxed = Primitives.box(kType);
            return QualifiedName.parse(kBoxed.getCanonicalName());
        default:
            return project.IFoo_Id;
        }
    }

    static Map<Class<?>, String> initVals = new HashMap<>();
    static {
        initVals.put(String.class, "\"\"");
        initVals.put(BigDecimal.class, "BigDecimal.ZERO");
        initVals.put(BigInteger.class, "BigInteger.ZERO");
        initVals.put(Timestamp.class, "new Timestamp(System.currentTimeMillis())");
    }

    public void initNotNulls(JavaSourceWriter out, ITableViewMetadata table) {
        out.println("public void initNotNulls() {");
        out.enter();
        for (IColumnMetadata column : table.getColumns()) {
            if (column.isPrimaryKey())
                continue;

            if (column.isExcluded())
                continue;

            if (column.isNullable(true))
                continue;

            Class<?> type = column.getType();
            String initVal = MiscTemplates.initVals.get(type);
            if (initVal == null)
                continue;

            out.println("this." + column.nam().fooBar + " = " + initVal + ";");
        }
        out.leave();
        out.println("}");
    }

    public void N_consts(ITreeOut out, ITableViewMetadata table, Boolean wantPrimaryKey) {
        List<String> defs = new ArrayList<>();
        for (IColumnMetadata column : table.getColumns()) {
            if (wantPrimaryKey != null)
                if (column.isPrimaryKey() != wantPrimaryKey.booleanValue())
                    continue;

            if (column.isExcluded())
                continue;

            Phrase name = column.nam();
            Class<?> type = column.getType();
            String constName = Phrase.fooBar(name.fooBar).FOO_BAR;

            if (type == String.class || Number.class.isAssignableFrom(type)) {
                int precision = column.getPrecision();
                if (precision > 0)
                    defs.add("public static final int N_" + constName + " = " + precision + ";");
            }
        }
        if (!defs.isEmpty()) {
            out.println();
            for (String def : defs)
                out.println(def);
        }
    }

    public static final String OrdinalPrefix = "_ord_";

    public void O_consts(ITreeOut out, ITableViewMetadata table, Boolean wantPrimaryKey) {
        List<String> defs = new ArrayList<>();
        String lastVarName = null;
        int lastOrdinal = 0;
        for (IColumnMetadata column : table.getColumns()) {
            if (wantPrimaryKey != null)
                if (column.isPrimaryKey() != wantPrimaryKey.booleanValue())
                    continue;

            if (column.isExcluded())
                continue;

            Phrase name = column.nam();
            String constName = Phrase.fooBar(name.fooBar).FOO_BAR;

            int ordinal = column.getOrdinal();
            String varName = OrdinalPrefix + constName;

            String expr;
            int diff = ordinal - lastOrdinal;
            if (lastVarName != null && Math.abs(diff) <= 10)
                expr = lastVarName + (diff < 0 ? " - " + -diff : " + " + diff);
            else
                expr = "" + ordinal;
            defs.add("private static final int " + varName + " = " + expr + ";");

            lastVarName = varName;
            lastOrdinal = ordinal;
        }
        if (!defs.isEmpty()) {
            out.println();
            for (String def : defs)
                out.println(def);
        }
    }

    public void columnField(JavaSourceWriter out, IColumnMetadata column) {
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        if (column.isPrimaryKey())
            out.println("@" + out.im.name(Id.class));

        boolean notNull = !column.isNullable(true);
        if (notNull)
            out.println("@" + out.im.name(NotNull.class));

        out.print(out.im.name(type) + " " + column.nam().fooBar);
        out.println(";");
    }

    /**
     * @Id
     * @Ordinal
     * @NotNull
     * @Precision
     * @TextInput
     * @Column
     */
    public void columnAccessors(JavaSourceWriter out, IColumnMetadata column, boolean impl) {
        Phrase name = column.nam();
        String constName = Phrase.fooBar(name.fooBar).FOO_BAR;
        String N_COL_NAME = "N_" + constName;

        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        if (column.isPrimaryKey()) {
            out.println("@" + out.im.name(Id.class));
        }

        int ordinal = column.getOrdinal();
        if (ordinal != 0) {
            String varName = OrdinalPrefix + constName;
            out.println("@" + out.im.name(Ordinal.class) + "(" + varName + ")");
        }

        boolean unique = column.isUnique();

        boolean notNull = !column.isNullable(true);
        if (notNull && !type.isPrimitive())
            out.println("@" + out.im.name(NotNull.class));

        // int columnDisplaySize = column.getColumnDisplaySize();
        int precision = column.getPrecision();
        int scale = column.getScale();

        out.print("@" + out.im.name(Precision.class) + "(");
        {
            if (precision > 0 && //
                    (type == String.class || Number.class.isAssignableFrom(type))) {
                out.print("value = " + N_COL_NAME);
            } else {
                out.print("value = " + precision);
            }
            if (scale != 0)
                out.print(", scale = " + scale);
            out.println(")");
        }

        if (type == String.class) {
            if (precision > 0) {
                out.println("@" + out.im.name(TextInput.class) + "(maxLength = " + N_COL_NAME + ")");
            }
        }

        out.print("@" + out.im.name(Column.class));
        {
            out.print("(name = \"" + name.foo_bar + "\"");
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

        String isOrGet = boolean.class == type ? "is" : "get";

        out.printf("public %s %s%s()", //
                out.im.name(type), isOrGet, name.FooBar);
        if (impl) {
            out.println(" {");
            out.printf("    return %s;\n", name.fooBar);
            out.println("}");
        } else {
            out.println(";");
        }
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        out.printf("public void set%s(%s%s value)", name.FooBar, //
                (notNull && !type.isPrimitive()) ? ("@" + out.im.name(NotNull.class) + " ") : "", out.im.name(type));
        if (impl) {
            out.println(" {");
            out.printf("    this.%s = value;\n", name.fooBar);
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
        Phrase name = column.nam();
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        out.println(out.im.name(type) + " " + name.fooBar + ";");

        if (type == String.class)
            out.println("String " + name.fooBar + "Pattern;");
        else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null)
                out.printf("%s %sRange = new %s();\n", //
                        out.im.name(rangeType), //
                        name.fooBar, //
                        out.im.name(rangeType));
        }
    }

    public void columnMaskAccessors(JavaSourceWriter out, IColumnMetadata column) {
        Phrase name = column.nam();
        Class<?> type = Primitives.box(column.getType());

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        out.printf("public %s get%s() {\n", //
                out.im.name(type), //
                name.FooBar);
        out.printf("    return %s;\n", name.fooBar);
        out.println("}");
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }
        out.printf("public void set%s(%s value) {\n", name.FooBar, //
                out.im.name(type));
        out.printf("    this.%s = value;\n", name.fooBar);
        out.println("}");

        if (type == String.class) {
            out.println();
            out.printf("public String get%sPattern() {\n", name.FooBar);
            out.printf("    return %sPattern;\n", name.fooBar);
            out.println("}");
            out.println();
            out.printf("public void set%sPattern(%s value) {\n", name.FooBar, //
                    out.im.name(type));
            out.printf("    this.%sPattern = value;\n", name.fooBar);
            out.println("}");
        } else {
            Class<?> rangeType = rangeMapping.get(type);
            if (rangeType != null) {
                String RT = out.im.name(rangeType);
                out.println();
                out.printf("public %s get%sRange() {\n", RT, name.FooBar);
                out.printf("    return %sRange;\n", name.fooBar);
                out.println("}");
                out.println();
                out.printf("public void set%sRange(%s range) {\n", name.FooBar, RT);
                out.printf("    this.%sRange = range;\n", name.fooBar);
                out.println("}");
            }
        }
    }

    public void sqlColumnNameList(ITreeOut out, List<IColumnMetadata> columns, String prefix) {
        int n = columns.size();
        for (int i = 0; i < n; i++) {
            IColumnMetadata column = columns.get(i);
            Phrase name = column.nam();
            out.print(prefix + name.foo_bar);
            if (i != n - 1)
                out.print(", ");
            out.println();
        }
    }

    public void sqlMatchPrimaryKey(ITreeOut out, IColumnMetadata[] keyColumns) {
        if (keyColumns.length == 0)
            throw new IllegalUsageException("Can't update table without primary key.");

        if (keyColumns.length == 1) {
            IColumnMetadata column = keyColumns[0];
            Phrase name = column.nam();
            out.println(name.foo_bar + " = #{id}");
        } else {
            for (int i = 0; i < keyColumns.length; i++) {
                IColumnMetadata column = keyColumns[i];
                Phrase name = column.nam();
                if (i != 0)
                    out.print("and ");
                out.println(name.foo_bar + " = #{" + name.fooBar + "}");
            }
        }
    }

}
