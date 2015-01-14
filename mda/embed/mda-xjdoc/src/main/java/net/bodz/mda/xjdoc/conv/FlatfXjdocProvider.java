package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;

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
        int lastDot = fqcn.lastIndexOf('.');
        String base = lastDot == -1 ? fqcn : fqcn.substring(lastDot + 1);
        URL resource = clazz.getResource(base + "." + extension);
        if (resource == null)
            return null;

        IStreamInputSource in = new URLResource(resource);
        return load(clazz.getName(), in);
    }

    public final ClassDoc load(String fqcn, IStreamInputSource inputSource)
            throws XjdocLoaderException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (inputSource == null)
            throw new NullPointerException("inputSource");

        try {
            Reader reader = inputSource.newReader();
            try {
                FlatfInput in = new FlatfInput(reader);
                return load(fqcn, in);
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            throw new XjdocLoaderException("Failed to read.", e);
        }
    }

    public ClassDoc load(String fqcn, IFlatfInput in)
            throws XjdocLoaderException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (in == null)
            throw new NullPointerException("in");

        ClassDoc classDoc = createClassDoc(fqcn);
        ImportMap importMap = classDoc.getOrCreateImports();

        IOptions options = new Options() //
                .addOption(ITagLibrary.class, getTagLibrary()) //
                .addOption(ImportMap.class, importMap);

        try {
            flatfLoader.load(in, classDoc, options);
        } catch (Exception e) {
            throw new XjdocLoaderException("Failed to load flatf resource for " + fqcn, e);
        }
        return classDoc;
    }

    ClassDoc createClassDoc(String fqcn) {
        return new ClassDoc(getTagLibrary(), fqcn);
    }

}
