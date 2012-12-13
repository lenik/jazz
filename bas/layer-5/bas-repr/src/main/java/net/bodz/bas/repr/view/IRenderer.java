package net.bodz.bas.repr.view;

import java.io.IOException;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.INegotiation;

@IndexedType
public interface IRenderer {

    Class<?> getType();

    String getContentType();

    boolean render(Object object, INegotiation negotiation)
            throws IOException;

}
