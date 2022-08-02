package net.bodz.lily.gen.model.java;

import java.io.Serializable;
import java.util.Objects;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;

public class EntityIdBuilder
        extends EntityStuffBuilder {

    public EntityIdBuilder(String mainQName) {
        super(mainQName, Naming.id(mainQName));
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            throw new IllegalArgumentException("no primary key column.");
        }

        out.printf("public class %s\n", fragmentName);
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

            out.println();
            ctors(table);

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

    void ctors(ITableMetadata table) {
        IColumnMetadata[] kv = table.getPrimaryKeyColumns();

        // default constructor
        out.println("public " + fragmentName + "() {");
        out.println("}");
        out.println();

        // simple arguments listing constructor
        out.print("public " + fragmentName + "(");
        for (int i = 0; i < kv.length; i++) {
            String col_name = kv[i].getName();
            String colName = StringId.UL.toCamel(col_name);
            if (i != 0)
                out.print(", ");
            out.printf("%s %s", imports.simple(kv[i].getType()), colName);
        }
        out.println(") {");
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                String col_name = k.getName();
                String colName = StringId.UL.toCamel(col_name);
                out.printf("this.%s = %s;\n", colName, colName);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // clone constructor
        out.printf("public %s(%s o) {\n", fragmentName, fragmentName);
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                String col_name = k.getName();
                String colName = StringId.UL.toCamel(col_name);
                out.printf("this.%s = o.%s;\n", colName, colName);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // from entity constructor
        out.printf("public %s(%s o) {\n", fragmentName, Naming.stuff(mainName));
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                String col_name = k.getName();
                String colName = StringId.UL.toCamel(col_name);
                out.printf("this.%s = o.%s;\n", colName, colName);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // clone()
        out.println("@Override");
        out.println("public " + fragmentName + " clone() {");
        out.enter();
        {
            out.println("return new " + fragmentName + "(this);");
            out.leave();
        }
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
        out.println(fragmentName + " o = (" + fragmentName + ") obj;");

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
                out.println("sb.append(\", " + colName + " \" + " + colName + ");");
            else
                out.println("sb.append(\"" + colName + " \" + " + colName + ");");
            i++;
        }
        out.println("return sb.toString();");

        out.leave();
        out.println("}");
    }

}
