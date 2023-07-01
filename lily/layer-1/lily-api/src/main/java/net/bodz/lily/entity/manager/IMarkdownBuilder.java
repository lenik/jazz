package net.bodz.lily.entity.manager;

import net.bodz.bas.err.FormatException;

public interface IMarkdownBuilder {

    String buildMarkdown(ResolvedEntity resolvedEntity)
            throws FormatException;

}
