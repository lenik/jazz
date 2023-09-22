package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.HorizAlignment;

public interface IDocPar
        extends
            IDocNode {

    String END_OF_PAR = "\n";

    HorizAlignment getAlignment();

}
