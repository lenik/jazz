package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule2;

public interface ITargetTypedKeyPatternMakeRule2<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKey<Param, UK>, //
        Vs extends IParameterizedKey<Param, VK>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        extends IKeyPatternLikeMakeRule2<Tp, Param, TK, IParameterizedKey<?, ?>, Us, Vs, T, TT, U, UK, UT, V, VK, VT>,
                ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1(), getInput2() };
    }

}
