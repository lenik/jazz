package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.rtx.IOptions;

public class FileTypeFeatures
        extends AbstractCommonTypeFeatures<File> {

    /**
     * The context cwd.
     */
    @ParameterType(File.class)
    public static final String textformContextDirectory = "textform.contextDirectory";
    public static final File defaultTextformContextDirectory = SystemColos.workdir.get();

    public FileTypeFeatures() {
        super(File.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public File parse(String text)
            throws ParseException {
        File file = new File(defaultTextformContextDirectory, text);
        return file;
    }

    @Override
    public File parse(String text, IOptions options)
            throws ParseException {
        File contextDirectory = options.get(textformContextDirectory, defaultTextformContextDirectory);
        File file = new File(contextDirectory, text);
        return file;
    }

}
