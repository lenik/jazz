package net.bodz.bas.compare;

import java.util.List;

public interface IListEditDelta<mutable_t>
        extends
            IEditDelta<List<mutable_t>> {

    @Override
    IListEditDeltaType getType();

}
