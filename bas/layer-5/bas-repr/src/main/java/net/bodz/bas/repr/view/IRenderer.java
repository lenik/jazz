package net.bodz.bas.repr.view;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IRenderer {

    Class<?> getType();

    String getContentType();

    boolean render(Object object, INegotiation negotiation)
            throws IOException;

}
