package net.bodz.bas.make.fn;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleMakeRule0<T extends IKeyData<TK, TT>, TK, TT> //
        implements IMakeRule0<T, TK, TT> {

    int priority;
    final Class<? extends T> targetType;
    final Class<? extends TK> keyType;
    final Class<? extends TT> dataType;
    IMakeable0<TT> fn;

    public SimpleMakeRule0(int priority, @NotNull Class<? extends T> targetType, @NotNull Class<? extends TK> keyType, @NotNull Class<? extends TT> dataType, @NotNull IMakeable0<TT> fn) {
        this.priority = priority;
        this.targetType = targetType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.fn = fn;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @NotNull
    @Override
    public Class<? extends T> getTargetType() {
        return targetType;
    }

    @NotNull
    @Override
    public Class<? extends TK> getKeyType() {
        return keyType;
    }

    @NotNull
    @Override
    public Class<? extends TT> getDataType() {
        return dataType;
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

    public static class Builder<T extends IKeyData<TK, TT>, TK, TT> { //

        int priority;
        Class<? extends T> targetType;
        Class<? extends TK> keyType;
        Class<? extends TT> dataType;
        IMakeable0<TT> fn;

        public Builder<T, TK, TT> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder<T, TK, TT> targetType(Class<? extends T> targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder<T, TK, TT> fn(@NotNull IMakeable0<TT> fn) {
            this.fn = fn;
            return this;
        }

        public Builder<T, TK, TT> input() {
            return this;
        }

        public SimpleMakeRule0<T, TK, TT> build() {
            if (targetType == null)
                throw new NullPointerException("targetType");
            if (keyType == null)
                keyType = TypeParam.infer1(targetType, IKeyData.class, 0);
            if (dataType == null)
                dataType = TypeParam.infer1(targetType, IKeyData.class, 1);
            if (fn == null)
                throw new NullPointerException("fn");
            return new SimpleMakeRule0<>(priority, targetType, keyType, dataType, fn);
        }

    }

}
