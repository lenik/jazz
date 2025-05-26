package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule6;

public interface IDataTypedKeyPatternMakeRule6<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKeys<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKeys<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKeys<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKeys<Param, XK, XT>, XK, //
        Ys extends IDataTypedParameterizedKeys<Param, YK, YT>, YK, //
        Zs extends IDataTypedParameterizedKeys<Param, ZK, ZT>, ZK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT, //
        Z extends IKeyData<ZK, ZT>, ZT> //
        extends IKeyPatternLikeMakeRule6<Tp, Param, K, IDataTypedParameterizedKeys<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6() };
    }

}
