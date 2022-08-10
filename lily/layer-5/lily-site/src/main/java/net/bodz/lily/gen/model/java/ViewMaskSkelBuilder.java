package net.bodz.lily.gen.model.java;

import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.gen.JavaFragmentBuilder;

public class ViewMaskSkelBuilder
        extends JavaFragmentBuilder<ITableViewMetadata> {

    public ViewMaskSkelBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableViewMetadata table) {
        String maskStuffName = NamingUtil.maskStuff(mainName);
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
