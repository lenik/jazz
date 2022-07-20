package net.bodz.lily.gen.model.java;

import javax.persistence.Table;

import net.bodz.bas.t.table.ITableMetadata;

public class EntitySkelBuilder
        extends FragmentSourceBuilder<ITableMetadata> {

    public EntitySkelBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String stuffName = Naming.stuff(mainName);

        out.print(imports.a(Table.class) + "(");
        {
            String catalog_name = table.getCatalogName();
            if (catalog_name != null)
                out.print("catalog=\"" + catalog_name + "\", ");
            String schema_name = table.getSchemaName();
            if (schema_name != null)
                out.print("schema=\"" + schema_name + "\", ");
            out.print("name=\"" + table.getName() + "\"");
            out.println(")");
        }

        out.printf("public class %s\n", fragmentName);
        out.printf("        extends %s {\n", //
                stuffName);
        out.enter();
        {
            out.println();
            out.println("private static final long serialVersionUID = 1L;");
            out.leave();
        }
        out.println();
        out.println("}");
    }

}
