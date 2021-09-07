package net.bodz.bas.text;

import java.util.function.Function;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IMessageModifier
        extends
            Function<String, String> {

}
