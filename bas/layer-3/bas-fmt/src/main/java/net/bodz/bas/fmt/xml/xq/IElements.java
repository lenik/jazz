package net.bodz.bas.fmt.xml.xq;

import java.util.NoSuchElementException;

public interface IElements
        extends
            IXmlSelection {

    IElement getFirst();

    default IElement first()
            throws NoSuchElementException {
        IElement first = getFirst();
        if (first != null)
            return first;
        else
            throw new NoSuchElementException();
    }

}
