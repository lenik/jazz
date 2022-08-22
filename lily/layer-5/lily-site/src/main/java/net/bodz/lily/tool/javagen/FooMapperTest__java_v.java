package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.test.AbstractViewTest;

public class FooMapperTest__java_v
        extends JavaGen__java {

    public FooMapperTest__java_v(JavaGenProject project) {
        super(project, project.FooMapperTest);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata model) {
        out.println("public class " + project.FooMapperTest.name);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s, %s> {\n", //
                        out.im.name(AbstractViewTest.class), //
                        out.im.name(project.Foo), //
                        out.im.name(project.FooMask), //
                        project.FooMapper.name);
                out.leave();
            }

            buildMapperMethods(out, model);

            out.println();
            out.leave();
        }
        out.println("}");
    }

    protected void buildMapperMethods(JavaSourceWriter out, ITableViewMetadata model) {
    }

}
