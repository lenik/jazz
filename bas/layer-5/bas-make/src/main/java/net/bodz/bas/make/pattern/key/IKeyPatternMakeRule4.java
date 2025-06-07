package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule4;

public interface IKeyPatternMakeRule4<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKey<Param, UK>, //
        Vs extends IParameterizedKey<Param, VK>, //
        Ws extends IParameterizedKey<Param, WK>, //
        Xs extends IParameterizedKey<Param, XK>, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT> //
        extends IKeyPatternLikeMakeRule4<Tp, Param, K, IParameterizedKey<?, ?>, Us, Vs, Ws, Xs, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4() };
    }

}
