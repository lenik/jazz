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

    // String genDir= "src/main/java/" ;
    String genDir = "src/main/generated/";
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
        String q_name = table.getNecessaryQualifiedName();
        String qName = StringId.UL.toQCamel(q_name);

        int lastDot = qName.lastIndexOf('.');
        String qPackage = null;
        String SimpleName = qName;
        String pkgDir = "";
        if (lastDot != -1) {
            qPackage = qName.substring(0, lastDot);
            SimpleName = qName.substring(lastDot + 1);
            pkgDir = qPackage.replace('.', '/') + "/";
        }
        String pkg = parentPackage + "." + qPackage;
        String fqcn = pkg + "." + SimpleName;
        String impl = pkg + ".impl";

        String parentPkgDir = parentPackage.replace('.', '/') + "/";

        String pkgPath = genDir + parentPkgDir + pkgDir;
        File parent = new File(outDir, pkgPath);

        ITreeOut out = open(parent, SimpleName + ".java");
        new EntityClassBuilder(fqcn).build(out, table);
        out.close();

        IColumnMetadata[] pkv = table.getPrimaryKeyColumns();
        if (pkv.length > 1) {
            String idType = SimpleName + EntityIdBuilder.ID_SUFFIX;
            out = open(parent, idType + ".java");
            new EntityIdBuilder(fqcn).build(out, table);
            out.close();
        }

        out = open(parent, "impl/" + SimpleName + "Mask.java");
        new EntityMaskBuilder(fqcn, impl + "." + SimpleName + "Mask").build(out, table);

        out = open(parent, "impl/" + SimpleName + "Index.java");
        new EntityIndexBuilder(fqcn, impl + "." + SimpleName + "Index").build(out, table);

        out = open(parent, "impl/" + SimpleName + "Mapper.java");
        new EntityMapperBuilder(fqcn, impl + "." + SimpleName + "Mapper").build(out, table);

        out = open(parent, "impl/" + SimpleName + "MapperTest.java");
        new EntityMapperTestBuilder(fqcn, impl + "." + SimpleName + "MapperTest").build(out, table);

        out = open(parent, "impl/" + SimpleName + "Samples.java");
        new EntitySamplesBuilder(fqcn, impl + "." + SimpleName + "Samples").build(out, table);

        out.close();
    }

    ITreeOut open(File parent, String name)
            throws FileNotFoundException {
        File file = new File(parent, name);
        file.getParentFile().mkdirs();
        return open(file);
    }

    ITreeOut open(File file)
            throws FileNotFoundException {
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
