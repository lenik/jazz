package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.HorizAlignment;

public interface IPar
        extends
            INode {

    String END_OF_PAR = "\n";

    default boolean isTextPar() {
        return false;
    }

    default boolean isListItem() {
        return false;
    }

    int getItemIndex();

    HorizAlignment getAlignment();

}
