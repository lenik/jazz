package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.model.base.CoObjectMask;

public class FooMask_stuff__java
        extends JavaGen__java {

    public FooMask_stuff__java(JavaGenProject project) {
        super(project, project._FooMask_stuff);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata table) {
        out.printf("public class %s\n", project._FooMask_stuff.name);
        out.printf("        extends %s {\n", out.im.name(CoObjectMask.class));
        out.enter();
        {
//            out.println();
//            out.println("private static final long serialVersionUID = 1L;");

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                templates.columnMaskFields(out, column);
            }

            for (IColumnMetadata column : table.getColumns()) {
                out.println();
                templates.columnMaskAccessors(out, column);
            }

            out.leave();
        }
        out.println();
        out.println("}");
    }

}
