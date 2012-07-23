package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.mda.xjdoc.util.WordTokenizer;

public class FlatfLoader {

    public void load(IStreamInputSource inputSource, IFlatfSerializable target, INegotiation negotiation)
            throws IOException, ParseException, NegotiationException {
        Reader reader = inputSource.newReader();
        try {
            FlatfInput in = new FlatfInput(reader);
            load(in, target, negotiation);
        } finally {
            reader.close();
        }
    }

    public void load(IFlatfInput in, IFlatfSerializable target, INegotiation negotiation)
            throws ParseException, IOException, NegotiationException {
        int token;
        String currentSection = in.getSectionName();
        ISectionHandler sectionHandler = target.getSectionHandler(currentSection, negotiation);

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
                sectionHandler = target.getSectionHandler(currentSection, negotiation);

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
