package net.bodz.lily.tool.javagen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Random;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.codegen.UpdateMethod;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.io.res.AbstractStreamResource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.URLResource;
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
     * Compute a digest as the random seed from the given string. "magic" by default.
     *
     * @option -S
     */
    String seedMagic = "magic";

    /**
     * Use a random seed value. Typically a value from timestamp.
     *
     * @option -R
     */
    boolean seedRandom;

    /**
     * Use diff-merge.
     *
     * @option -d
     */
    boolean diffMerge;

    /**
     * Overwrite all existing files.
     *
     * @option -f
     */
    boolean forceMode;

    /**
     * Save the catalog metadata to file.
     *
     * @option --save-catalog =FILE
     */
    File saveCatalogAs;

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

        long seed;
        if (seedRandom)
            seed = System.currentTimeMillis();
        else
            seed = seedMagic.hashCode();

        ClassPathInfo pathInfo = new ClassPathInfo(packageName, simpleName, //
                outDir, "src/main/java", "src/main/resources");
        JavaGenProject project = new JavaGenProject(outDir, pathInfo, new Random(seed));

        UpdateMethod updateMethod;
        if (forceMode)
            updateMethod = diffMerge ? UpdateMethod.DIFF_MERGE : UpdateMethod.OVERWRITE;
        else
            updateMethod = diffMerge ? UpdateMethod.DIFF_MERGE : UpdateMethod.NO_UPDATE;
        project.setPreferredUpdateMethod(updateMethod);

        return project;
    }

    public void makeTable(ITableMetadata table)
            throws IOException {
        JavaGenProject project = createProject(table);

        new Foo_stuff__java(project).buildFile(table, UpdateMethod.OVERWRITE);
        if (table.getPrimaryKeyColumns().length > 1)
            new Foo_Id__java(project).buildFile(table, UpdateMethod.OVERWRITE);

        new Foo__java(project).buildFile(table);
        new FooMask_stuff__java(project).buildFile(table, UpdateMethod.OVERWRITE);
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

        new Foo_stuff__java_v(project).buildFile(view, UpdateMethod.OVERWRITE);
        if (view.getPrimaryKeyColumns().length > 1)
            new Foo_Id__java(project).buildFile(view, UpdateMethod.OVERWRITE);

        new Foo__java_tv(project).buildFile(view);
        new FooMask_stuff__java(project).buildFile(view, UpdateMethod.OVERWRITE);
        new FooMask__java(project).buildFile(view);
        new FooIndex__java(project).buildFile(view);
        new VFooMapper__xml(project).buildFile(view);
        new FooMapper__java_tv(project).buildFile(view);
        new FooMapperTest__java_v(project).buildFile(view);
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (parentPackage == null)
            throw new IllegalArgumentException("parent-package isn't specified.");

        if (outDir == null) {
            Class<?> appClass = getClass();
            MavenPomDir pomDir = MavenPomDir.closest(SysProps.userWorkDir);
            if (pomDir == null) {
                if (appClass != JavaGen.class) {
                    pomDir = MavenPomDir.fromClass(appClass);
                    if (pomDir == null)
                        throw new IllegalUsageException("Not belongs to a maven project: " + appClass);
                } else
                    throw new IllegalUsageException("Not with-in a maven project.");
            }
            outDir = pomDir.getBaseDir();
        }

        if (includeTables == null && includeViews == null)
            includeTables = includeViews = true;

        connection = dataContext.getConnection();

        for (String arg : args) {
            if (arg.startsWith("@")) {
                String path = arg.substring(1);
                AbstractStreamResource resource;

                if (new File(path).exists()) {
                    resource = new FileResource(new File(path));
                } else {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    URL classRes = classLoader.getResource(path);
                    if (classRes == null)
                        throw new IllegalArgumentException("Bad resource path: " + path);
                    resource = new URLResource(classRes);
                }

                for (String line : resource.read().lines()) {
                    String name = line.trim();
                    if (name.isEmpty() || name.startsWith("#"))
                        continue;
                    TableOid oid = TableOid.parse(name);
                    catalogSubset.addTable(oid);
                }

            } else if (arg.endsWith(".*.*")) {
                catalogSubset.addAllSchemas();
            } else if (arg.endsWith(".*")) {
                String schemaName = StringPart.before(arg, ".*");
                // schemaName = schemaName.toLowerCase();
                catalogSubset.addFullSchema(schemaName);
            } else {
                TableOid oid = TableOid.parse(arg);
                catalogSubset.addTable(oid);
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

        if (saveCatalogAs != null)
            XmlFn.save(catalog, saveCatalogAs);

        catalog.accept(new ICatalogVisitor() {
            @Override
            public boolean beginTableView(ITableViewMetadata table) {
                return processTableView(table);
            }
        });

        connection.close();
    }

    public static void main(String[] args)
            throws Exception {
        JavaGen app = new JavaGen(DataHub.getPreferredHub().getMain());
        app.execute(args);
    }

}
