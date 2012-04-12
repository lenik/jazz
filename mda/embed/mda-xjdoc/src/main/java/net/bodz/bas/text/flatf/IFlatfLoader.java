package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfLoader {

    ISectionHandler getSectionHandler(String sectionName);

    void load(IFlatfInput in)
            throws ParseException, IOException;

}
