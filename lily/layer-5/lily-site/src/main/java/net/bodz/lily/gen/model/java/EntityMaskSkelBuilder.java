package net.bodz.lily.gen.model.java;

import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.lily.gen.JavaFragmentBuilder;

public class EntityMaskSkelBuilder
        extends JavaFragmentBuilder<ITableMetadata> {

    public EntityMaskSkelBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String maskStuffName = Naming.maskStuff(mainName);
        out.printf("public class %s\n", fragmentName);
        out.printf("        extends %s {\n", //
                maskStuffName);
        out.enter();
        {
//            out.println();
//            out.println("private static final long serialVersionUID = 1L;");
            out.leave();
        }
        out.println();
        out.println("}");
    }

}
