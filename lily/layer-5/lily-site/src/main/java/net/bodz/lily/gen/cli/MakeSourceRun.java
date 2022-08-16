package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.bas.t.catalog.IViewMetadata;
import net.bodz.lily.gen.model.java.*;

public class MakeSourceRun {

    static final Logger logger = LoggerFactory.getLogger(MakeSourceRun.class);

    JavaModelGenerator app;

    String simpleName;
    String packageName;
    String className;
    String implPackageName;

    File stuffDir;
    File skelJavaDir;
    File skelResourcesDir;

    ITableViewMetadata tableView;

    boolean overwriteMapperXml = true;

    public MakeSourceRun(JavaModelGenerator app, ITableViewMetadata tableView) {
        this.app = app;
        this.tableView = tableView;

        String full_table_name = tableView.getCompactName();
        String qTableName = StringId.UL.toQCamel(full_table_name);

        String fullSchemaName = null;
        simpleName = qTableName;
        packageName = app.parentPackage;
        int lastDot = qTableName.lastIndexOf('.');
        if (lastDot != -1) {
            fullSchemaName = qTableName.substring(0, lastDot);
            simpleName = qTableName.substring(lastDot + 1);
            packageName += "." + fullSchemaName;
        }
        className = packageName + "." + simpleName;
        implPackageName = packageName + ".impl";

        String packagePath = packageName.replace('.', '/') + "/";
        stuffDir = new File(app.outDir, app.generatedDirname + packagePath);
        skelJavaDir = new File(app.outDir, app.skelDirname + packagePath);
        skelResourcesDir = new File(app.outDir, app.skelResDirname + packagePath);
    }

    public void makeTable()
            throws IOException {
        ITableMetadata table = (ITableMetadata) tableView;

        // _Person_stuff.java
        FragmentConfig fc = new FragmentConfig();
        fc.dir = stuffDir;
        fc.baseName = NamingUtil.stuff(simpleName) + ".java";
        fc.fragmentName = packageName + "." + NamingUtil.stuff(simpleName);
        ITreeOut out = fc.open(true);
        new TableStuffBuilder(className, fc.fragmentName).build(out, table);
        out.close();

        // Person_Id.java
        IColumnMetadata[] pkv = table.getPrimaryKeyColumns();
        if (pkv.length > 1) {
            fc.dir = stuffDir;
            fc.baseName = NamingUtil.id_iface(simpleName) + ".java";
            out = fc.open(true);
            new TableIdBuilder(className).build(out, table);
            out.close();

            fc.dir = stuffDir;
            fc.baseName = NamingUtil.id(simpleName) + ".java";
            out = fc.open(true);
            new TableIdBuilder(className).build(out, table);
            out.close();
        }

        fc.dir = skelJavaDir;
        fc.baseName = simpleName + ".java";
        if ((out = fc.open(false)) != null)
            new TableSkelBuilder(className, className).build(out, table);

        fc.dir = stuffDir;
        fc.baseName = "impl/" + NamingUtil.maskStuff(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.maskStuff(simpleName);
        out = fc.open(true);
        new TableMaskStuffBuilder(className, fc.fragmentName).build(out, table);

        fc.dir = skelJavaDir;
        fc.baseName = "impl/" + NamingUtil.mask(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.mask(simpleName);
        if ((out = fc.open(false)) != null)
            new TableMaskSkelBuilder(className, fc.fragmentName).build(out, table);

        fc.dir = stuffDir;
        fc.baseName = "impl/" + NamingUtil.index(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.index(simpleName);
        out = fc.open(true);
        new TableIndexBuilder(className, fc.fragmentName).build(out, table);

        fc.dir = skelResourcesDir;
        fc.baseName = "impl/" + NamingUtil.mapper(simpleName) + ".xml";
        fc.fragmentName = implPackageName + "." + NamingUtil.mapper(simpleName);
        if ((out = fc.open(overwriteMapperXml)) != null)
            new TableMapperXmlBuilder(className, fc.fragmentName).build(out, table);

        fc.dir = skelJavaDir;
        fc.baseName = "impl/" + NamingUtil.mapper(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.mapper(simpleName);
        if ((out = fc.open(false)) != null)
            new TableMapperBuilder(className, fc.fragmentName).build(out, table);

        fc.dir = stuffDir;
        fc.baseName = "impl/" + NamingUtil.mapperTest(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.mapperTest(simpleName);
        out = fc.open(true);
        new TableMapperTestBuilder(className, fc.fragmentName).build(out, table);

        // samples content can be changed, modify it will cause unnecessary VCS commits.
        fc.dir = stuffDir;
        fc.baseName = "impl/" + NamingUtil.samples(simpleName) + ".java";
        fc.fragmentName = implPackageName + "." + NamingUtil.samples(simpleName);
        if ((out = fc.open(false)) != null)
            new TableSamplesStuffBuilder(className, fc.fragmentName).build(out, table);
    }

    public void makeView()
            throws IOException {
        IViewMetadata view = (IViewMetadata) tableView;

        ITreeOut out = open(stuffDir, NamingUtil.stuff(simpleName) + ".java", true);
        new ViewStuffBuilder(className, packageName + "." + NamingUtil.stuff(simpleName)).build(out, tableView);
        out.close();

        IColumnMetadata[] pkv = view.getPrimaryKeyColumns();
        if (pkv.length > 1) {
            out = open(stuffDir, NamingUtil.id(simpleName) + ".java", true);
            new TableIdBuilder(className).build(out, view);
            out.close();
        }

        if ((out = open(skelJavaDir, simpleName + ".java", false)) != null)
            new ViewSkelBuilder(className, className).build(out, view);

        out = open(stuffDir, "impl/" + NamingUtil.maskStuff(simpleName) + ".java", true);
        new ViewMaskStuffBuilder(className, implPackageName + "." + NamingUtil.maskStuff(simpleName)).build(out, view);

        if ((out = open(skelJavaDir, "impl/" + NamingUtil.mask(simpleName) + ".java", false)) != null)
            new ViewMaskSkelBuilder(className, implPackageName + "." + NamingUtil.mask(simpleName)).build(out, view);

        out = open(stuffDir, "impl/" + NamingUtil.index(simpleName) + ".java", true);
        new ViewIndexBuilder(className, implPackageName + "." + NamingUtil.index(simpleName)).build(out, view);

        if ((out = open(skelResourcesDir, "impl/" + NamingUtil.mapper(simpleName) + ".xml", overwriteMapperXml)) != null)
            new ViewMapperXmlBuilder(className, implPackageName + "." + NamingUtil.mapper(simpleName)).build(out, view);

        if ((out = open(skelJavaDir, "impl/" + NamingUtil.mapper(simpleName) + ".java", false)) != null)
            new ViewMapperBuilder(className, implPackageName + "." + NamingUtil.mapper(simpleName)).build(out, view);

//        out = open(stuffDir, "impl/" + Naming.mapperTest(simpleName) + ".java", true);
//        new ViewMapperTestBuilder(className, implPackageName + "." + Naming.mapperTest(simpleName)).build(out,
//                view);
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
        String href = FilePath.getRelativePath(file, app.outDir);
        logger.info("Generate: " + href);
        FileOutputStream fileOut = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fileOut, Charsets.UTF8);
        WriterPrintOut wpo = new WriterPrintOut(osw);
        return TreeOutImpl.from(wpo);
    }

}
