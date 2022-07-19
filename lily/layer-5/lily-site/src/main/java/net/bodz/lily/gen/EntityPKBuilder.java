package net.bodz.lily.gen;

import java.io.Serializable;
import java.util.Objects;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.t.table.IColumnMetadata;
import net.bodz.bas.t.table.ITableMetadata;

public class EntityPKBuilder
        extends EntityClassBuilder {

    String idType;

    public EntityPKBuilder(String packageName) {
        super(packageName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);
        String CamelName = Strings.ucfirst(camelName);

        primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            throw new IllegalArgumentException("no primary key column.");
        default:
            idType = CamelName + "_PK";
        }

        out.printf("public class %s\n", idType);
        out.printf("        implements %s {\n", //
                imports.simple(Serializable.class));
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            N_consts(table, true);

            for (IColumnMetadata column : primaryKeyCols) {
                out.println();
                columnField(column);
            }

            for (IColumnMetadata column : primaryKeyCols) {
                out.println();
                columnAccessors(column);
            }

            out.println();
            hashCode(table);

            out.println();
            equals(table);

            out.println();
            toString(table);

            out.leave();
        }
        out.println();
        out.println("}");
    }

    void hashCode(ITableMetadata table) {
        out.println(imports.a(Override.class));
        out.println("public int hashCode() {");
        out.enter();

        int i = 0;
        out.print("return " + imports.simple(Objects.class) + ".hash(");
        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            if (i != 0)
                out.print(", ");
            out.print(colName);
            i++;
        }
        out.println(");");

        out.leave();
        out.println("}");
    }

    void equals(ITableMetadata table) {
        out.println(imports.a(Override.class));
        out.println("public boolean equals(Object obj) {");
        out.enter();

        out.println("if (this == obj)");
        out.println("    return true;");
        out.println("if (obj == null)");
        out.println("    return false;");
        out.println("if (getClass() != obj.getClass())");
        out.println("    return false;");
        out.println(idType + " o = (" + idType + ") obj;");

        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            out.printf("if (! Objects.equals(%s, o.%s)) return false;\n", //
                    colName, colName);
        }
        out.println("return true;");
        out.leave();
        out.println("}");
    }

    void toString(ITableMetadata table) {
        out.println(imports.a(Override.class));
        out.println("public String toString() {");
        out.enter();

        out.println("StringBuilder sb = new StringBuilder(100);");
        int i = 0;
        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            if (i != 0)
                out.println("sb.append(\", \"); ");
            out.println("sb.append(\"" + colName + " \" + " + colName + ");");
            i++;
        }
        out.println("return sb.toString();");

        out.leave();
        out.println("}");
    }

}
