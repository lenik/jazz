package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeSession;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.MakeAction;
import net.bodz.bas.make.fn.IMakeable2;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule2;
import net.bodz.bas.meta.decl.NotNull;

public interface IKeyPatternLikeMakeRule2<Tp extends IKeyPatternLike<Param, K>, Param, K, //
        Keys extends IParameterizedKeys<?, ?>, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends IKeyPatternLikeMakeRule<Tp, Param, K, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    Us getInput1();

    Vs getInput2();

    @Override
    default MakeAction<T> compile(@NotNull T target, @NotNull IMakeSession session)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];
        @SuppressWarnings("unchecked")
        Vs input2s = (Vs) getInputs()[1];

        Param param = pattern.match(target.getKey());
        if (param == null)
            return null;

        UK input1Key = input1s.getKey(param);
        if (input1Key == null)
            return null;

        @SuppressWarnings("unchecked")
        U input1 = (U) session.getData(input1Key);
        if (input1 == null)
            return null;

        VK input2Key = input2s.getKey(param);
        if (input2Key == null)
            return null;

        @SuppressWarnings("unchecked")
        V input2 = (V) session.getData(input2Key);
        if (input2 == null)
            return null;

        IMakeable2<TT, UT, VT> fn = compile(target, input1, input2);
        if (fn == null)
            return null;

        SimpleMakeRule2<T, K, TT, U, UK, UT, V, VK, VT> rule = SimpleMakeRule2.<T, K, TT, U, UK, UT, V, VK, VT>builder()//
                .priority(this.getPriority())//
                .fn(fn).build();

        MakeAction<T> instance = new MakeAction<>(rule, target, input1, input2);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        @SuppressWarnings("unchecked")
        U input1 = (U) inputs[0];
        @SuppressWarnings("unchecked")
        V input2 = (V) inputs[1];
        IMakeable2<TT, UT, VT> fn = compile(target, input1, input2);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData());
            t.setData(tData);
        };
    }

    IMakeable2<TT, UT, VT> compile(@NotNull T target, U input1, V input2)
            throws CompileException;

}
