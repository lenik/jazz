package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.StructRow;

public class Foo_stuff__java
        extends JavaGen__java {

    public Foo_stuff__java(JavaGenProject project) {
        super(project, project._Foo_stuff);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata tableView) {
        ITableMetadata table = (ITableMetadata) tableView;
        QualifiedName idType = templates.getIdType(table);

        String parent;
        if (idType != null)
            parent = out.im.name(CoEntity.class) + "<" + out.im.name(idType) + ">";
        else
            parent = out.im.name(StructRow.class);

        String description = tableView.getDescription();
        templates.javaDoc(out, description);

        if (idType != null)
            out.printf("@%s(%s.class)\n", //
                    out.im.name(IdType.class), //
                    out.im.name(idType));

        out.printf("public abstract class %s\n", project._Foo_stuff.name);
        out.printf("        extends %s {\n", parent);
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");

            templates.N_consts(out, table, null);

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                templates.columnField(out, column);
            }

            IColumnMetadata[] primaryKeyCols = table.getPrimaryKeyColumns();
            if (primaryKeyCols.length > 1) {
                out.println();
                out.println("@Override");
                out.printf("public %s id() {\n", out.im.name(idType));
                // out.printf(" return id;\n");
                out.printf("    return new %s(this);\n", out.im.name(project.Foo_Id));
                out.printf("}\n");
                out.println();
                out.println("@Override");
                out.printf("public void id(%s id) {\n", out.im.name(idType));
                // out.printf(" this.id = id;\n");
                for (IColumnMetadata k : primaryKeyCols) {
                    Phrase name = Phrase.foo_bar(k.getName());
                    out.printf("    this.%s = id.get%s();\n", name.fooBar, name.FooBar);
                }
                out.printf("}\n");
            } else {
                for (IColumnMetadata k : primaryKeyCols) {
                    Phrase name = Phrase.foo_bar(k.getName());
                    out.println();
                    out.println("@Override");
                    out.printf("public %s id() {\n", out.im.name(idType));
                    out.printf("    return get%s();\n", name.FooBar);
                    out.printf("}\n");
                    out.println();
                    out.println("@Override");
                    out.printf("public void id(%s id) {\n", out.im.name(idType));
                    out.printf("    set%s(id);\n", name.FooBar);
                    out.printf("}\n");
                }
            }

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                templates.columnAccessors(out, column, true);
            }

            out.println();
            templates.initNotNulls(out, table);

            out.leave();
        }
        out.println();
        out.println("}");
    }

}
