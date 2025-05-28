package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IKeyPatternLikeMakeRule<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        T extends IKeyData<K, TT>, TT>
        extends IPriority {

    Tp getPattern();

    Keys[] getInputs();

    default BoundRule<T> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        Class<? extends Param> paramType = pattern.getParameterType();

        IParameterizedKeys<?, ?>[] inputss = getInputs();

        Param param = pattern.match(target.getKey());
        if (param == null)
            return null;

        IKeyData<?, ?>[] inputs = new IKeyData[inputss.length];
        for (int i = 0; i < inputs.length; i++) {
            IParameterizedKeys<?, ?> _keys = inputss[i];
            Object key;
            if (_keys.getParameterType().isAssignableFrom(paramType)) {
                @SuppressWarnings("unchecked")
                IParameterizedKeys<Param, ?> keys = (IParameterizedKeys<Param, ?>) _keys;
                key = keys.getKey(param);
            } else {
                key = _keys.getKey(null);
            }
            if (key == null)
                return null;

            IKeyData<Object, ?> input = binding.getData(key);
            if (input == null)
                return null;

            inputs[i] = input;
        }

        MakeFunction<T> fn = compile(target, inputs);
        if (fn == null)
            return null;

        SimpleMakeRule<T, K, TT> rule = SimpleMakeRule.<T, K, TT>builder()//
                .priority(this.getPriority())//
                .fn(fn).build();

        BoundRule<T> instance = new BoundRule<>(rule, target);
        return instance;
    }

    MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException;

}
