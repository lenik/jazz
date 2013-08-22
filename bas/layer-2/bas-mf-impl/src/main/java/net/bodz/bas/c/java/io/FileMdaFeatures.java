package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.INegotiation.IParameter;

public class FileMdaFeatures
        extends AbstractCommonMdaFeatures<File> {

    /**
     * The context cwd.
     */
    @ParameterType(File.class)
    public static final String textformContextDirectory = "textform.contextDirectory";
    public static final File defaultTextformContextDirectory = SystemColos.workdir.get();

    public FileMdaFeatures() {
        super(File.class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
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
    public File parse(String text, INegotiation negotiation)
            throws ParseException {
        File contextDirectory = defaultTextformContextDirectory;

        for (IParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (textformContextDirectory.equals(paramId))
                contextDirectory = (File) paramValue;
        }

        File file = new File(contextDirectory, text);
        return file;
    }

}
