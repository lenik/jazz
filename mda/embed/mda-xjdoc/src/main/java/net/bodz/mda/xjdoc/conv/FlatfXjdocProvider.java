package net.bodz.mda.xjdoc.conv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.flatf.FlatfInput;
import net.bodz.bas.fmt.flatf.FlatfLoader;
import net.bodz.bas.fmt.flatf.IFlatfInput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.mda.xjdoc.AbstractXjdocProvider;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;

public class FlatfXjdocProvider
        extends AbstractXjdocProvider {

    static final Logger logger = LoggerFactory.getLogger(FlatfXjdocProvider.class);

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
    public ClassDoc loadClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        return loadExtension(clazz, DEFAULT_EXTENSION);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public ClassDoc loadExtension(Class<?> clazz, String extension)
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

        ByteArrayOutputStream buf = new ByteArrayOutputStream(4096);
        try {
            InputStream in = resource.openStream();
            byte[] block = new byte[4096];
            int cb;
            while ((cb = in.read(block, 0, block.length)) != -1) {
                buf.write(block, 0, cb);
            }
        } catch (IOException e) {
            throw new UnexpectedException("Error read from resource: " + e.getMessage(), e);
        }
        String text;
        try {
            text = buf.toString("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
        return loadText(clazz.getName(), text);
    }

    public final ClassDoc loadText(String fqcn, String text)
            throws XjdocLoaderException {
        if (fqcn == null)
            throw new NullPointerException("fqcn");
        if (text == null)
            throw new NullPointerException("text");

        try {
            Reader reader = new StringReader(text);
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
