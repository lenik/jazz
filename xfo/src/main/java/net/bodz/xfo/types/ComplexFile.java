package net.bodz.xfo.types;

import java.io.IOException;

import net.bodz.bas.lang.err.ParseException;

public class ComplexFile extends AttributedFile {

    private final AttributedFile[] attributedFiles;

    public ComplexFile(AttributedFile... attributedFiles) {
        // assert attributedFiles.length > 0;
        super(attributedFiles[0].getFile());
        this.attributedFiles = attributedFiles;
    }

    @Override
    public AttributedElement getAttributes() throws IOException, ParseException {
        return fetchAttributes();
    }

    protected AttributedElement fetchAttributes() throws IOException, ParseException {
        AttributedElement[] av = new AttributedElement[attributedFiles.length];
        for (int i = 0; i < attributedFiles.length; i++)
            av[i] = attributedFiles[i].getAttributes();
        ComplexAttributes merged = new ComplexAttributes(av);
        return merged;
    }

}
