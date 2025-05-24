package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule0<T extends IKeyData<TK, TT>, TK, TT>
        implements IMakeRule0<T, TK, TT> {

    int priority;
    IMakeable0<TT> fn;

    public SimpleMakeRule0(int priority, @NotNull IMakeable0<TT> fn) {
        this.priority = priority;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }


    @Override
    public TT make()
            throws MakeException {
        return fn.make();
    }

    public static <T extends IKeyData<TK, TT>, TK, TT> //
    Builder<T, TK, TT> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT> {

        int priority;
        IMakeable0<TT> fn;

        public Builder<T, TK, TT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT> fn(@NotNull IMakeable0<TT> fn) {
            this.fn = fn;
            return this;
        }

        public SimpleMakeRule0<T, TK, TT> build() {
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleMakeRule0<>(priority, fn);
        }

    }

}
