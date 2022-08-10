package net.bodz.lily.gen.model.java;

import java.io.Serializable;

import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class TableIdIfaceBuilder
        extends TableStuffBuilder {

    public TableIdIfaceBuilder(String mainQName) {
        super(mainQName, NamingUtil.id(mainQName));
    }

    @Override
    protected void buildClassBody(ITableViewMetadata tableView) {
        ITableMetadata table = (ITableMetadata) tableView;

        primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            throw new IllegalArgumentException("no primary key column.");
        }

        out.printf("public interface %s\n", fragmentName);
        out.println("        implements");
        out.println("            " + imports.simple(Serializable.class) + " {");
        out.enter();
        {

            N_consts(table, true);

            for (IColumnMetadata column : primaryKeyCols) {
                out.println();
                columnAccessors(column);
            }

            out.leave();
        }
        out.println();
        out.println("}");
    }

}
