package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.catalog.*;

public class JavaModelGenerator
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(JavaModelGenerator.class);

    String skelDirname = "src/main/java/";
    String skelResDirname = "src/main/resources/";
    String generatedDirname = "src/main/generated/";

    /**
     * Parent package name of generated java models.
     *
     * @option -p =QNAME
     */
    String parentPackage;

    /**
     * Output directory.
     *
     * @option -O =PATH
     */
    File outDir;

    /**
     * Generate models for views.
     *
     * @option -V
     */
    Boolean includeViews;

    /**
     * Generate models for tables.
     *
     * @option -T
     */
    Boolean includeTables;

    Class<?> appClass = getClass();
    DataContext dataContext;
    Connection connection;

    CatalogSubset catalogSubset = new CatalogSubset(null);

    public JavaModelGenerator(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    boolean processTableView(ITableViewMetadata tableView) {
        switch (tableView.getTableType()) {
        case TABLE:
        case SYSTEM_TABLE:
        case TEMP:
        case GLOBAL_TEMP:
            ITableMetadata table = (ITableMetadata) tableView;
            try {
                makeTable(table);
            } catch (Exception e) {
                logger.error("Error make table: " + e.getMessage(), e);
                return false;
            }
            return true;

        case VIEW:
            try {
                makeView(tableView);
            } catch (Exception e) {
                logger.error("Error make table: " + e.getMessage(), e);
                return false;
            }
            return true;

        default:
            return false;
        }
    }

    void makeTable(ITableMetadata table)
            throws IOException {
        MakeSourceRun run = new MakeSourceRun(this, table);
        run.makeTable();
    }

    void makeView(ITableViewMetadata view)
            throws IOException {
        MakeSourceRun run = new MakeSourceRun(this, view);
        run.makeView();
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (parentPackage == null)
            throw new IllegalArgumentException("parent-package isn't specified.");

        outDir = MavenPomDir.fromClass(appClass).getBaseDir();

        if (includeTables == null && includeViews == null)
            includeTables = includeViews = true;

        connection = dataContext.getConnection();

        for (String arg : args) {
            if (arg.endsWith(".*.*")) {
                catalogSubset.addAllSchemas();
            } else if (arg.endsWith(".*")) {
                String schemaName = StringPart.before(arg, ".*");
                // schemaName = schemaName.toLowerCase();
                catalogSubset.addFullSchema(schemaName);
            } else {
                TableOid id = TableOid.parse(arg);
                catalogSubset.addTable(id);
            }
        }

        DefaultCatalogMetadata catalog = new DefaultCatalogMetadata();
        catalog.setJDBCLoadSelector(new IJDBCLoadSelector() {
            @Override
            public boolean selectSchema(SchemaOid id) {
                ContainingType type = catalogSubset.contains(id.getSchemaName());
                return type != ContainingType.NONE;
            }

            @Override
            public boolean selectTable(TableOid oid, TableType type) {
                if (type.isTable())
                    if (includeTables != Boolean.TRUE)
                        return false;
                if (type.isView())
                    if (includeViews != Boolean.TRUE)
                        return false;

                boolean contains = catalogSubset.contains(oid);
                return contains;
            }

        });
        catalog.loadFromJDBC(connection, "TABLE", "VIEW");
        XmlFn.save(catalog, new File("/xxx/a.xml"));

        catalog.accept(new ICatalogVisitor() {
            @Override
            public boolean beginTableView(ITableViewMetadata table) {
                return processTableView(table);
            }
        });

        connection.close();
    }

}
