package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfLoader {

    void addSectionHandler(String sectionName, ISectionHandler sectionHandler);

    void load(IFlatfInput in)
            throws ParseException, IOException;

}
