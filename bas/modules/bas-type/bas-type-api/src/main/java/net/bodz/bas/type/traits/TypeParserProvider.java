package net.bodz.bas.type.traits;

import java.util.Collection;
import java.util.Map.Entry;

public abstract class TypeParserProvider {

    public abstract Collection<Entry<Class<?>, Parser>> getTypeParsers();

}
