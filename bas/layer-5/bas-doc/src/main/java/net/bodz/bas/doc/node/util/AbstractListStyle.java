package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.RstFn;

public abstract class AbstractListStyle
        implements
            IListStyle,
            IRstForm {

    HorizAlignment justify;
    MeasureLength left;
    MeasureLength hanging;
    MeasureLength tabPosition;

    @Override
    public HorizAlignment getJustify() {
        return justify;
    }

    public void setJustify(HorizAlignment justify) {
        this.justify = justify;
    }

    @Override
    public MeasureLength getLeft() {
        return left;
    }

    public void setLeft(MeasureLength left) {
        this.left = left;
    }

    @Override
    public MeasureLength getHanging() {
        return hanging;
    }

    public void setHanging(MeasureLength hanging) {
        this.hanging = hanging;
    }

    @Override
    public MeasureLength getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(MeasureLength tabPosition) {
        this.tabPosition = tabPosition;
    }

    @Override
    public String toString() {
        return RstFn.toString(this);
    }

}
