package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.gen.model.java._TableViewStuffBuilder.ClassBodyDetail;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.StructRow;

public class TableStuffBuilder
        extends _TableViewStuffBuilder
        implements
            ClassBodyDetail {

    IColumnMetadata[] primaryKeyCols;

    public TableStuffBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    ITableMetadata table;
    String idType;

    @Override
    public void body_classAnnotations() {
        if (idType != null)
            out.printf("%s(%s.class)\n", //
                    imports.a(IdType.class), //
                    idType);
    }

    @Override
    public String body_parent() {
        return imports.simple(idType == null ? StructRow.class : CoEntity.class)
                + (idType == null ? "" : "<" + idType + ">");
    }

    @Override
    public void body_idAccessors() {
        if (primaryKeyCols.length > 1) {
            out.println();
            out.println("@Override");
            out.printf("public %s getId() {\n", idType);
            // out.printf(" return id;\n");
            out.printf("    return new %s(this);\n", idType);
            out.printf("}\n");
            out.println();
            out.println("@Override");
            out.printf("public void setId(%s id) {\n", idType);
            // out.printf(" this.id = id;\n");
            for (IColumnMetadata k : primaryKeyCols) {
                String col_name = k.getName();
                String colName = StringId.UL.toCamel(col_name);
                out.printf("    this.%s = id.%s;\n", colName, colName);
            }
            out.printf("}\n");
        }
    }

    @Override
    protected void buildClassBody(ITableViewMetadata tableView) {
        this.table = (ITableMetadata) tableView;
        primaryKeyCols = table.getPrimaryKeyColumns();
        switch (primaryKeyCols.length) {
        case 0:
            break;
        case 1:
            Class<?> kType = primaryKeyCols[0].getType();
            Class<?> kBoxed = Primitives.box(kType);
            idType = imports.simple(kBoxed);
            break;
        default:
            idType = NamingUtil.id(mainName);
        }

        super.buildClassBody(tableView, this);
    }

}
