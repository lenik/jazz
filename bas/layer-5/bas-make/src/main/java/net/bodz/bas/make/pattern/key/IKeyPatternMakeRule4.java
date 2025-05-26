package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule4;

public interface IKeyPatternMakeRule4<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        Xs extends IParameterizedKeys<Param, XK>, XK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends IKeyPatternLikeMakeRule4<Tp, Param, K, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] { getInput1(), getInput2(), getInput3(), getInput4() };
    }

}
