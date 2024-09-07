package net.bodz.lily.tool.javagen;

import java.io.Serializable;
import java.util.Objects;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.codegen.ImportSet;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.entity.Identifier;

public class Foo_Id__java
        extends JavaGen__java {

    public Foo_Id__java(JavaGenProject project) {
        super(project, project.Foo_Id);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata tableView) {
        ITableMetadata table = (ITableMetadata) tableView;

        IColumnMetadata[] primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            throw new IllegalArgumentException("no primary key column.");
        }

        out.println("@" + out.im.name(Identifier.class));
        out.printf("public class %s\n", project.Foo_Id.name);
        out.printf("        implements %s {\n", //
                out.im.name(Serializable.class));
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            templates.N_consts(out, table, true);
            templates.O_consts(out, table, true);

            for (IColumnMetadata column : primaryKeyCols) {
                out.println();
                templates.columnField(out, column);
            }

            out.println();
            ctors(out, table, out.im);

            for (IColumnMetadata column : primaryKeyCols) {
                out.println();
                templates.columnAccessors(out, column, true);
            }

            out.println();
            hashCode(out, table, out.im);

            out.println();
            equals(out, table, out.im);

            out.println();
            toString(out, table, out.im);

            out.leave();
        }
        out.println();
        out.println("}");
    }

    void ctors(ITreeOut out, ITableMetadata table, ImportSet imports) {
        IColumnMetadata[] kv = table.getPrimaryKeyColumns();

        // default constructor
        out.println("public " + project.Foo_Id.name + "() {");
        out.println("}");
        out.println();

        // simple arguments listing constructor
        out.print("public " + project.Foo_Id.name + "(");
        for (int i = 0; i < kv.length; i++) {
            if (i != 0)
                out.print(", ");
            out.printf("%s %s", imports.name(kv[i].getType()), kv[i].nam().fooBar);
        }
        out.println(") {");
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                Phrase name = k.nam();
                out.printf("this.%s = %s;\n", name.fooBar, name.fooBar);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // clone constructor
        out.printf("public %s(%s o) {\n", project.Foo_Id.name, project.Foo_Id.name);
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                Phrase name = k.nam();
                out.printf("this.%s = o.%s;\n", name.fooBar, name.fooBar);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // from entity constructor
        out.printf("public %s(%s o) {\n", project.Foo_Id.name, imports.name(project._Foo_stuff));
        out.enter();
        {
            for (IColumnMetadata k : kv) {
                Phrase name = k.nam();
                out.printf("this.%s = o.%s;\n", name.fooBar, name.fooBar);
            }
            out.leave();
        }
        out.println("}");
        out.println();

        // clone()
        out.println("@Override");
        out.println("public " + project.Foo_Id.name + " clone() {");
        out.enter();
        {
            out.println("return new " + project.Foo_Id.name + "(this);");
            out.leave();
        }
        out.println("}");
    }

    void hashCode(ITreeOut out, ITableMetadata table, ImportSet imports) {
        out.println("@" + imports.name(Override.class));
        out.println("public int hashCode() {");
        out.enter();

        int i = 0;
        out.print("return " + imports.name(Objects.class) + ".hash(");
        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            if (i != 0)
                out.print(", ");
            out.print(column.nam().fooBar);
            i++;
        }
        out.println(");");

        out.leave();
        out.println("}");
    }

    void equals(ITreeOut out, ITableViewMetadata table, ImportSet imports) {
        out.println("@" + imports.name(Override.class));
        out.println("public boolean equals(Object obj) {");
        out.enter();

        out.println("if (this == obj)");
        out.println("    return true;");
        out.println("if (obj == null)");
        out.println("    return false;");
        out.println("if (getClass() != obj.getClass())");
        out.println("    return false;");
        out.println(project.Foo_Id.name + " o = (" + project.Foo_Id.name + ") obj;");

        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            Phrase name = column.nam();
            out.printf("if (! Objects.equals(%s, o.%s)) return false;\n", //
                    name.fooBar, name.fooBar);
        }
        out.println("return true;");
        out.leave();
        out.println("}");
    }

    void toString(ITreeOut out, ITableMetadata table, ImportSet imports) {
        out.println("@" + imports.name(Override.class));
        out.println("public String toString() {");
        out.enter();

        out.println("StringBuilder sb = new StringBuilder(100);");
        int i = 0;
        for (IColumnMetadata column : table.getPrimaryKeyColumns()) {
            Phrase name = column.nam();
            if (i != 0)
                out.println("sb.append(\", " + name.fooBar + " \" + " + name.fooBar + ");");
            else
                out.println("sb.append(\"" + name.fooBar + " \" + " + name.fooBar + ");");
            i++;
        }
        out.println("return sb.toString();");

        out.leave();
        out.println("}");
    }

}
