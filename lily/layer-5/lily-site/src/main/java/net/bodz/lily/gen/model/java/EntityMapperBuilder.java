package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.t.table.ITableMetadata;

public class EntityMapperBuilder
        extends EntityClassBuilder {

    public EntityMapperBuilder(String fqcn) {
        super(fqcn);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);
        String CamelName = Strings.ucfirst(camelName);

        String mapperType = CamelName + "Mapper";
        String maskType = CamelName + "Mask";

        out.println("public interface " + mapperType);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s> {\n", //
                        imports.simple(IMapperTemplate.class), //
                        imports.simple(fqcn), //
                        maskType);
                out.println();
                out.leave();
            }
            out.leave();
        }
        out.println("}");

    }

}
