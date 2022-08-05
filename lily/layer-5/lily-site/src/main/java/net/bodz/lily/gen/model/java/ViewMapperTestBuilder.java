package net.bodz.lily.gen.model.java;

import net.bodz.bas.t.catalog.ITableViewMetadata;

public class ViewMapperTestBuilder
        extends ViewStuffBuilder {

    public ViewMapperTestBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableViewMetadata table) {
        String mapperName = mainName + "Mapper";
        String maskName = mainName + "Mask";
        String samplesName = mainName + "Samples";

        out.println("public class " + fragmentName);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s<%s, %s, %s> {\n", //
                        imports.simple("net.bodz.lily.test.AbstractMapperTest"), //
                        imports.simple(mainQName), //
                        maskName, //
                        mapperName);
                out.leave();
            }
            out.println();
            out.println("@Override");
            out.printf("public %s buildSample() throws Exception {\n", //
                    mainName);
            out.enter();
            {
                out.println("return " + samplesName + ".build();");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");

    }

}
