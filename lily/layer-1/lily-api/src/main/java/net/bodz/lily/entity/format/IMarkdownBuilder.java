package net.bodz.lily.entity.format;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.ResolvedEntity;

public interface IMarkdownBuilder {

    String buildMarkdown(ResolvedEntity resolvedEntity, IVariantMap<String> q)
            throws FormatException;

}
