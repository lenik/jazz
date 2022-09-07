package net.bodz.lily.test;

import java.sql.Connection;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.t.catalog.CatalogDumper;
import net.bodz.bas.t.catalog.DefaultSchemaMetadata;
import net.bodz.bas.t.catalog.TableList;
import net.bodz.bas.t.catalog.TableViewList;

public class SchemaPlay {

    static ITreeOut out = TreeOutImpl.from(Stdio.cout);

    public static void main(String[] args)
            throws Exception {
        new SchemaPlay().run();
    }

    void run()
            throws Exception {
        DataContext dataContext = DataHub.getPreferredHub().getTest();
        Connection cn = dataContext.getConnection();
        try {
            DefaultSchemaMetadata dsm = new DefaultSchemaMetadata();
            dsm.getId().setSchemaName("public");
            dsm.loadFromJDBC(cn, "TABLE", "VIEW");
            dsm.accept(new CatalogDumper(out).indented());

            TableList tables = dsm.findTables(null);
            tables = tables.sortInCreationOrder(dsm, cn);
            out.println(tables.id());

            TableViewList views = dsm.findViews(null);
            out.println(views.id());
        } finally {
            cn.close();
        }
    }

}
