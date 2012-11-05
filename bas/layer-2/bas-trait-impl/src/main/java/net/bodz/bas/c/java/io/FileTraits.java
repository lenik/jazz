package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.meta.util.ReferredType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;

public class FileTraits
        extends AbstractCommonTraits<File> {

    /**
     * The context cwd.
     */
    @ReferredType(File.class)
    public static final String textformContextDirectory = "textform.contextDirectory";
    public static final File defaultTextformContextDirectory = SystemColos.workdir.get();

    public FileTraits() {
        super(File.class);
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
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
