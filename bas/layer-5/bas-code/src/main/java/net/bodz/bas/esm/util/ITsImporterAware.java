package net.bodz.bas.esm.util;

import net.bodz.bas.esm.ITsImporter;

public interface ITsImporterAware {

    ITsImporter getTsImporter();

    default TsTypeResolver typeResolver() {
        return new TsTypeResolver(getTsImporter());
    }

    default TsTypeInfoResolver typeInfoResolver() {
        return new TsTypeInfoResolver(getTsImporter());
    }

}
