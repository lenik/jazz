package net.bodz.bas.esm.util;

import net.bodz.bas.esm.ITsImporter;
import net.bodz.bas.t.tuple.QualifiedName;

public interface ITsImporterAware {

    ITsImporter getTsImporter();

    QualifiedName getThisType();

    default TsTypeResolver typeResolver() {
        TsTypeResolver resolver = new TsTypeResolver(getTsImporter());
        QualifiedName thisType = getThisType();
        if (thisType != null)
            resolver.thisType(thisType);
        return resolver;
    }

    default TsTypeInfoResolver typeInfoResolver() {
        TsTypeInfoResolver resolver = new TsTypeInfoResolver(getTsImporter());
        QualifiedName thisType = getThisType();
        if (thisType != null)
            resolver.thisType(thisType);
        return resolver;
    }

}
