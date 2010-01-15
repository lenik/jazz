package net.bodz.bas.text.lop.fsm;

import net.bodz.bas.annotations.ChainOrder;
import net.bodz.bas.annotations.ChainUsage;
import net.bodz.bas.hint.OverrideOption;


public interface CharSet extends Comparable<CharSet> {

    /**
     * It's safe to iterate from begin to end of a limited {@link CharSet}.
     */
    boolean isAbstract();

    int getBegin();

    int getEnd();

    boolean contains(int x);

    /**
     * @return <code>null</code> if droppable.
     */
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    CharSet reduce();

    int UNKNOWN = -1;
    int NONE = 0;
    int PARTIAL = 1;
    int ALL_THIS = 2;
    int ALL_THAT = 3;
    int EQUALS = 4;

    /**
     * Only called if this {@link CharSet} isn't abstract.
     * 
     * @return {@link #UNKNOWN}, {@link #NONE}, {@link #PARTIAL}, {@link #ALL_THIS},
     *         {@link #ALL_THAT}
     */
    int intersects(CharSet that);

    SplitResult split(CharSet that);

    int compareTo(CharSet that);

}
