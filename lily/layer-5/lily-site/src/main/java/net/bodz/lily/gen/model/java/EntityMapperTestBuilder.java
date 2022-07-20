package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.table.ITableMetadata;

public class EntityMapperTestBuilder
        extends EntityClassBuilder {

    public EntityMapperTestBuilder(String fqcn) {
        super(fqcn);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);
        String CamelName = Strings.ucfirst(camelName);

        String mapperType = CamelName + "Mapper";
        String mapperTestType = CamelName + "MapperTest";
        String maskType = CamelName + "Mask";

        out.println("public class " + mapperTestType);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s, %s> {\n", //
                        imports.simple("net.bodz.lily.test.AbstractMapperTest"), //
                        imports.simple(fqcn), //
                        maskType, //
                        mapperType);
                out.println();
                out.leave();
            }
            out.println("@Override");
            out.printf("public %s getContext() {\n", imports.simple(DataContext.class));
            out.enter();
            {
                out.println("return VioletTests.getDefaultContext();");
                out.leave();
            }
            out.println("}");
            out.println();
            out.println("@Override");
            out.printf("public %s buildSample() {\n", //
                    imports.simple(fqcn));
            out.enter();
            {
                out.println("return PersonSamples.build();");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");

    }

}
