package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.persistence.Table;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.lily.gen.MapperXmlFormatter;
import net.bodz.lily.gen.model.java.reflect.EntityClassModel;

/**
 * Generate mybatis mapper xml from java reflection.
 */
@ProgramName("class2xml")
public class XmlMapperGenerator
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(XmlMapperGenerator.class);

    String genDir="src/main/resources/";
    // String genDir = "generated-sources/main/resources/";

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

    void makePackage(String pkg)
            throws ReflectiveOperationException, IOException {
        String pkgDir = pkg.replace('.', '/') + "/";
        String pkgPath = "target/classes/" + pkgDir;
        File parent = new File(outDir, pkgPath);
        for (File file : parent.listFiles()) {
            if (file.isDirectory())
                continue;
            String simpleName = FilePath.stripExtension(file.getName());
            String fqcn = pkg + "." + simpleName;
            Class<?> clazz = Class.forName(fqcn);
            if (clazz.isAnnotationPresent(Table.class))
                makeClass(fqcn);
        }
    }

    void makeClass(String fqcn)
            throws ReflectiveOperationException, IOException {
        Class<?> clazz = Class.forName(fqcn);
        makeClass(clazz);
    }

    void makeClass(Class<?> clazz)
            throws IOException {
        String SimpleName = clazz.getSimpleName();

        String qPackage = clazz.getPackage().getName() + ".impl";
        String qName = qPackage + "." + SimpleName + "Mapper";

        String pkgDir = qPackage.replace('.', '/') + "/";
        String pkgPath = genDir + pkgDir;
        File parent = new File(outDir, pkgPath);
        parent.mkdirs();

        ITreeOut out = open(parent, SimpleName + "Mapper.xml");
        MapperXmlFormatter formatter = new MapperXmlFormatter(out);
        EntityClassModel entity = new EntityClassModel(clazz);
        formatter.mapperXml(qName, entity);
        out.close();
    }

    ITreeOut open(File parent, String name)
            throws FileNotFoundException {
        File file = new File(parent, name);
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

        for (String arg : args) {
            if (arg.equals("*"))
                makePackage(parentPackage);
            else
                makeClass(arg);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new XmlMapperGenerator().execute(args);
    }

}
