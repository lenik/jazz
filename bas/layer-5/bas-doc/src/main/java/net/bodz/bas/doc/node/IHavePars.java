package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.t.list.IAutoList;

public interface IHavePars
        extends
            INode {

    @Override
    default boolean havePars() {
        return true;
    }

    IAutoList<IPar> getPars();

    default PartGroup addSectionGroup(PartLevel level) {
        return getPars().append(new PartGroup(this, level));
    }

    default PartGroup addSectionGroup(int level) {
        return getPars().append(new PartGroup(this, level));
    }

    default ListPar addListPar() {
        return getPars().append(new ListPar(this));
    }

    default ListPar addListPar(boolean ordered) {
        return getPars().append(new ListPar(this, ordered));
    }

    default Table addTable() {
        return getPars().append(new Table(this));
    }

    default Hr addHr() {
        return getPars().append(new Hr(this));
    }

    default TextPar addTextPar() {
        return getPars().append(new TextPar(this));
    }

}
