package net.bodz.bas.make;

import java.util.function.BiConsumer;

import net.bodz.bas.make.fn.SimpleMakeRule1;

public class MakeRuleBuilder<S, T extends IKeyData<TK, TT>, TK, TT> {

    S subject;
    BiConsumer<S, IMakeRule<T, TK, TT>> apply;

    public MakeRuleBuilder(S subject, BiConsumer<S, IMakeRule<T, TK, TT>> apply) {
        this.subject = subject;
        this.apply = apply;
    }

    public SimpleMakeRule.Builder<S, T, TK, TT> input(IKeyData<?, ?>... inputs) {
        return SimpleMakeRule.<T, TK, TT, S>builder().apply(apply).subject(subject).input(inputs);
    }

    public <U extends IKeyData<UK, UT>, UK, UT> //
    SimpleMakeRule1.Builder<S, T, TK, TT, U, UK, UT> input(U input1) {
        return SimpleMakeRule1.<S, T, TK, TT, U, UK, UT>builder().apply(apply).subject(subject)//
                .input(input1);
    }

}