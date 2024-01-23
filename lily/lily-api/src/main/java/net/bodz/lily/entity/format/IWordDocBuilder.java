package net.bodz.lily.entity.format;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.ResolvedEntity;

public interface IWordDocBuilder {

    XWPFDocument buildWordDoc(ResolvedEntity resolvedEntity, IVariantMap<String> q)
            throws FormatException;

}
