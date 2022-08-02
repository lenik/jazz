package net.bodz.lily.gen.model.java;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.lily.model.base.CoIndex;

public class EntityIndexBuilder
        extends EntityStuffBuilder {

    public EntityIndexBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String maskName = mainName + "Mask";

        out.println("/**");
        out.println("* @label " + mainName);
        out.println("*/");
        out.printf("@%s(%s.class)\n", //
                imports.simple(ObjectType.class), //
                imports.simple(mainQName));
        out.println("public class " + fragmentName);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s> {\n", //
                        imports.simple(CoIndex.class), //
                        imports.simple(mainQName), //
                        maskName);
                out.leave();
            }
//            out.println();
//            out.println("public static final String SCHEMA = \"" + table.getName() + "\";");
            out.println();
            out.println("public " + fragmentName + "() {");
            out.enter();
            {
//                out.println("super(SCHEMA);");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");
    }

}
