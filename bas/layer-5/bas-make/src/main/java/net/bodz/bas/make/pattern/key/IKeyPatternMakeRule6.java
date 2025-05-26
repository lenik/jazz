package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule6;

public interface IKeyPatternMakeRule6<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        Ws extends IParameterizedKeys<Param, WK>, WK, //
        Xs extends IParameterizedKeys<Param, XK>, XK, //
        Ys extends IParameterizedKeys<Param, YK>, YK, //
        Zs extends IParameterizedKeys<Param, ZK>, ZK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT, //
        Z extends IKeyData<ZK, ZT>, ZT> //
        extends IKeyPatternLikeMakeRule6<Tp, Param, K, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6() };
    }

}
