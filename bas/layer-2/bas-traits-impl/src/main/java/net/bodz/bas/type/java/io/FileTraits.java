package net.bodz.bas.type.java.io;

import java.io.File;

import net.bodz.bas.context.clg.SystemCLG;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.exception.ParseException;

public class FileTraits
        extends AbstractCommonTraits<File> {

    /**
     * The context cwd.
     */
    @ValueType(File.class)
    public static final String textformContextDirectory = "textform.contextDirectory";
    public static final File defaultTextformContextDirectory = SystemCLG.cwd.get();

    public FileTraits() {
        super(File.class);
    }

    @Override
    public IParser<File> getParser() {
        return this;
    }

    @Override
    public File parse(String text)
            throws ParseException {
        File file = new File(defaultTextformContextDirectory, text);
        return file;
    }

    @Override
    public File parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        File contextDirectory = defaultTextformContextDirectory;

        for (NegotiationParameter param : negotiation) {
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
