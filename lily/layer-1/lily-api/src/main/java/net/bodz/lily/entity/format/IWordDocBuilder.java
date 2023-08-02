package net.bodz.lily.entity.format;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.ResolvedEntity;

public interface IWordDocBuilder {

    WordprocessingMLPackage buildWordDoc(ResolvedEntity resolvedEntity, IVariantMap<String> q)
            throws FormatException;

}
