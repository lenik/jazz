package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import net.bodz.bas.t.table.DefaultTableMetadata;
import net.bodz.bas.t.table.IColumnMetadata;
import net.bodz.bas.t.table.ITableMetadata;
import net.bodz.lily.gen.model.java.*;

public class JavaModelGenerator
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(JavaModelGenerator.class);

    String skelDirname = "src/main/java/";
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

    public JavaModelGenerator(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    void makeCatalog(String catalogName)
            throws SQLException, IOException {
        List<String> names = new ArrayList<>();
        ResultSet rs = connection.getMetaData().getSchemas(catalogName, null);
        while (rs.next()) {
            String name = rs.getString("SCHEMA_NAME");
            names.add(name);
        }
        rs.close();
        for (String schemaName : names)
            makeSchema(catalogName, schemaName);
    }

    void makeSchema(String catalogName, String schemaName)
            throws SQLException, IOException {
        List<String> names = new ArrayList<>();
        ResultSet rs = connection.getMetaData().getTables(catalogName, schemaName, null, //
                new String[] { "TABLE" });
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            // String type = rs.getString("TABLE_TYPE");
            // String remarks = rs.getString("REMARKS");
            names.add(name);
        }
        rs.close();
        for (String tableName : names)
            makeEntity(catalogName, schemaName, tableName);
    }

    void makeEntity(String qName)
            throws SQLException, IOException {
        DefaultTableMetadata table = DefaultTableMetadata.fromMetaData(connection, qName);
        makeEntity(table);
    }

    void makeEntity(String catalogName, String schemaName, String tableName)
            throws SQLException, IOException {
        DefaultTableMetadata table = DefaultTableMetadata.fromMetaData(connection, //
                catalogName, schemaName, tableName);
        makeEntity(table);
    }

    void makeEntity(ITableMetadata table)
            throws SQLException, IOException {
        String q_table_name = table.getNecessaryQualifiedName();
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

        if ((out = open(skel_, "impl/" + Naming.mapper(name) + ".java", false)) != null)
            new EntityMapperBuilder(qName, implPkg + "." + Naming.mapper(name)).build(out, table);

        out = open(gen_, "impl/" + Naming.mapperTest(name) + ".java", true);
        new EntityMapperTestBuilder(qName, implPkg + "." + Naming.mapperTest(name)).build(out, table);

        // samples content can be changed, modify it will cause unnecessary VCS commits.
        if ((out = open(gen_, "impl/" + Naming.samples(name) + ".java", false)) != null)
            new EntitySamplesStuffBuilder(qName, implPkg + "." + Naming.samples(name)).build(out, table);

        out.close();
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
            if (arg.endsWith(".*.*"))
                makeCatalog(StringPart.before(arg, ".*.*"));
            else if (arg.endsWith(".*"))
                makeSchema(null, StringPart.before(arg, ".*"));
            else
                makeEntity(arg);
        }

        connection.close();
    }

}
