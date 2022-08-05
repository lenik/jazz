package net.bodz.lily.gen.model.java;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class ViewMapperBuilder
        extends ViewStuffBuilder {

    public ViewMapperBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableViewMetadata table) {
        String maskName = mainName + "Mask";

        out.println("public interface " + fragmentName);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s> {\n", //
                        imports.simple(IMapperTemplate.class), //
                        imports.simple(mainQName), //
                        maskName);
                out.println();
                out.leave();
            }
            out.leave();
        }
        out.println("}");

    }

}
