package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.test.AbstractMapperTest;

public class VFooMapperTest__java
        extends JavaGen__java {

    public VFooMapperTest__java(JavaGenProject project) {
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
                        out.im.name(AbstractMapperTest.class), //
                        out.im.name(project.Foo), //
                        out.im.name(project.FooMask), //
                        project.FooMapper.name);
                out.leave();
            }
            out.println();
            out.println("@Override");
            out.printf("public %s buildSample() throws Exception {\n", //
                    out.im.name(project.Foo));
            out.enter();
            {
                out.println("return " + out.im.name(project.FooSamples) + ".build();");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");
    }

}
