package net.bodz.lily.entity.manager;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.err.FormatException;

public interface IDocxBuilder {

    WordprocessingMLPackage buildDocx(ResolvedEntity resolvedEntity)
            throws FormatException;

}
