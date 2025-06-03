package net.bodz.bas.make;

import net.bodz.bas.make.strategy.IMakeStrategy;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractMakeRule<T extends IKeyData<TK, TT>, TK, TT>
        implements IMakeRule<T, TK, TT> {

    IMakeStrategy source;

    public AbstractMakeRule(@NotNull IMakeStrategy source) {
        this.source = source;
    }

    @Override
    public IMakeStrategy getSource() {
        return source;
    }

    public void setSource(IMakeStrategy source) {
        this.source = source;
    }

}
