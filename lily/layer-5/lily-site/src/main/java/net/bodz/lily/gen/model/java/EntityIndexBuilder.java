package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.t.table.ITableMetadata;
import net.bodz.lily.model.base.CoIndex;

public class EntityIndexBuilder
        extends EntityClassBuilder {

    public EntityIndexBuilder(String fqcn) {
        super(fqcn);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);
        String CamelName = Strings.ucfirst(camelName);

        String indexType = CamelName + "Index";
        String maskType = CamelName + "Mask";

        out.println("/**");
        out.println("* @label " + CamelName);
        out.println("*/");
        out.printf("@%s(%s.class)\n", imports.simple(ObjectType.class), imports.simple(fqcn));
        out.println("public class " + indexType);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s> {\n", //
                        imports.simple(CoIndex.class), //
                        imports.simple(fqcn), //
                        maskType);
                out.println();
                out.leave();
            }
            out.println("public static final String SCHEMA = \"" + table.getName() + "\";");
            out.println();
            out.println("public " + indexType + "() {");
            out.enter();
            {
                out.println("super(SCHEMA);");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");
    }

}
