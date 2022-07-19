package net.bodz.lily.gen;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.t.table.IColumnMetadata;
import net.bodz.bas.t.table.ITableMetadata;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

public class JavaClassFormatter {

    ITreeOut out;
    String packageName;

    public JavaClassFormatter(ITreeOut out, String packageName) {
        this.out = out;
        this.packageName = packageName;
    }

    public void javaClass(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);

        Class<?> idType = null;
        IColumnMetadata[] pkv = table.getPrimaryKeyColumns();
        if (pkv.length == 1)
            idType = pkv[0].getType();

        out.println("package " + packageName + ";");
        out.println();

        Set<Class<?>> imports = new LinkedHashSet<>();
        // javax
        imports.add(Table.class);
        // form-validate
        imports.add(NotNull.class);
        imports.add(Precision.class);
        // lily-model
        imports.add(IdType.class);
        imports.add(CoEntity.class);
        // others
        if (!"java.lang".equals(idType.getPackage().getName()))
            imports.add(idType);

        for (Class<?> c : imports)
            out.println("import " + c.getName() + ";");
        out.println();

        out.printf("@Table(name = \"%s\")\n", table_name);
        out.printf("@IdType(%s)\n", idType.getSimpleName());
        out.printf("public class %s\n", camelName);
        out.printf("        extends CoEntity<%s> {\n", idType.getSimpleName());
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                columnField(column);
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

    public void maskClass(ITableMetadata table) {
    }

    void columnField(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + "*/");

        boolean notNull = !column.isNullable();
        if (notNull)
            out.println("@NotNull");

        out.println(type.getSimpleName() + " " + colName + ";");
    }

    void columnAccessors(IColumnMetadata column) {
        String col_name = column.getName();
        String colName = StringId.UL.toCamel(col_name);
        Class<?> type = column.getType();

        String description = column.getDescription();
        if (description != null && !description.isEmpty())
            out.println("/** " + description + "*/");

        boolean notNull = !column.isNullable();
        if (notNull)
            out.println("@NotNull");

        String GET = Boolean.class == type ? "is" : "get";

        String ColName = Strings.ucfirst(colName);
        out.printf("public %s %s%s() {\n", type.getSimpleName(), GET, ColName);
        out.printf("    return %s;\n", colName);
        out.println("}");
        out.println();
        out.printf("public void set%s(%s%s value) {\n", ColName, //
                notNull ? "@NotNull " : "", type.getSimpleName());
        out.printf("    this.%s = value;\n", colName);
        out.println("}");
    }

}
