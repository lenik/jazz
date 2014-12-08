package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.FlatfInput;
import net.bodz.bas.fmt.flatf.FlatfLoader;
import net.bodz.bas.fmt.flatf.IFlatfInput;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.mda.xjdoc.AbstractXjdocProvider;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;

public class FlatfXjdocProvider
        extends AbstractXjdocProvider {

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
    public ClassDoc getClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        return load(clazz, DEFAULT_EXTENSION);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public ClassDoc load(Class<?> clazz, String extension)
            throws XjdocLoaderException {
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
        ClassDoc doc;
        try {
            doc = load(clazz.getName(), in);
        } catch (Exception e) {
            throw new XjdocLoaderException(e.getMessage(), e);
        }
        return doc;
    }

    public final ClassDoc load(String fqcn, IStreamInputSource inputSource)
            throws IOException, ParseException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (inputSource == null)
            throw new NullPointerException("inputSource");

        Reader reader = inputSource.newReader();
        try {
            FlatfInput in = new FlatfInput(reader);
            return load(fqcn, in);
        } finally {
            reader.close();
        }
    }

    public ClassDoc load(String fqcn, IFlatfInput in)
            throws ParseException, IOException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (in == null)
            throw new NullPointerException("in");

        ClassDoc classDoc = createClassDoc(fqcn);
        ImportMap importMap = classDoc.getOrCreateImports();

        IOptions options = new Options() //
                .addOption(ITagLibrary.class, getTagLibrary()) //
                .addOption(ImportMap.class, importMap);

        flatfLoader.load(in, classDoc, options);
        return classDoc;
    }

    ClassDoc createClassDoc(String fqcn) {
        return new ClassDoc(getTagLibrary(), fqcn);
    }

}
