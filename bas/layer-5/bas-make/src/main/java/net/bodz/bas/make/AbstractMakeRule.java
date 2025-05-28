package net.bodz.bas.make;

import net.bodz.bas.make.strategy.IMakeStrategy;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractMakeRule<T extends IKeyData<?, ?>>
        implements IMakeRule<T> {

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
