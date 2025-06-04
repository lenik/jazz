package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.SimpleMakeRule;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface ITargetPatternLikeMakeRule<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        T extends IKeyData<TK, TT>, TT>
        extends IPriority {

    Tp getPattern();

    Keys[] getInputs();

    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        Class<? extends Param> paramType = pattern.getParameterType();

        IParameterizedTarget<?, ?, ?, ?>[] inputss = getInputs();

        Param param = pattern.match(target);
        if (param == null)
            return null;

        IKeyData<?, ?>[] inputs = new IKeyData[inputss.length];
        for (int i = 0; i < inputs.length; i++) {
            IParameterizedTarget<?, ?, ?, ?> _inputTargets = inputss[i];
            IKeyData<?, ?> input;
            if (_inputTargets.getParameterType().isAssignableFrom(paramType)) {
                @SuppressWarnings("unchecked")
                IParameterizedTarget<Param, ?, ?, ?> inputTargets = (IParameterizedTarget<Param, ?, ?, ?>) _inputTargets;
                input = inputTargets.getTarget(param, binding);
            } else {
                input = _inputTargets.getTarget(null, binding);
            }
            if (input == null)
                return null;

            inputs[i] = input;
        }

        MakeFunction<T> fn = compile(target, inputs);
        if (fn == null)
            return null;

        SimpleMakeRule<T, TK, TT> rule = SimpleMakeRule.<T, TK, TT, T>builder()//
                .priority(this.getPriority())//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target);
        return instance;
    }

    MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException;

}
