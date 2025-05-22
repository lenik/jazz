package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IPatternMakeRule1<Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
        T extends IKeyData<K, TT>, TT, U extends IKeyData<UK, UT>, UT>
        extends IPatternMakeRule<Tp, K, T> {

    @Override
    default int getPriority() {
        return 0;
    }

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys<?, ?>[] { getInput1() };
    }

    Us getInput1();


    @Override
    default MakeRuleInstance<T> compile(@NotNull T target, @NotNull IMakeSession session)
            throws MakeException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        Us input1s = (Us) getInputs()[0];

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

        MakeFunction1<TT, UT> fn = compile(target, input1);
        if (fn == null)
            return null;

        SimpleMakeRule1<T, K, TT, U, UK, UT> rule = SimpleMakeRule1.<T, K, TT, U, UK, UT>builder()//
                .priority(this.getPriority())//
                .input1(input1)//
                .fn(fn).build();

        MakeRuleInstance<T> instance = new MakeRuleInstance<>(rule, input1);
        return instance;
    }

    MakeFunction1<TT, UT> compile(@NotNull T target, U input1)
            throws MakeException;

}
