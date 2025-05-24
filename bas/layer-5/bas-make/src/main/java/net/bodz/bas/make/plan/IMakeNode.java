package net.bodz.bas.make.plan;

import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.make.MakeAction;
import net.bodz.bas.make.MakeNodeType;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeNode
        extends IElement {

    @NotNull
    MakeNodeType getType();

    default boolean isSelect() {
        return getType() == MakeNodeType.SELECT;
    }

    default boolean isSequence() {
        return getType() == MakeNodeType.SEQUENCE;
    }

    default boolean isAction() {
        return getType() == MakeNodeType.ACTION;
    }

    @NotNull
    List<IMakeNode> getInputs();

    MakeAction<?> getActionCall();

}
