package net.bodz.bas.esm;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ITsTypeInfoProvider {

    EsmName forClassName(String className);

    EsmName forClass(Class<?> clazz);

}
