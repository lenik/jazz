package net.bodz.bas.semantictype.annotation;

import net.bodz.bas.semantictype.ISemanticType;

public @interface Type {

    Class<? extends ISemanticType<?>> value();

}
