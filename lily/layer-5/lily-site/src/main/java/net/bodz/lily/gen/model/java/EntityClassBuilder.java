package net.bodz.lily.gen.model.java;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.t.table.IColumnMetadata;
import net.bodz.bas.t.table.ITableMetadata;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

public class EntityClassBuilder
        extends FragmentSourceBuilder<ITableMetadata> {

    IColumnMetadata[] primaryKeyCols;

    public EntityClassBuilder(String fqcn) {
        super(fqcn, fqcn);
    }

    public EntityClassBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {

        String idType = null;
        primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            break;
        case 1:
            idType = imports.simple(primaryKeyCols[0].getType());
            break;
        default:
            idType = fragmentName + EntityIdBuilder.ID_SUFFIX;
        }
        // if (idType == null)
        // throw new NullPointerException("idType");

        out.print(imports.a(Table.class) + "(");
        {
            String catalog_name = table.getCatalogName();
            if (catalog_name != null)
                out.print("catalog=\"" + catalog_name + "\", ");
            String schema_name = table.getSchemaName();
            if (schema_name != null)
                out.print("schema=\"" + schema_name + "\", ");
            out.print("name=\"" + table.getName() + "\"");
            out.println(")");
        }

        if (idType != null)
            out.printf("%s(%s.class)\n", //
                    imports.a(IdType.class), //
                    idType);
        out.printf("public class %s\n", fragmentName);
        out.printf("        extends %s%s {\n", //
                imports.simple(idType == null ? Object.class : CoEntity.class), //
                idType == null ? "" : "<" + idType + ">");
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            N_consts(table, null);

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnField(column);
            }

            if (primaryKeyCols.length > 1) {
                out.println();
                out.println("@Override");
                out.printf("public %s getId() {\n", idType);
                // out.printf(" return id;\n");
                out.printf("    return new %s(this);\n", idType);
                out.printf("}\n");
                out.println();
                out.println("@Override");
                out.printf("public void setId(%s id) {\n", idType);
                // out.printf(" this.id = id;\n");
                for (IColumnMetadata k : primaryKeyCols) {
                    String col_name = k.getName();
                    String colName = StringId.UL.toCamel(col_name);
                    out.printf("    this.%s = id.%s;\n", colName, colName);
                }
                out.printf("}\n");
            }

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnAccessors(column);
            }

            out.println();
            initNotNulls(table);

            out.leave();
        }
        out.println();
        out.println("}");
    }

    void columnField(IColumnMetadata column) {
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

    void columnAccessors(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty()) {
            out.println("/** " + description + " */");
        }

        if (column.isPrimaryKey())
            out.println(imports.a(Id.class));

        boolean notNull = !column.isNullable();
        if (notNull)
            out.println(imports.a(NotNull.class));

        int precision = column.getPrecision();
        out.print(imports.a(Precision.class) + "(");
        {
            if (type == String.class) {
                String N_COL_NAME = "N_" + col_name.toUpperCase();
                out.print("value = " + N_COL_NAME);
            } else {
                out.print("value = " + precision);
            }
            int scale = column.getScale();
            if (scale != 0)
                out.print(", scale = " + scale);
            out.println(")");
        }

        if (type == String.class)

        {
            if (precision > 0) {
                String N_COL_NAME = "N_" + col_name.toUpperCase();
                out.println(imports.a(TextInput.class) + "(maxLength = " + N_COL_NAME + ")");
            }
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
                notNull ? imports.a(NotNull.class) + " " : "", imports.simple(type));
        out.printf("    this.%s = value;\n", colName);
        out.println("}");
    }

    static Map<Class<?>, String> initVals = new HashMap<>();
    static {
        initVals.put(String.class, "\"\"");
        initVals.put(BigDecimal.class, "BigDecimal.ZERO");
        initVals.put(Timestamp.class, "new Timestamp(System.currentTimeMillis()");
    }

    void N_consts(ITableMetadata table, Boolean wantPrimaryKey) {
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

    private void initNotNulls(ITableMetadata table) {
        out.println("public void initNotNulls() {");
        out.enter();
        for (IColumnMetadata column : table.getColumns()) {
            if (column.isPrimaryKey())
                continue;

            if (column.isNullable())
                continue;

            Class<?> type = column.getType();
            String initVal = initVals.get(type);
            if (initVal == null)
                continue;

            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            out.println("this." + colName + " = " + initVal + ";");
        }
        out.leave();
        out.println("}");
    }

}
