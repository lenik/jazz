package net.bodz.bas.shell.util;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.program.meta.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;
import net.bodz.mda.xjdoc.util.ImportMap;

/**
 * Dump xjdocs attached to the class
 * 
 * <p lang="zh-cn">
 * 列示类上附着的xjdoc文档
 * 
 * @label xjdoc-dumper
 * @label.zh.cn Xjdoc列示器
 */
@MainVersion({ 0, 0 })
@ProgramName("xjdmp")
public class XjdocDumper
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(XjdocDumper.class);

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        TagLibraryLoader taglibLoader = new TagLibraryLoader(classLoader);
        TagLibrarySet taglibs = taglibLoader.parseSet("*");

        ITreeOut out = TreeOutImpl.from(Stdio.cout);

        for (String fqcn : args) {
            for (Class<?> clazz = Class.forName(fqcn); clazz != null; clazz = clazz.getSuperclass()) {
                ClassDoc classDoc = ClassDocLoader.load(clazz);
                if (classDoc == null) {
                    out.println("Class has no doc: ", clazz.getName());
                    continue;
                }

                out.println("Class " + clazz.getName());
                out.enter();

                ImportMap classImports = classDoc.getOrCreateImports();
                IOptions options = new Options() //
                        .addOption(ITagLibrary.class, taglibs) //
                        .addOption(ImportMap.class, classImports);

                BCharOut buf = new BCharOut(1024);
                FlatfOutput ffOut = new FlatfOutput(buf);
                classDoc.writeObject(ffOut, options);

                String ff = buf.toString();
                ff = ff.replace("\n", "\n    ");
                out.println(ff);
                out.leave();
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        new XjdocDumper().execute(args);
    }

}
