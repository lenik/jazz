package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule5;

public interface IDataTypedTarget2KeyPatternMakeRule5<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
        Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT> //
        extends IKeyPatternLikeMakeRule5<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>,
                IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5() };
    }

}
