package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.IMakeable3;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule3;
import net.bodz.bas.meta.decl.NotNull;

public interface ITargetPatternLikeMakeRule3<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
        Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    Ws getInput3();

    @Override
    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];
        @SuppressWarnings("unchecked")
        Vs input2s = (Vs) getInputs()[1];
        @SuppressWarnings("unchecked")
        Ws input3s = (Ws) getInputs()[2];

        Param param = pattern.match(target);
        if (param == null)
            return null;

        U input1 = input1s.getTarget(param, binding);
        if (input1 == null)
            return null;

        V input2 = input2s.getTarget(param, binding);
        if (input2 == null)
            return null;

        W input3 = input3s.getTarget(param, binding);
        if (input3 == null)
            return null;

        IMakeable3<TT, UT, VT, WT> fn = compile(target, input1, input2, input3);
        if (fn == null)
            return null;

        SimpleMakeRule3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> rule = SimpleMakeRule3.<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder()//
                .priority(this.getPriority())//
                .input(input1, input2, input3)//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target, input1, input2, input3);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        @SuppressWarnings("unchecked")
        W input3 = (W) inputs[2];
        IMakeable3<TT, UT, VT, WT> fn = compile(target, input1, input2, input3);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData());
            t.setData(tData);
        };
    }

    IMakeable3<TT, UT, VT, WT> compile(@NotNull T target, U input1, V input2, W input3)
            throws CompileException;

}
