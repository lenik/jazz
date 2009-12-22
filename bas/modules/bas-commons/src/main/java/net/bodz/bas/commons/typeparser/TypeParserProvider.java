package net.bodz.bas.commons.typeparser;

import java.util.Collection;
import java.util.Map.Entry;

public abstract class TypeParserProvider {

    public abstract Collection<Entry<Class<?>, Parser>> getTypeParsers();

}
