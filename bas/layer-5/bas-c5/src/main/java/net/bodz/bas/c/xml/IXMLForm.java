package net.bodz.bas.c.xml;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.rtx.IOptions;

public interface IXMLForm<T> {

    int typerIndex = 260403962; // IXMLForm

    T parseXML(IStreamInputSource source, IOptions options)
            throws IOException, ParseException;

    void formatXML(T object, IStreamOutputTarget target, IOptions options)
            throws IOException;

}
