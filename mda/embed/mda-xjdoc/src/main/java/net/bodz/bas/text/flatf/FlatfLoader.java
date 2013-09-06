package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.util.WordTokenizer;

public class FlatfLoader {

    public void load(IStreamInputSource inputSource, IFlatfSerializable target, IOptions options)
            throws IOException, ParseException {
        Reader reader = inputSource.newReader();
        try {
            FlatfInput in = new FlatfInput(reader);
            load(in, target, options);
        } finally {
            reader.close();
        }
    }

    public void load(IFlatfInput in, IFlatfSerializable target, IOptions options)
            throws ParseException, IOException {
        int token;
        String currentSection = in.getSectionName();
        ISectionHandler sectionHandler = target.getSectionHandler(currentSection, options);

        while ((token = in.next()) != IFlatfInput.EOF) {
            switch (token) {
            case IFlatfInput.PI:
                String piText = in.getPiText();
                String piCommand = WordTokenizer.firstWord(piText);
                String piData = piText.substring(piCommand.length());
                piCommand = piCommand.trim();
                piData = piData.trim();

                if (!sectionHandler.pi(piCommand, piData)) {
                    throw new IllegalUsageException("Can't process instruction: " + piText);
                }
                break;

            case IFlatfInput.SECTION_BEGIN:
                if (sectionHandler != null)
                    sectionHandler.sectionEnd(currentSection);

                currentSection = in.getSectionName();
                sectionHandler = target.getSectionHandler(currentSection, options);

                if (sectionHandler == null) {
                    throw new ParseException("Unhandled section: " + currentSection);
                } else {
                    sectionHandler.sectionBegin(in.getSectionName());
                }
                break;

            case IFlatfInput.ATTRIBUTE:
                String attributeName = in.getAttributeName();
                String attributeText = in.getAttributeText();
                sectionHandler.attribute(attributeName, attributeText);
                break;

            default:
                throw new UnexpectedException();
            }
        }
    }

}
