package net.bodz.lily.tool.javagen;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.catalog.*;

public class JavaGen
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(JavaGen.class);

    /**
     * Parent package name of generated java models.
     *
     * @option -p =QNAME
     */
    String parentPackage;

    /**
     * Output directory. Use the maven base.dir by default.
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

    /**
     * Overwrite all existing files.
     *
     * @option -f
     */
    boolean forceMode;

    Class<?> appClass = getClass();
    DataContext dataContext;
    Connection connection;

    CatalogSubset catalogSubset = new CatalogSubset(null);

    public JavaGen(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    boolean processTableView(ITableViewMetadata tableView) {
        switch (tableView.getTableType()) {
        case TABLE:
        case SYSTEM_TABLE:
        case TEMP:
        case GLOBAL_TEMP:
            try {
                makeTable((ITableMetadata) tableView);
            } catch (Exception e) {
                logger.error("Error make table: " + e.getMessage(), e);
                return false;
            }
            return true;

        case VIEW:
            try {
                makeView((IViewMetadata) tableView);
            } catch (Exception e) {
                logger.error("Error make table: " + e.getMessage(), e);
                return false;
            }
            return true;

        default:
            return false;
        }
    }

    JavaGenProject createProject(ITableViewMetadata tableView) {
        String full_table_name = tableView.getCompactName();
        String qTableName = StringId.UL.toQCamel(full_table_name);

        String fullSchemaName = null;
        String simpleName = qTableName;
        String packageName = parentPackage;
        int lastDot = qTableName.lastIndexOf('.');
        if (lastDot != -1) {
            fullSchemaName = qTableName.substring(0, lastDot);
            simpleName = qTableName.substring(lastDot + 1);
            packageName += "." + fullSchemaName;
        }

        ClassPathInfo pathInfo = new ClassPathInfo(packageName, simpleName, //
                outDir, "src/main/java", "src/main/resources");
        JavaGenProject project = new JavaGenProject(outDir, pathInfo);
        project.setForceMode(forceMode);
        return project;
    }

    public void makeTable(ITableMetadata table)
            throws IOException {
        JavaGenProject project = createProject(table);

        new Foo_stuff__java(project).buildFile(table, true);
        if (table.getPrimaryKeyColumns().length > 1)
            new Foo_Id__java(project).buildFile(table, true);

        new Foo__java(project).buildFile(table);
        new FooMask_stuff__java(project).buildFile(table, true);
        new FooMask__java(project).buildFile(table);
        new FooIndex__java(project).buildFile(table);
        new FooMapper__xml(project).buildFile(table);
        new FooMapper__java(project).buildFile(table);
        new FooMapperTest__java(project).buildFile(table);
        new FooSamples__java(project).buildFile(table);
    }

    public void makeView(IViewMetadata view)
            throws IOException {
        JavaGenProject project = createProject(view);

        new VFoo_stuff__java(project).buildFile(view, true);
        if (view.getPrimaryKeyColumns().length > 1)
            new Foo_Id__java(project).buildFile(view, true);

        new VFoo__java(project).buildFile(view);
        new FooMask_stuff__java(project).buildFile(view, true);
        new FooMask__java(project).buildFile(view);
        new FooIndex__java(project).buildFile(view);
        new VFooMapper__xml(project).buildFile(view);
        new VFooMapper__java(project).buildFile(view);
        new VFooMapperTest__java(project).buildFile(view);
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
