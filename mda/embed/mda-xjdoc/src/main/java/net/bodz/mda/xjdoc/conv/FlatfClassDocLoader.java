package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.text.flatf.FlatfInput;
import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.ClassDocLoadException;
import net.bodz.mda.xjdoc.IClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;
import net.bodz.mda.xjdoc.util.ImportMap;

public class FlatfClassDocLoader
        implements IClassDocLoader {

    public static final String DEFAULT_EXTENSION = "ff";

    private FlatfLoader flatfLoader = new FlatfLoader();

    @Override
    public int getPriority() {
        return 10;
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    @Override
    public ClassDoc load(Class<?> clazz)
            throws ClassDocLoadException {
        return load(clazz, DEFAULT_EXTENSION);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public ClassDoc load(Class<?> clazz, String extension)
            throws ClassDocLoadException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (extension == null)
            throw new NullPointerException("extension");

        String fqcn = clazz.getName();
        String resourceName = fqcn.replace('.', '/') + "." + extension;

        /**
         * For Maven exec:java, the system class loader just includes plexus-classworlds, and
         * context class loader should be used.
         */
        ClassLoader resourceLoader = Thread.currentThread().getContextClassLoader();
        if (resourceLoader == null) {
            resourceLoader = clazz.getClassLoader();
            if (resourceLoader == null)
                throw new NullPointerException("resourceLoader");
        }

        URL resource = resourceLoader.getResource(resourceName);
        if (resource == null)
            return null;

        IStreamInputSource in = new URLResource(resource);
        TagLibrarySet taglibs = TagLibraryLoader.allFor(clazz);
        ClassDoc doc;
        try {
            doc = load(clazz.getName(), in, taglibs);
        } catch (Exception e) {
            throw new ClassDocLoadException(e.getMessage(), e);
        }
        return doc;
    }

    public final ClassDoc load(String fqcn, IStreamInputSource inputSource, ITagLibrary taglib)
            throws IOException, ParseException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (inputSource == null)
            throw new NullPointerException("inputSource");

        Reader reader = inputSource.newReader();
        try {
            FlatfInput in = new FlatfInput(reader);
            return load(fqcn, in, taglib);
        } finally {
            reader.close();
        }
    }

    public ClassDoc load(String fqcn, IFlatfInput in, ITagLibrary taglib)
            throws ParseException, IOException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (in == null)
            throw new NullPointerException("in");

        ClassDoc classDoc = createClassDoc(fqcn);
        ImportMap importMap = classDoc.getOrCreateImports();

        IOptions options = new Options() //
                .addOption(ITagLibrary.class, taglib) //
                .addOption(ImportMap.class, importMap);

        flatfLoader.load(in, classDoc, options);
        return classDoc;
    }

    ClassDoc createClassDoc(String fqcn) {
        return new ClassDoc(fqcn);
    }

}
