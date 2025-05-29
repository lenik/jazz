package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule3;

public interface ITargetPatternMakeRule3<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT> //
        extends IKeyPatternLikeMakeRule3<Tp, Param, TK, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] { getInput1(), getInput2(), getInput3() };
    }

}
