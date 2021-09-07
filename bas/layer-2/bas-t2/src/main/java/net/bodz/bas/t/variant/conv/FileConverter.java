package net.bodz.bas.t.variant.conv;

import java.io.File;
import java.math.BigDecimal;

import net.bodz.bas.err.TypeConvertException;

public class FileConverter
        extends AbstractVarConverter<File> {

    public FileConverter() {
        super(File.class);
    }

    @Override
    public File fromNumber(Number in)
            throws TypeConvertException {
        String path = in.toString();
        return new File(path);
    }

    @Override
    public File fromString(String in)
            throws TypeConvertException {
        return new File(in);
    }

    @Override
    public String toString(File value) {
        return value.getPath();
    }

    @Override
    public Number toNumber(File value) {
        String path = value.getPath();
        if (path.contains("."))
            return Double.parseDouble(path);
        else
            return new BigDecimal(path);
    }

    @Override
    public boolean toBoolean(File value) {
        return value.exists();
    }

    public static final FileConverter INSTANCE = new FileConverter();

}
