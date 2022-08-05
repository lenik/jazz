package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.catalog.*;
import net.bodz.lily.gen.model.java.*;

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

    void makeView(ITableViewMetadata view) {

    }

    void makeTable(ITableMetadata table)
            throws SQLException, IOException {
        String q_table_name = table.getCompactName();
        String qTableName = StringId.UL.toQCamel(q_table_name);

        String qSchema = null;
        String name = qTableName;
        String pkg = parentPackage;
        int lastDot = qTableName.lastIndexOf('.');
        if (lastDot != -1) {
            qSchema = qTableName.substring(0, lastDot);
            name = qTableName.substring(lastDot + 1);
            pkg += "." + qSchema;
        }
        String qName = pkg + "." + name;
        String pkgDir = pkg.replace('.', '/') + "/";

        File gen_ = new File(outDir, generatedDirname + pkgDir);
        File skel_ = new File(outDir, skelDirname + pkgDir);
        File res_ = new File(outDir, skelResDirname + pkgDir);

        String implPkg = pkg + ".impl";

        ITreeOut out = open(gen_, Naming.stuff(name) + ".java", true);
        new EntityStuffBuilder(qName, pkg + "." + Naming.stuff(name)).build(out, table);
        out.close();

        IColumnMetadata[] pkv = table.getPrimaryKeyColumns();
        if (pkv.length > 1) {
            out = open(gen_, Naming.id(name) + ".java", true);
            new EntityIdBuilder(qName).build(out, table);
            out.close();
        }

        if ((out = open(skel_, name + ".java", false)) != null)
            new EntitySkelBuilder(qName, qName).build(out, table);

        out = open(gen_, "impl/" + Naming.maskStuff(name) + ".java", true);
        new EntityMaskStuffBuilder(qName, implPkg + "." + Naming.maskStuff(name)).build(out, table);

        if ((out = open(skel_, "impl/" + Naming.mask(name) + ".java", false)) != null)
            new EntityMaskSkelBuilder(qName, implPkg + "." + Naming.mask(name)).build(out, table);

        out = open(gen_, "impl/" + Naming.index(name) + ".java", true);
        new EntityIndexBuilder(qName, implPkg + "." + Naming.index(name)).build(out, table);

        if ((out = open(res_, "impl/" + Naming.mapper(name) + ".xml", false)) != null)
            new MapperXmlBuilder(qName, implPkg + "." + Naming.mapper(name)).build(out, table);

        if ((out = open(skel_, "impl/" + Naming.mapper(name) + ".java", false)) != null)
            new EntityMapperBuilder(qName, implPkg + "." + Naming.mapper(name)).build(out, table);

        out = open(gen_, "impl/" + Naming.mapperTest(name) + ".java", true);
        new EntityMapperTestBuilder(qName, implPkg + "." + Naming.mapperTest(name)).build(out, table);

        // samples content can be changed, modify it will cause unnecessary VCS commits.
        if ((out = open(gen_, "impl/" + Naming.samples(name) + ".java", false)) != null)
            new EntitySamplesStuffBuilder(qName, implPkg + "." + Naming.samples(name)).build(out, table);
    }

    ITreeOut open(File parent, String name, boolean overwrite)
            throws FileNotFoundException {
        File file = new File(parent, name);
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();
        return open(file, overwrite);
    }

    ITreeOut open(File file, boolean overwrite)
            throws FileNotFoundException {
        if (file.exists())
            if (!overwrite)
                return null;
        String href = FilePath.getRelativePath(file, outDir);
        logger.info("Generate: " + href);
        FileOutputStream fileOut = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fileOut, Charsets.UTF8);
        WriterPrintOut wpo = new WriterPrintOut(osw);
        return TreeOutImpl.from(wpo);
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (parentPackage == null)
            throw new IllegalArgumentException("parent-package isn't specified.");

        outDir = MavenPomDir.fromClass(appClass).getBaseDir();

        connection = dataContext.getConnection();

        for (String arg : args) {
            if (arg.endsWith(".*.*")) {
                catalogSubset.addAllSchemas();
            } else if (arg.endsWith(".*")) {
                String schemaName = StringPart.before(arg, ".*");
                // schemaName = schemaName.toLowerCase();
                catalogSubset.addFullSchema(schemaName);
            } else {
                TableId id = new TableId();
                id.setFullName(arg);
                catalogSubset.addTable(id);
            }
        }

        DefaultCatalogMetadata catalog = new DefaultCatalogMetadata();
        catalog.setJDBCLoadSelector(new IJDBCLoadSelector() {
            @Override
            public boolean selectSchema(SchemaId id) {
                ContainingType type = catalogSubset.contains(id.getSchemaName());
                System.out.println("SCHEMA " + id + ": " + type);
                return type != ContainingType.NONE;
            }

            @Override
            public boolean selectTable(TableId id) {
                boolean contains = catalogSubset.contains(id);
                System.out.println("TABLE " + id + ": " + contains);
                return contains;
            }
        });
        catalog.loadFromJDBC(connection, "TABLE", "VIEW");
        catalog.dump();

        catalog.accept(new ICatalogVisitor() {
            @Override
            public boolean beginTableView(ITableViewMetadata table) {
                return processTableView(table);
            }
        });

        connection.close();
    }

}
