package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.free.IllegalUsageException;
import javax.free.UnexpectedException;

import net.bodz.mda.xjdoc.util.WordTokenizer;

public class FlatfLoader
        implements IFlatfLoader {

    Map<String, ISectionHandler> sectionHandlers = new HashMap<String, ISectionHandler>();

    @Override
    public void addSectionHandler(String sectionName, ISectionHandler sectionHandler) {
        if (sectionName == null)
            throw new NullPointerException("sectionName");
        if (sectionHandler == null)
            throw new NullPointerException("sectionHandler");
        sectionHandlers.put(sectionName, sectionHandler);
    }

    @Override
    public void load(IFlatfInput in)
            throws ParseException, IOException {
        int token;
        String currentSection = in.getSectionName();
        ISectionHandler sectionHandler = sectionHandlers.get(currentSection);

        while ((token = in.next()) != IFlatfInput.EOF) {
            switch (token) {
            case IFlatfInput.PI:
                String piText = in.getPiText();
                String piCommand = WordTokenizer.firstWord(piText);
                String piParam = piText.substring(piCommand.length());
                piCommand = piCommand.trim();
                piParam = piParam.trim();
                processInstruction(piCommand, piParam);
                break;

            case IFlatfInput.SECTION_BEGIN:
                if (sectionHandler != null)
                    sectionHandler.sectionEnd(currentSection);

                currentSection = in.getSectionName();
                sectionHandler = sectionHandlers.get(currentSection);

                if (sectionHandler == null) {
                    throw new ParseException("Unhandled section: " + currentSection, -1);
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

    protected void processInstruction(String piCommand, String piText) {
        throw new IllegalUsageException("Undefined preprocess-instruction command: " + piCommand);
    }

}
