package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IPatternMakeRule2<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKeys<Param, UK>, UK, Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT, V extends IKeyData<VK, VT>, VT>
        extends IPatternMakeRule<Tp, K, T> {

    @Override
    default int getPriority() {
        return 0;
    }

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys<?, ?>[] { getInput1(), getInput2() };
    }

    Us getInput1();

    Vs getInput2();

    @Override
    default MakeRuleInstance<T> compile(@NotNull T target, @NotNull IMakeSession session)
            throws MakeException {
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

        VK input2Key = input2s.getKey(param);
        if (input2Key == null)
            return null;

        @SuppressWarnings("unchecked")
        U input1 = (U) session.getData(input1Key);
        if (input1 == null)
            return null;

        @SuppressWarnings("unchecked")
        V input2 = (V) session.getData(input2Key);
        if (input2 == null)
            return null;

        MakeFunction2<TT, UT, VT> fn = compile(target, input1, input2);
        if (fn == null)
            return null;

        SimpleMakeRule2<T, K, TT, U, UK, UT, V, VK, VT> rule = SimpleMakeRule2.<T, K, TT, U, UK, UT, V, VK, VT>builder()//
                .priority(this.getPriority())//
                .input1(input1)//
                .input2(input2)//
                .fn(fn).build();

        MakeRuleInstance<T> instance = new MakeRuleInstance<>(rule, input1, input2);
        return instance;
    }

    MakeFunction2<TT, UT, VT> compile(@NotNull T target, U input1, V input2)
            throws MakeException;

}
