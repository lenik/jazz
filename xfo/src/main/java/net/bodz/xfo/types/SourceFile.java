package net.bodz.xfo.types;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import net.bodz.bas.lang.err.ParseException;

public class SourceFile extends AttributedFile {

    private final SourceType sourceType;

    public SourceFile(File file, SourceType sourceType) {
        super(file);
        if (sourceType == null)
            throw new NullPointerException("sourceType");
        this.sourceType = sourceType;
    }

    @Override
    protected AttributedElement fetchAttributes() throws IOException, ParseException {
        File file = getFile();
        Reader reader = sourceType.open(file);
        try {
            Map<String, String> map = sourceType.parseAttributes(file.getName(), reader);
            return new MapAttributes(map);
        } finally {
            reader.close();
        }
    }

}
