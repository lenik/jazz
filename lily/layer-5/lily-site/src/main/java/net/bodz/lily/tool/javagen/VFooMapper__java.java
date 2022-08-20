package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class VFooMapper__java
        extends JavaGen__java {

    public VFooMapper__java(JavaGenProject project) {
        super(project, project.FooMapper);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata table) {
        out.println("public interface " + project.FooMapper.name);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s> {\n", //
                        out.im.name(IMapperTemplate.class), //
                        out.im.name(project.Foo), //
                        out.im.name(project.FooMask));
                out.println();
                out.leave();
            }
            out.leave();
        }
        out.println("}");
    }

}
