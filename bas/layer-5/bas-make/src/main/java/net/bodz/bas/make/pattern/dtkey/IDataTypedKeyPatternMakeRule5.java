package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule5;

public interface IDataTypedKeyPatternMakeRule5<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, //
        Ys extends IDataTypedParameterizedKey<Param, YK, YT>, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT, //
        X extends IKeyData<XK, XT>, XK, XT, //
        Y extends IKeyData<YK, YT>, YK, YT> //
        extends IKeyPatternLikeMakeRule5<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, Us, Vs, Ws, Xs, Ys, T, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5() };
    }

}
