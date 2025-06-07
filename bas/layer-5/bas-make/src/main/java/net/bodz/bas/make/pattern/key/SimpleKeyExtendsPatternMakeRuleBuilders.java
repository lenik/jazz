package net.bodz.bas.make.pattern.key;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IKeyData;

public abstract class SimpleKeyExtendsPatternMakeRuleBuilders<S, Tp extends IKeyExtendsPattern<Param, TK>, Param, TK, T extends IKeyData<TK, TT>, TT> {

    public abstract BiConsumer<S, IKeyExtendsPatternMakeRule<Tp, Param, TK, T, TT>> getApply();

    public abstract S getSubject();

    public abstract Tp getPattern();
//
//    public <Us extends IParameterizedKey<Param, UK>, UK, //
//            U extends IKeyData<UK, UT>, UT> //
//    SimpleKeyPatternMakeRule1.Builder<S, Tp, Param, TK, Us, UK, T, TT, U, UT> input(Us input1s) {
//        return SimpleKeyPatternMakeRule1.<S, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
//                .apply(getApply()).subject(getSubject()) //
//                .pattern(getPattern()) //
//                .input(input1s);
//    }

}
