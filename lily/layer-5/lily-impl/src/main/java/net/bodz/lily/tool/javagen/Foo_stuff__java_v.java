package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.bas.t.catalog.IViewMetadata;
import net.bodz.bas.t.catalog.Phrase;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.StructRow;

public class Foo_stuff__java_v
        extends JavaGen__java {

    public Foo_stuff__java_v(JavaGenProject project) {
        super(project, project._Foo_stuff);

    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata tableView) {
        IViewMetadata view = (IViewMetadata) tableView;

        QualifiedName idType = templates.getIdType(view);

        String parent;
        if (idType != null)
            parent = out.im.name(CoEntity.class) + "<" + out.im.name(idType) + ">";
        else
            parent = out.im.name(StructRow.class);

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

            templates.N_consts(out, view, null);

            for (IColumnMetadata column : view.getColumns()) {
                out.println();
                templates.columnField(out, column);
            }

            IColumnMetadata[] primaryKeyCols = view.getPrimaryKeyColumns();
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
                    Phrase name = k.nam();
                    out.printf("    this.%s = id.get%s();\n", name.fooBar, name.FooBar);
                }
                out.printf("}\n");
            } else {
                for (IColumnMetadata k : primaryKeyCols) {
                    Phrase name = k.nam();
                    out.println();
                    out.println("@Override");
                    out.printf("public %s id() {\n", out.im.name(idType));
                    out.printf("    return get%s();\n", name.FooBar);
                    out.printf("}\n");
                    out.println();
                    out.println("@Override");
                    out.printf("public void id(%s id) {\n", out.im.name(idType));
                    out.printf("    this.set%s(id);\n", name.FooBar);
                    out.printf("}\n");
                }
            }

            for (IColumnMetadata column : view.getColumns()) {
                out.println();
                templates.columnAccessors(out, column, true);
            }

            out.println();
            templates.initNotNulls(out, view);

            out.leave();
        }
        out.println();
        out.println("}");
    }

}
