package net.bodz.lily.gen.model.java;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.gen.JavaFragmentBuilder;
import net.bodz.lily.model.base.StructRow;

public class _TableViewStuffBuilder
        extends JavaFragmentBuilder<ITableViewMetadata> {

    public _TableViewStuffBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    public static interface ClassBodyDetail {
        default void body_classAnnotations() {
        }

        String body_parent();

        default void body_idAccessors() {
        }

    }

    @Override
    protected void buildClassBody(ITableViewMetadata tableView) {
        buildClassBody(tableView, new ClassBodyDetail() {
            @Override
            public String body_parent() {
                return imports.simple(StructRow.class);
            }
        });
    }

    protected void buildClassBody(ITableViewMetadata table, ClassBodyDetail detail) {
        detail.body_classAnnotations();
        out.printf("public abstract class %s\n", fragmentName);
        out.printf("        extends %s {\n", //
                detail.body_parent());
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            N_consts(table, null);

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnField(column);
            }

            detail.body_idAccessors();

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnAccessors(column);
            }

            out.println();
            Commons.initNotNulls(out, table);

            out.leave();
        }
        out.println();
        out.println("}");
    }

    protected void columnField(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + " */");

        if (column.isPrimaryKey())
            out.println(imports.a(Id.class));

        boolean notNull = !column.isNullable();
        if (notNull)
            out.println(imports.a(NotNull.class));

        out.print(imports.simple(type) + " " + colName);
        out.println(";");
    }

    protected void columnAccessors(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        if (column.isPrimaryKey())
            out.println(imports.a(Id.class));
        boolean unique = column.isUnique();

        boolean notNull = !column.isNullable();
        if (notNull && !type.isPrimitive())
            out.println(imports.a(NotNull.class));

        // int columnDisplaySize = column.getColumnDisplaySize();
        int precision = column.getPrecision();
        int scale = column.getScale();

        out.print(imports.a(Precision.class) + "(");
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
                out.println(imports.a(TextInput.class) + "(maxLength = " + N_COL_NAME + ")");
            }
        }

        out.print(imports.a(Column.class));
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

        String GET = Boolean.class == type ? "is" : "get";
        String ColName = Strings.ucfirst(colName);

        out.printf("public %s %s%s() {\n", //
                imports.simple(type), //
                GET, ColName);
        out.printf("    return %s;\n", colName);
        out.println("}");
        out.println();

        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        out.printf("public void set%s(%s%s value) {\n", ColName, //
                (notNull && !type.isPrimitive()) ? imports.a(NotNull.class) + " " : "", imports.simple(type));
        out.printf("    this.%s = value;\n", colName);
        out.println("}");
    }

    protected void N_consts(ITableViewMetadata table, Boolean wantPrimaryKey) {
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

}
